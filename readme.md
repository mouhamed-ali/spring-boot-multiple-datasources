# Spring boot multiple data-sources

In this example, we will learn how to configure multiple datasources and connect to multiple databases in a typical Spring Boot web application. 
We will use Spring Boot 1.5.10, Spring Data JPA, Hibernate, Spring Rest. 
The used databases are Mysql and Postgres.
 
As we will not install these databases on our localhost, we gonna use a docker environment (docker compose) to run these databases and connect our app to it.
If you check the `docker-compose.yml` file, you'll find that we are using these containers :
 - spring-app : which is our application
 - mysql : the mysql database
 - adminer : (formerly phpMinAdmin) which is used to manage our mysql db
 - postgres : the postgres db
 - pg-admin : used to visualize and manage the postgres db
 
The used db strategy of this example is create-drop, so each time we run the app we will drop it before creating the schema. We gonna populate it then with sql scripts
from resources :
 - initial-db-mysql.sql : for the mysql db
 - initial-db-postgres.sql : for the postgres db
 
The connection parameters to databases are in `application-dev.yml`.
To know more about the datasources configurations, check the config directory. There's explanations in the comments.
 
We are using Using spring data jpa to make curd operations on our entities, check the repository package. And finally, we are exposing some rest endpoints to show and add
entities to our db.

## Development prerequisites

for development, you will need :

- Docker : to run the docker environment
- Java 8
- You favourite IDE : I'm using intellij by the way

## Install

Checkout the project :

    $ git clone https://github.com/amdouni-mohamed-ali/spring-boot-multiple-datasources.git
    $ cd spring-boot-multiple-datasources

As we will not install any database on our local machine.
We will use docker images. Check the `docker-compose-yml` and the description above to know more.

## Run/Stop the app

To start the docker environment, run this command :

    $ docker-compose up --build

Well, the first time you run this command you have to wait about 10 minutes to setup the database otherwise you'll have db connections refused :

You can use these commands if you like to check logs in a new console :

    $ docker-compose logs -f mysql
    $ docker-compose logs -f postgres

Wait for these mysql logs :

```log
2020-02-07 09:59:11+00:00 [Note] [Entrypoint]: Database files initialized
2020-02-07 09:59:11+00:00 [Note] [Entrypoint]: Starting temporary server
2020-02-07 09:59:11+00:00 [Note] [Entrypoint]: Waiting for server startup
2020-02-07 09:59:11 0 [Warning] TIMESTAMP with implicit DEFAULT value is deprecated. Please use --explicit_defaults_for_timestamp server option (see documentation for more details).
|
|
|
2020-02-07 09:59:23+00:00 [Note] [Entrypoint]: Stopping temporary server
2020-02-07 09:59:24 98 [Note] mysqld: Normal shutdown
|
|
2020-02-07 09:59:27+00:00 [Note] [Entrypoint]: Temporary server stopped

2020-02-07 09:59:27+00:00 [Note] [Entrypoint]: MySQL init process done. Ready for start up.
|
|
|
2020-02-07 09:59:29 1 [Warning] 'proxies_priv' entry '@ root@fcdafc790fe6' ignored in --skip-name-resolve mode.
2020-02-07 09:59:29 1 [Note] Event Scheduler: Loaded 0 events
2020-02-07 09:59:29 1 [Note] mysqld: ready for connections.
Version: '5.6.47'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server (GPL)
```

Wait for these postgres logs :

```log
fixing permissions on existing directory /data/postgres ... ok
creating subdirectories ... ok
selecting default max_connections ... 100
selecting default shared_buffers ... 128MB
selecting dynamic shared memory implementation ... posix
creating configuration files ... ok
running bootstrap script ... ok
performing post-bootstrap initialization ... ok
syncing data to disk ... ok
|
|
|
PostgreSQL init process complete; ready for start up.

LOG:  database system was shut down at 2020-02-07 09:59:21 UTC
LOG:  MultiXact member wraparound protections are now enabled
LOG:  autovacuum launcher started
LOG:  database system is ready to accept connections
```

Check the spring app logs now, you may have connections errors. This is normal because databases took much time to start up the first time :

    $ docker logs spring-app

If so restart the container :

    $ docker-compose restart spring-app

To get access to the mysql db, check this url :

- http://localhost:8000

![adminer](https://user-images.githubusercontent.com/16627692/74018732-b17e7c00-4996-11ea-9e55-f7c27ef2fe0e.png)

The password is : password

Check the created tables to make sure that the init script has been executed :

![adminer-customer](https://user-images.githubusercontent.com/16627692/74018797-cc50f080-4996-11ea-9b1f-0f329587c81e.png)

![adminer-address](https://user-images.githubusercontent.com/16627692/74018856-ee4a7300-4996-11ea-934a-4ddddb83c8fe.png)

And to get access to the postgres db, check this url :

- http://localhost:9000

![postgres](https://user-images.githubusercontent.com/16627692/74018948-13d77c80-4997-11ea-97ae-b9f4c82937bd.png)

The password is : password

You have to create a new server :

![pg-create-server](https://user-images.githubusercontent.com/16627692/74018974-22259880-4997-11ea-8c90-1b88f98feaa3.png)

The password is : password

Check the generated tables :

![postgres-account](https://user-images.githubusercontent.com/16627692/74019035-38335900-4997-11ea-8e08-28057739c9e0.png)

![postgres-customer](https://user-images.githubusercontent.com/16627692/74019037-38cbef80-4997-11ea-9d2c-d3ff4c4b2ac1.png)

To stop the app you can simply run this command from the project directory :

    $ docker-compose down

## Test the app

As a test scenario, we gonna add customers to both mysql and postgres and check databases after.

* create a new mysql customer

```shell script
$ curl -X POST -i -H "Content-Type: application/json" -d '{"id":"2", "firstName":"Jack", "lastName":"MYSQL", "email":"jack@example.com", "phoneNumber":"0614582788", "birthDate":"02231997"}' http://localhost:8080/api/mysql
```

```log
HTTP/1.1 201 
Content-Length: 0
Date: Fri, 07 Feb 2020 10:08:32 GMT
```

* get all mysql customers

```shell script
$ curl -H "Content-Type: application/json" localhost:8080/api/mysql
```

```json
[ 
   { 
      "id":1,
      "firstName":"Hugo",
      "lastName":"MYSQL",
      "email":"hugo@gmail.com",
      "phoneNumber":"00047561238",
      "birthDate":"Nevada",
      "address":{ 
         "id":1,
         "building":"Mount Eden Road",
         "street":"445 Mount Eden Road",
         "country":"USA",
         "handler":{ 

         },
         "hibernateLazyInitializer":{ 

         }
      }
   },
   { 
      "id":2,
      "firstName":"Jack",
      "lastName":"MYSQL",
      "email":"jack@example.com",
      "phoneNumber":"0614582788",
      "birthDate":"02231997",
      "address":null
   }
]
```

* create a new postgres customer

```shell script
$ curl -X POST -i -H "Content-Type: application/json" -d '{"id":"2", "firstName":"Jack", "lastName":"POSTGRES", "email":"jack@example.com", "phoneNumber":"0614582788", "birthPlace":"Kentucky"}' http://localhost:8080/api/postgres
```

```log
HTTP/1.1 201 
Content-Length: 0
Date: Fri, 07 Feb 2020 10:30:49 GMT
```

* get all postgres customers

```shell script
$ curl localhost:8080/api/postgres
```

```json
[ 
   { 
      "id":1,
      "firstName":"Hugo",
      "lastName":"POSTGRES",
      "email":"hugo@gmail.com",
      "phoneNumber":"00047561238",
      "birthPlace":"Nevada",
      "accounts":[ 
         { 
            "id":1,
            "balance":200000.0,
            "accountName":"Hugo-POSTGRES",
            "dateOpened":"01021991"
         }
      ]
   },
   { 
      "id":2,
      "firstName":"Jack",
      "lastName":"POSTGRES",
      "email":"jack@example.com",
      "phoneNumber":"0614582788",
      "birthPlace":"Kentucky",
      "accounts":[ 

      ]
   }
]
```

* check the mysql db

![adminer-after](https://user-images.githubusercontent.com/16627692/74022951-dd9dfb00-499e-11ea-8c32-44c3171f098b.png)

* check the postgres db

![postgres-after](https://user-images.githubusercontent.com/16627692/74022973-e7276300-499e-11ea-9a64-5fc85a79970a.png)

## clean up

to clean up the created resources by our docker environment, run these commands :

    $ docker-compose down
    $ docker-compose rm
    $ docker volume prune

the last command will remove all volumes from your docker. Don't run it if you have sensible data in you volumes.

## Authors

- Mohamed Ali AMDOUNI
