FROM tomcat:9.0
EXPOSE 8282
RUN sed -i 's/port="8080"/port="8282"/' /usr/local/tomcat/conf/server.xml
COPY /target/opa_app.war /usr/local/tomcat/webapps/
