# Stage 1: BUILD (Tạo file WAR)
# Cần Maven và JDK để build dự án
FROM maven:3.9.6-jdk-17-slim AS builder
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean install -DskipTests

# Stage 2: DEPLOY (Chạy file WAR)
# Chỉ cần Tomcat và JDK để chạy file WAR
FROM tomcat:9.0-jdk17-temurin-jammy

# Xóa ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# COPY file WAR từ Stage 1 sang Stage 2
# /app/target/EmailListApp.war là đường dẫn trong Stage "builder"
COPY --from=builder /app/target/EmailListApp.war /usr/local/tomcat/webapps/ROOT.war

# Port mặc định là 8080.
EXPOSE 8080