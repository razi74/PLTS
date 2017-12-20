export PATH=/opt/jdk1.8.0_151/jre/bin/java:$PATH
mvn clean install
export JAVA_HOME=/opt/jdk1.8.0_151
$JAVA_HOME/bin/java -jar target/plts-1.jar $1
