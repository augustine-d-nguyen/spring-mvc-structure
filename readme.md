**I. - Environments**\
    - JDK 1.7.0_79\
    - Maven 3.3.3\
    - Tomcat 8.0.21\
**II - Building project**\
    - Compile project      : mvn clean package\
    - Run unit test        : mvn clean test\
    - Run integration test : mvn clean verify\
    - Run test coverage    : mvn cobertura:cobertura\
       (Report at ${project}/target/site/cobertura/index.html)\
**III - Deploy**\
    - Copy file war from ${project}/target to ${tomcat_home}/webapps\
    - Start tomcat : catalina.sh run\