# Nexus Repo example for Maven and Docker 

## Run nexus  
`docker run -d -p 127.0.0.1:8081:8081 -p 127.0.0.1:6000:6000 --name nexus sonatype/nexus3`  

`8081` for default UI  
`6000` for docker endpoint 

## Grab pass from Docker container
`docker exec -it nexus sh` -> `cat nexus-data/admin.password`

#Maven 

## Create maven repo
Create `maven2(hosted)` repo with name `nexus-maven-example`

## Add user and password to .m2/settings.xml
If .xml file does not exist, create one.  
```
...
<servers>
    <server>
        <id>nexus-local</id>
        <username>admin</username>
        <password>admin</password>
    </server>
</servers>
...
```
## Deploy
`mvn clean deploy`

# Docker

## Create Docker repo
Create `docker(hosted)` repo with name `nexus-docker-example`  
add HTTP port 6000 to created repo. 

## Compile and prepare java files.
Navigate to <project>/docker folder and execute `sh jar.sh`  
Should create <project>/target/dependency/* files.  

## Create docker image for nexus
In project folder execute:  
`docker build -f ./docker/app.Dockerfile -t localhost:6000/nexus-docker-example:latest .`

## Login     
`docker login http://localhost:6000/repository/nexus-docker-example/`

## Push   
`docker push localhost:6000/nexus-docker-example:latest`

## Test
`docker rmi localhost:6000/nexus-docker-example:latest`
`docker pull localhost:6000/nexus-docker-example`