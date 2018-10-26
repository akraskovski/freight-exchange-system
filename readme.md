Freight Exchange System
=======================

Freight exchange service platform, which manages orders created by clients or organizations. 
Client — could be an individual person or some registered company employee. Created order is needed to be delivered to some point. 
Forwarder searches the best offer on the marketplace and contacts with carriers in platform who is ready to get and deliver this order.

Application user roles: `ADMIN`, `COMPANY_ADMIN`, `CLIENT`, `CARRIER`, `FORWARDER`.

-----------------------------

**Backend application part build components:**

* Kotlin v1.2.71
* GraalVM v1.0.0-rc8 (Target JVM 8)
* Gradle 4.10
* Spring Boot 2.0.5 RELEASE
* Spring Security(OAuth2.0 with custom independent authorization server)
* PostgreSQL 10.5
* Mailjet as email notification service

------------------------------

Modules structure:
-----------------

1) OAuth2.0 Authorization server. Run as independent application, 
generally used as authentication and authorization provider and container for the user accounts.

2) Application API(Resource Server in OAuth terms). Main business logic of the server side application. 
Was built using gradle modules:
    * Web module
    * Core module
    * Notification module
    
------------------------------------

Building and running the application
------------------------------------

*Note: if you don't have installed gradle - you can use wrappers in each module*
 
Building and running authorization server:
 ------------------------------------------
 
* Go into the `auth-server` package
* Specify you database credentials in `application.yml` properties file
* Call `./gradlew bootRun` or start spring boot application in your preferred IDE
* During first startup application admin account should be created. 
You could check by requesting access token for the admin using password grant flow: 
```
curl -X POST \
  http://localhost:7777/oauth/token \
  -H 'Authorization: Basic ZmVzLWNsaWVudDphSHtHX3tdKTZ5W2o4YXBGenFhUkotQmJ3cEFie2M/LzRQLXhDWywyeU48bTk0WTk=' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'username=fes-admin%40gmail.com&password=admin&grant_type=password'
```

Building and running main server application:
 ------------------------------------------
 * Go into the `server-app` package
 * Specify you database credentials and admin account credentials in `application.yml` properties file
 * Call `./gradlew bootRun` or start spring boot application in your preferred IDE
 * Start using resource server API attaching previously requested Bearer-Token in each request.