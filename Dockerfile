FROM openjdk:11-slim
MAINTAINER TBME Labs <info@tbmelabs.ch>

ENTRYPOINT ["/usr/bin/java", "-jar", "/home/serviceregistry/registry-server.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE} /home/serviceregistry/registry-server.jar

RUN useradd -ms /bin/bash serviceregistry
RUN chown serviceregistry /home/serviceregistry/registry-server.jar

USER serviceregistry
WORKDIR /home/serviceregistry
