# Stage 1: BUILD - Giai đoạn này sử dụng JDK và Maven để biên dịch và đóng gói ứng dụng.
# Chúng ta sử dụng tag 'maven:3-jdk-17' để đảm bảo image tồn tại trên Docker Hub.
FROM maven:3-jdk-17 AS builder

# Đặt thư mục làm việc bên trong container.
WORKDIR /app

# Copy các file cấu hình và mã nguồn
COPY pom.xml .
COPY src /app/src

# Chạy Maven để dọn dẹp, cài đặt các dependency và tạo file WAR.
# -DskipTests để bỏ qua các bài kiểm tra (giúp build nhanh hơn).
RUN mvn clean install -DskipTests

# ----------------------------------------------------------------------

# Stage 2: DEPLOY - Giai đoạn này chỉ cần Tomcat và JDK để chạy ứng dụng (giảm dung lượng image).
# Sử dụng image Tomcat ổn định trên nền JDK 17.
FROM tomcat:9-jdk17-temurin

# Xóa ứng dụng mặc định (ROOT) của Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR đã được tạo ra từ Giai đoạn 1 (builder)
# vào thư mục webapps của Tomcat, và đổi tên thành ROOT.war để chạy ở đường dẫn gốc (/).
# Đường dẫn: /app/target/EmailListApp.war được xác định từ Stage 1.
COPY --from=builder /app/target/EmailListApp.war /usr/local/tomcat/webapps/ROOT.war

# Port mặc định của Tomcat
EXPOSE 8080

# Tomcat sẽ tự khởi động ứng dụng (sử dụng ENTRYPOINT/CMD mặc định của image base)