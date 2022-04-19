@echo off
cd WEB-INF\classes\com\thinking\machines\hr\dl
javac -classpath ..\..\..\..\..;..\..\..\..\..\..\lib\*;. *.java
cd..
cd beans
javac *.java
cd..
cd utils
javac -classpath ..\..\..\..\..;..\..\..\..\..\..\lib\*;. *.java
cd..
cd tags
javac -classpath c:\tomcat9\lib\*;..\..\..\..\..;. *.java
cd..
cd servlets
javac -classpath c:\tomcat9\lib\*;..\..\..\..\..;..\..\..\..\..\..\lib\*;. *.java
cd c:\tomcat9\webapps\growwMore