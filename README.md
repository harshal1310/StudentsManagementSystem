
# API Endpoints

## Admin

### Register Admin
- **URL:** `http://localhost:8080/admin/register`
- **Method:** POST
- **Request Body:**
  json
  {
    "username": "adminUser",
    "password": "adminPass"
  }
  
### Login Admin
- **URL:** `http://localhost:8080/admin/login`
- **Method:** POST
- **Request Body:**
  json
  {
    "username": "adminUser",
    "password": "adminPass"
  }
  
## Courses

### Add Course
- **URL:** `http://localhost:8080/courses/add`
- **Method:** POST
- **Request Body:**
  json
  {
    "courseName": "Bio",
    "courseType": "Science",
    "courseDescription": "An introductory course to Mathematics",
    "duration": "3 months",
    "topics": "Algebra, Geometry, Calculus"
  }
  
### Get All Courses
- **URL:** `http://localhost:8080/courses/all`
- **Method:** GET

### Enroll Student in Course
- **URL:** `http://localhost:8080/courses/2/enroll/1234`
- **Method:** POST

### Unenroll Student from Course
- **URL:** `http://localhost:8080/courses/1/unenroll/1235`
- **Method:** DELETE

### Update Course
- **URL:** `http://localhost:8080/courses/update/{courseId}`
- **Method:** PUT
- **Request Body:**
  json
  {
    "courseName": "Updated Course Name",
    "courseType": "Updated Course Type",
    "courseDescription": "Updated Course Description",
    "duration": "Updated Duration",
    "topics": "Updated Topics"
  }
  
## Students

### Add Student
- **URL:** `http://localhost:8080/students/add`
- **Method:** POST
- **Request Body:**
 json
  {
    "name": "dev Doe",
    "studentAge": 20,
    "studentEmail": "axs.doe@example.com",
    "uniqueCode": "1234",
    "gender": "Male",
    "dateOfBirth": "12-09-2025",
    "email": "cdsc@gmail.com",
    "number": "1234567809"
  }
  
### Get Students Enrolled in Courses
- **URL:** `http://localhost:8080/courses/1234`
- **Method:** GET

### Update Student
- **URL:** `http://localhost:8080/students/update/{studentId}`
- **Method:** PUT
- **Request Body:**
  json
  {
    "name": "Updated Name",
    "dateOfBirth": "Updated Date of Birth",
    "gender": "Updated Gender",
    "email": "Updated Email",
    "number": "Updated Number",
    "parentsName": "Updated Parents Name",
    "password": "Updated Password",
    "uniqueCode": "Updated Unique Code"
  }
 
### Verify Student Identity
- **URL:** `http://localhost:8080/students/verify`
- **Method:** POST
- **Request Body:**
  json
  {
    "studentCode": "1234",
    "dateOfBirth": "12-09-2025"
  }










Get Students by Course ID
URL: http://localhost:8080/courses/{courseId}/students
Method: GET
Description: Retrieves a list of students enrolled in a specific course.
Path Parameters:
courseId (Long): The ID of the course.
Get Students by Name
URL: http://localhost:8080/students/studentsByName?name={name}
Method: GET
Description: Retrieves a list of students by their name.
Query Parameters:
name (String): The name of the student.


