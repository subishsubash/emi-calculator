FROM adoptopenjdk/openjdk11:alpine-jre
ADD emi-calculator-DEV.1.0.jar emi-calculator.jar
ENTRYPOINT ["java","-jar","app.jar"]