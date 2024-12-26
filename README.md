c:\kafka>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

C:\kafka> .\bin\windows\kafka-server-start.bat .\config\server.properties

C:\kafka\bin\windows>  ./kafka-topics.bat --create --bootstrap-server localhost:9092 --topic test

C:\kafka\bin\windows>  .\kafka-console-producer.bat --broker-list localhost:9092 --topic test

C:\kafka\bin\windows> .\kafka-console-consumer.bat --topic test --bootstrap-server localhost:9092 --from-beginning
 
 
PS C:\kafka\bin\windows> .\kafka-console-producer.bat --broker-list localhost:9092 --topic test
>{"name":"renjith","age":49}
>



PS C:\kafka\bin\windows> .\kafka-console-consumer.bat --topic test --bootstrap-server localhost:9092 --from-beginning
{"name":"renjith","age":49}


C:\kafka\bin\windows>  ./kafka-topics.bat --list --bootstrap-server localhost:9092


C:\kafka\bin\windows>./kafka-topics.bat --create --bootstrap-server localhost:9092 --topic topic-employee







