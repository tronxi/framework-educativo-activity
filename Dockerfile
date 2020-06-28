FROM alpine/git as git
WORKDIR /repo
ADD https://api.github.com/repos/tronxi/framework-educativo-activity/git/refs/heads/develop version.json
RUN git clone https://github.com/tronxi/framework-educativo-activity.git
RUN cd framework-educativo-activity && git checkout develop

FROM maven as builder
COPY --from="git" /repo/framework-educativo-activity .
RUN mvn package spring-boot:repackage

FROM openjdk:8-alpine
ENV clave clave
ENV eureka_host http://localhost
ENV activity_service activity-service
ENV profile dev
ENV rabbit_host localhost
ENV rabbit_pass guest
ENV activity_db localhost
ENV access_key acceskey
ENV secret_key secretkey
ENV secret_token secretToken
COPY --from="builder" /target/framework-educativo-activity-0.0.1-SNAPSHOT.jar .
CMD java -jar -Dspring.profiles.active=${profile} -Djasypt.encryptor.password=${clave} framework-educativo-activity-0.0.1-SNAPSHOT.jar --eureka-host=${eureka_host} --activity-service=${activity_service} --activity-db=${activity_db} --spring.rabbitmq.host=${rabbit_host} --spring.rabbitmq.password=${rabbit_pass} --aws-s3.access-key=${access_key} --aws-s3.secret-key=${secret_key} --secret-token=${secret_token}
