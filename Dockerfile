FROM openjdk:8u181-jdk-alpine
MAINTAINER vawu 2064759975@qq.com
WORKDIR /opt
COPY ./ ./
RUN  cd /opt  && \
        mv target/*.jar /opt/core.jar
RUN echo 'Asia/Shanghai' >/etc/timezone
USER root
ENV studentId=00000000
ENV timeTemp=5000
ENV address=null
ENV JAVA_OPTS="\
-server \
-Xmx515m \
-Xmn515m \
-XX:SurvivorRatio=1 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=256m \
-XX:ParallelGCThreads=4 \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/logs/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:+DisableExplicitGC \
-XX:+UseConcMarkSweepGC \
-XX:+UseParNewGC \
-XX:+CMSParallelRemarkEnabled \
-XX:+CMSClassUnloadingEnabled \
-XX:LargePageSizeInBytes=128M \
-XX:+UseFastAccessorMethods \
-XX:+UseCMSInitiatingOccupancyOnly \
-XX:CMSInitiatingOccupancyFraction=80 \
-XX:SoftRefLRUPolicyMSPerMB=0 \
-XX:+PrintClassHistogram \
-XX:+PrintHeapAtGC \
-XX:+UnlockDiagnosticVMOptions \
-XX:+UnlockExperimentalVMOptions \
-XX:+PrintFlagsFinal \
-XX:GCLogFileSize=10M"
CMD java ${JAVA_OPTS} -Duser.timezone=GMT+8 -Dstu.phone=$studentId -Dstu.timeTemp=$timeTemp -Dstu.address=$address -jar core.jar