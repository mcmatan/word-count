Before you run the project:

1.
Please install redis and run via docker:
docker run -p 6379:6379 -d redis:6.0 redis-server --requirepass "mypass"

2.
Install Kafka and Zookeeper on you machine and run them

3.
Endpoints:
POST http://localhost:8080/count
{path, text, url}
GET http://localhost:8080/statistics?word=house

4.
mvn package


<p align = "center"><img src="https://i.ibb.co/SQVzD5X/Screen-Shot-2020-12-05-at-4-59-02-PM.png" alt="WordCount Icon"/></p>
