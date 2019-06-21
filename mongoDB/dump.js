db.createUser(
        {
            user: "admin",
            pwd: "admin",
            roles: [
                {
                    role: "readWrite",
                    db: "admin"
                }
            ]
        }
);

db.users.insertOne(
   { email: "admin@admin.com", firstName: "admin", lastName: "admin", password:"$2a$10$zVRbsmuxar7PibSddr8a8e1IbyzfjSXgn5N1HtqRxdy24kCuhuVdy",role: "ROLE_ADMIN"}
)
