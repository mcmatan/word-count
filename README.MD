# WordCount

## Setup:

1. Add GOOGLE_APPLICATION_CREDENTIALS to you environments variables and set to the json file I've sent via email

2. Install Redis and run via docker:
docker run -p 6379:6379 -d redis:6.0 redis-server --requirepass "mypass"

3. Install Kafka and Zookeeper on you machine (default port)

4. Endpoints:
POST http://localhost:8080/count
{path, text, url}
GET http://localhost:8080/statistics?word=house

5. run: mvn package

<p align = "center"><img src="https://i.ibb.co/NVsy6Fm/Screen-Shot-2020-12-05-at-8-27-42-PM.png"/></p>
