Signup API: POST
http://localhost:8080/auth/signup

{
    "username":"ankit1",
    "password":"123456",
    "email":"ankit1@gmail.com",
    "mobile":"8120836123"
}
----------------------------------------
Logoin API : POST
http://localhost:8080/auth/login
{
    "username":"ankit",
    "password":"123456"
}
-----------------------------------------
Book Appoinment API : POST
localhost:8080/patient/book

{
    "id":1000000,
    "appointmentDate":1750586448,
    "reason":"head pain"
}

header 
Authorization: Bearer {{authToken}}
------------------------------------------------
Get List of Appoinments : GET

http://localhost:8080/patient/list

header 
Authorization: Bearer {{authToken}}
