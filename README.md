Authors: Michal Morawiec & Dawid Mieszczak 
Masters project backend

RUN PROJECT

docker-compose up -d --build

-d - flag to disable logging
localhost:5000 - authenitication service
localhost:5050 - phpmyadmin
localhost:3306 - mariaDB

http://192.168.0.33:5000/swagger-ui.html <- swagger api
admin user:
firstName: admin
lastName: admin
email: admin@admin.com
password: password

authenitcation
edit http  header:
Authorization Bearer token

("admin", "admin", "admin@admin.com","$2a$10$zVRbsmuxar7PibSddr8a8e1IbyzfjSXgn5N1HtqRxdy24kCuhuVdy");