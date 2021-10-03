FROM openjdk:16
WORKDIR /app/
COPY ./* ./
RUN javac Main.java
