#mvn clean
#mvn package

docker build -t authz .
docker rm authCont -f
docker run -i -p 8282:8282 --network="mnetwork" --name authCont authz