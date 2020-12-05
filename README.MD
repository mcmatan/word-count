# WordCount

## Setup:

1. Add GOOGLE_APPLICATION_CREDENTIALS to you inviroments veriables, set to the json file that I've sent

2. Please install redis and run via docker:
docker run -p 6379:6379 -d redis:6.0 redis-server --requirepass "mypass"

3. Install Kafka and Zookeeper on you machine and run them

4. Endpoints:
POST http://localhost:8080/count
{path, text, url}
GET http://localhost:8080/statistics?word=house

5. run: mvn package

<p align = "center"><img src="https://i.ibb.co/mvymRbj/Screen-Shot-2020-12-05-at-5-19-25-PM.png" alt="WordCount Icon"/></p>
