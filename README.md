# lighthouse mobile-main service

### To run micro-service locally:
##### 1. Install Java 14 or higher

```
sudo apt update

sudo apt -y install wget curl

wget --no-check-certificate -c --header  "Cookie: oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/otn-pub/java/jdk/15.0.2%2B7/0d1cfde4252546c6931946de8db48ee2/jdk-15.0.2_linux-x64_bin.deb"

sudo apt install ./jdk-15.0.2_linux-x64_bin.deb

cat <<EOF | sudo tee /etc/profile.d/jdk15.sh
export JAVA_HOME=/usr/lib/jvm/jdk-15.0.2
export PATH=\$PATH:\$JAVA_HOME/bin
EOF

source /etc/profile.d/jdk15.sh
```
##### 2. Install Docker
```
sudo apt-get remove docker docker-engine docker.io containerd runc
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo apt-key fingerprint 0EBFCD88

sudo add-apt-repository \
  "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) \
  stable"

sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io
sudo groupadd docker
sudo usermod -aG docker $USER
newgrp docker
docker run hello-world
```
##### 3. Pull Postgres Database image
```
docker pull postgres 
docker run -d -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=password postgres
```
##### 4. Create in Postgres DATABASE with name 'lighthousedb'.
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
##### 5. Run application! 
```
./gradlew bootRun
```
To run task in background use
```
nohup ./gradlew bootRun > mobile-out.log 2>&1 &
```
### To push docker image to dockerhub:
Run command from root directory 
```
/bin/bash docker/dockerhub-push
```