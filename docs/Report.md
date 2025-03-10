# Report API

# Create Report
Endpoint : POST /reports

Request Header :
X-API-TOKEN : TOKEN

Request Body :

```json
{
  "description": "asd"
}
```

Response Body
```json
{
  "status" : 200,
  "message" : "Report created",
  "data" : {
    "reportId" : "001",
    "description": "asd"
  }
}
```


# Edit Report
Endpoint : PUT /reports

Request Header :
X-API-TOKEN : TOKEN

Request Body :
```json
{
  "reportId": "002",
  "description": "wasd"
}
```

Response Body

```json
{
  "status" : 200,
  "message" : "Report updated",
  "data" : {
    "reportId" : "002",
    "description": "wasd"
  }
}
```

# GET Report
Endpoint : GET /reports

Request Header :
X-API-TOKEN : TOKEN

Query Param :
- attendanceId


Response Body
```json
{
  "data": [
    {
      "reportId": "001",
      "description": "asd"
    },
    {
      "reportId": "002",
      "description": "wasd"
    }
  ]
}
```


# DEL Report
Endpoint : DEL /api/report

Request Header :
X-API-TOKEN : TOKEN

Request Body :

```json
{
  "reportId" : "001"
}
```

Response Body
```json
{
  "message" : "Delete Berhasil",
  "createdDate" : "2024-07-27T09:00:00.511Z"
  
}
```