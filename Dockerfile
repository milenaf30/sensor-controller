FROM bellsoft/liberica-openjdk-alpine-musl:11

WORKDIR /var/www/server

COPY .mvn /var/www/server/.mvn
COPY mvnw /var/www/server/mvnw
COPY pom.xml /var/www/server/pom.xml

RUN ./mvnw dependency:resolve

RUN ./mvnw process-resources
RUN ./mvnw compiler:compile

RUN ./mvnw surefire:test

COPY . .

RUN ./mvnw install -DskipTests
RUN ./mvnw compile

ARG environmentParam
ENV ENV=$environmentParam

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n","-Xdebug","-jar","./target/sensor-0.0.1-SNAPSHOT.jar","--spring.profiles.active=${ENV}"]

EXPOSE 8090