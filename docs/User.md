# User API 

# Register User
Endpoint : POST /users

Request Body : 
```json
{

  "username" : "admin",
  "password" : "secret",
  "name" : "admin_ganteng",
  "phone" : "081233334444"

}
```

Response Body (Success) : 
```json
{
  "message" : "OK"
}
```

Response Body (Fail) : 
```json
{
  "message" : "Username/Password tidak sesuai"
}
```

# Login User
Endpoint : POST /auth/login
```json
{
  "username" : "admin",
  "password" : "secret"
}
```

Response Body (Success):
```json
{
  "message" : "Login Success",
  "data" : {
    "token" : "TOKEN",
    "duration" : 3600
  }
}
```
# Get User
Endpoint : GET /users/current

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) : 
```json
{
  "data" : {
    "username": "admin",
    "name" : "admin_ganteng"
  }
}

```

Response Body (Fail, 401)
```json
{
  "message" : "Unauthorized"
}
```

# Change Password User

Endpoint : PUT /users/current/change-password

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "oldPassword" : "secret",
  "newPassword" : "secret"
}
```

Response Body (Success)
```json
{
  "message" : "Password changed"
}

```

Response Body (Fail, 401)
```json
{
  "message" : "Unauthorized"
}
```

# Delete User
Endpoint : DEL /users

Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "message" : "OK"
}
```



# Logout User
Endpoint : DEL /users/logout

Request Header : 
    X-API-Token : Token

Response Body (Success) : 
```json
{
  "data" : "Logout Success"
}
```