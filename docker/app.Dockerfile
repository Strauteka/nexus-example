FROM eclipse-temurin:17_35-jdk-centos7
RUN  yum install curl
ARG DEPENDENCY=/target/dependency
RUN mkdir -p service
COPY ${DEPENDENCY}/BOOT-INF/lib /service/lib
COPY ${DEPENDENCY}/META-INF /service/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /service
ENTRYPOINT ["java", "-cp","service:service/lib/*","edu.strauteka.nexus.NexusExampleApplication"]
