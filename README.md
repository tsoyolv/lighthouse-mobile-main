# lighthouse mobile-main service

##### To run micro-service locally:
1. Install Java 14 or higher
2. Install Docker for DataBase image 
```
docker pull postgres 
docker run -d -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=password postgres
```
3. Create in Postgres DATABASE with name 'lighthousedb'.
   For example:
```
docker exec -it postgres bash
psql -U postgres
CREATE DATABASE lighthousedb;
```
   or in IDEA:
   click on Database -> add datasource PostgreSql and
```   
CREATE DATABASE lighthousedb;
```
4. Run application! 
```
./gradlew bootRun
```

##### To push docker image to dockerhub:
1. Run command from root folder 
```
/bin/bash docker/dockerhub-push
``` 

To push docker image to registry:
1. Change docker registry credentials if needed in registry-push file
2. Run command from root folder
```
/bin/bash docker/registry-push
``` 