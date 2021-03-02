
@ECHO OFF

docker-compose up -d
call mvn -f ConsumeApiFillDataBase\pom.xml clean package
echo "Esperando levantar el servicio: "
ping 127.0.0.1 -n 6 > nul
cd ConsumeApiFillDataBase
docker build --tag=api-store-bd-m:1.0 --rm=true .

echo "Ejecuta aplicacion consumir api"
docker-compose up -d

echo "Levantar Rest api"
cd ..
call mvn -f RestApi\pom.xml clean package

cd RestApi
docker build --tag=api-rest-m:1.0 --rm=true .
docker-compose up