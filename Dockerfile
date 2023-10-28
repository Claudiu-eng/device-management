FROM openjdk:21
ADD target/DS2023_30641_Tulbure_Claudiu_Marcel_1_DeviceManagement-0.0.1-SNAPSHOT.jar device-management.jar
ENTRYPOINT ["java", "-jar", "/device-management.jar"]