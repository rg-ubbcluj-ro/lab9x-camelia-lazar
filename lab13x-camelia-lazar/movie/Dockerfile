FROM gradle:6.8-jdk11 AS build
COPY --chown=gradle:gradle . /code
WORKDIR /code
RUN gradle build --no-daemon

FROM tomcat:9.0.45-jdk11
WORKDIR /code
RUN sed -i 's/port="8080"/port="8083"/' ${CATALINA_HOME}/conf/server.xml
COPY --from=0 /code/build/libs /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

#ADD
#WORKDIR /code
#
#COPY . /code