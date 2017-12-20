set JAVA_HOME=Z:\installs\java\jdk1.8.0_121\
SET CLASSPATH=%CLASSPATH;.
set param1=%1
call mvn clean install
%JAVA_HOME%\bin\java -jar target/plts-1.jar %param1%