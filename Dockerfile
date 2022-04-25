FROM openjdk:11

ENV MONGO_URI=${MONGO_URI} \
    MONGO_DATABASE=${MONGO_DATABASE} \
    MONGO_USER=${MONGO_USER} \
    MONGO_PASSWORD=${MONGO_PASSWORD} \
    DATA_MONGODB_REPOSITORIES_ENABLED=${DATA_MONGODB_REPOSITORIES_ENABLED:-'true'} \
    SERVER_PORT=${SERVER_PORT:-8081} \
    SERVER_SERVELT_CONTEXT_PATH=${SERVER_SERVELT_CONTEXT_PATH:-'/clientes'}

USER root

EXPOSE ${SERVER_PORT}

WORKDIR /app
ADD ./app/MvpClienteJava-0.0.1.jar /app/
ADD ./application-docker.yml /app/

ENTRYPOINT ["java", "-Xmx512m", "-ms128m", "-jar", "/app/MvpClienteJava-0.0.1.jar", "--spring.config.location=file:///app/application-docker.yml"]
