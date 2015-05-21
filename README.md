# harahetta
network sample program for aiit 2015 network lecture 

## Server

```bash
echo 'api_key=your_recruit_api_key' > src/main/resources/release.properties
mvn compile
mvn exec:java -Dexec.mainClass=jp.ac.aiit.network.harahetta.server.HarahettaServer
```

## Client

```bash
mvn compile
mvn exec:java -Dexec.mainClass=jp.ac.aiit.network.harahetta.client.HarahettaClient

```