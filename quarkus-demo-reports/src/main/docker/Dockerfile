####
#
# Graalvm won't install on Windows, so I used Ubuntu VM in VirtualBox. Allocate at least 6GB of memory
#
# install docker, graalvm, gcc, libz-dev
#
# set GRAALVM_HOME and JAVA_HOME variables
#
# mvn package -Pnative -Dnative-image.docker-build=true
#
# docker build -f src/main/docker/Dockerfile -t quarkus-demo-reports .
#
# sudo docker run -i --rm -p 8080:8080 quarkus-demo-reports
#
###
FROM registry.fedoraproject.org/fedora-minimal
WORKDIR /work/
COPY target/*-runner /work/application
COPY src/main/resources/lib/libsunec.so /work/libsunec.so
RUN chmod 775 /work
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "./application -Dquarkus.http.host=0.0.0.0 -Djava.library.path=/work" ]