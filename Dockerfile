# Sử dụng base image có sẵn JDK và Tomcat
# Ở đây ta dùng Tomcat 9 trên nền JDK 17
FROM tomcat:9.0-jdk17-temurin-jammy

# Xây dựng file WAR bằng Maven
# Render sẽ chạy lệnh BUILD_COMMAND (mvn clean install) trước
# Giả sử file WAR được tạo ra là target/EmailListApp.war

# Xóa ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR vào thư mục webapps của Tomcat
# Chú ý: Đường dẫn này dựa trên output của lệnh build Maven
COPY target/EmailListApp.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat sẽ tự khởi động ứng dụng (ENTRYPOINT mặc định của image tomcat)