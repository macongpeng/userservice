#!/bin/sh
mvn clean package docker:build

#make sure mysql docker container is running
#docker stop demo-mysql
#docker rm demo-mysql
#docker run -d --name demo-mysql -e MYSQL_ROOT_PASSWORD=p4SSW0rd -e MYSQL_DATABASE=userserviceDB -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbp4ss mysql:latest

#sleep 10 seconds to wait for mysql server to be up
#sleep 10
#remove the existing docker container of userservice-docker-webapp
docker stop userservice-docker-webapp
docker rm userservice-docker-webapp

docker run -it --name userservice-docker-webapp --link demo-mysql:mysql -p 8080:8080 horncom/userservice
#docker-compose up
