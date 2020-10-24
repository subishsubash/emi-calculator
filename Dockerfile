FROM adoptopenjdk/openjdk11:alpine-jre
ADD target\emi-calculator-DEV.1.0.jar emi-calculator.jar
ENTRYPOINT ["java","-jar","emi-calculator.jar"]