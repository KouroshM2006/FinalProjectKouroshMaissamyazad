# Course Management System By Kourosh Maissamyazad
The Course Management System (CMS) is designed for educational institutions to manage courses, students, teachers, and assignments. Administrators can create user accounts, teachers can manage and create assignments, and students can enroll in courses, submit assignments, and download their schedules. The system maintains academic records and facilitates interactions between these user types.


log: 50% implementation
- Fully implemented both super classes
- decision to remove the uploadSchedule method from the Student class, because I don't think it would be necessary
- implemented the Course class and later I will add the courseComparator insider class to it
- fully implemented the createUser interface and Administrator class
- created junit testing for all user defined methods
- semi implementation of the Teacher class

log: 100% implementation
- changed the name of the CreateUser interface to Creatable and added new methods to it
- Administrators can now import users from a csv file
- created CourseManagementSystem class to hold users and courses and facilitates searching them up
- Students can now submit assignments and view feedback from their teachers
- teachers can now grade assignments and submit feed back

  

