cd theodon
#mvn clean package
#mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar
mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.projectKey=phyzicsz_theodon


