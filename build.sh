cd theodon
#mvn clean package
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar

