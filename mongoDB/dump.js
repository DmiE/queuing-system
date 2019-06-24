use admin
db.createUser(  
  {
    user: "admin",
    pwd: "admin",
    roles: [ { role: "root", db: "admin" } ]
  }
);

use app
db.createUser( 
  {
    user: "admin",
    pwd: "admin",
    roles: [ { role: "root", db: "admin" } ]
  }
);

db.users.findAndModify({
    query: { email: "admin@admin.com" },
    update: {
        $setOnInsert: { firstName: "admin", lastName: "admin", password:"$2a$10$zVRbsmuxar7PibSddr8a8e1IbyzfjSXgn5N1HtqRxdy24kCuhuVdy",role: ["ROLE_ADMIN"],"_class": "app.entity.MongoEntities.UserMongoDB" }
    },
    upsert: true // insert the document if it does not exist
});

