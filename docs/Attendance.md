# Attendance API

# Get Status Attendance

Request Header :
X-API-TOKEN : TOKEN

Endpoint : GET /attendances/today

Query Param :
- username : String, username column with LIKE

Response Body :

```json
{
  "id": "001",
  "status": 1, 
  "checkInTime": "2024-07-27T09:00:00.511Z",
  "checkOutTime": "2024-07-27T18:00:00.511Z",
  "reports": [
    {
      "description":   "report 1"  
    },
    {
      "description":   "report 2"
    }
  ]
}
```

# Get All Attendances

Request Header :
X-API-TOKEN : TOKEN

Endpoint : GET /attendances/all

Query Param :
- username : String, username column with LIKE

Response Body :

```json
{
  "data": [
    {
      "attendanceId": "001",
      "status": 1,
      "checkInTime": "2024-07-27T09:00:00.511Z",
      "checkOutTime": null,
      "reports" : []
    },
    {
      "attendanceId": "002",
      "status": 2,
      "checkInTime": "2024-07-27T09:00:00.511Z",
      "checkOutTime": "2024-07-27T18:00:00.511Z",
      "reports" : []
    }
  ]
}
```


# Check-in
Endpoint : POST /attendance/check-in

Request Header : 
X-API-TOKEN : TOKEN


Response Body
```json
{
  "status": 1,
  "message" : "Berhasil Check-In",
  "checkInTime" : "2024-07-27T09:00:00.511Z"
  
}
```

# Check-in
Endpoint : POST /attendance/check-out

Request Header :
X-API-TOKEN : TOKEN

Response Body
```json
{
  "status": 2,
  "message" : "Berhasil Check-Out",
  "checkOutTime" : "2024-07-27T18:00:00.511Z"
}
```





