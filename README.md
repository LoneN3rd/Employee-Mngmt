# Employee Management
A simple Springboot project that provides ways to manage employees and departments
## Employee
- create an employee,
- update employee information,
- delete an employee (updates delete status, not actually deleting) and,
- provides several ways of querying employee information
  - Get all active employees
  - Get all inactive employees
  - Get employee by Id
  - Get employees by first name OR last name
  - Get employees by first name AND last name
  - Get employees by department
  - Get employess whose address contains a given keyword
- gets app info (demonstrates use of enviroment properties)

## Department
- create a department
- update a department
- delete a department
- provides several ways of querying employee information
  - Get all active departments
  - Get all inactive departments
  - Get department by Id
  - Get departments by name

# Concepts Covered
- Data persistence using MySQL
  - JPA, JPQL
  - One to one mapping
  - Custom queries
- Input validation
- Pagination and sorting
- Documentation using Swagger

# DB Used
MySQL

# Other Info
I used swagger OpenAPI definition
Access swagger urls as follows
- http://host:port/context-path/v3/api-docs
- http://host:port/context-path/swagger-ui/index.html
