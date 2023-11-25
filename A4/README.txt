
README
Assignment 4:
Files included:

A4_population_query.sql
A4-creation.sql
DatabaseOfStudents.java

Launch PG4Admin, under the "Browser" panel on the left, expand "Servers".
Click on "PostgreSQL #" (where # is the version number).
It will prompt you for the password. Enter the password you set during the installation.
Add your username as well.
Set the database name to students and the port to 5432.


Creating the Database:
On the left browser panel of PG4Admin, right-click on Databases and click Create and then Database.
Name the new database "students" and click Save.
Select the student database by clicking on it.
Open the Query Tool by right-clicking on students and selecting Query Tool.
Click on the Open File icon (looks like a folder) in the toolbar.
Go to the location where you saved A4-creation.sql and open it.
The content of A4-creation.sql is now in the query editor.
Click on the run button (a triangle) to execute the SQL commands.
Now you will see success messages and you can expand the students database tab, click Schemas > public > Tables.
Now you can see all the column names.


Populating the Database:
Right-click on the database name in pgAdmin.
Choose Query Tool to open an SQL editor window.
In the Query Tool, click on the Open File button (it looks like a folder) and navigate to the location of your downloaded A4_population_query.
Once the file is open in the editor, click on the green Run button to execute the SQL commands.
The queries should run without error and run SELECT * FROM students; to confirm
This query fetches all entries from the students table. You should see the all students.


Now you can open the A4 folder in your IDE.
Run the command javac DatabaseOfStudents.java in your terminal and then java DatabaseOfStudents.java
Respond to the console output accordingly.
You should out your username and password first.
Then getAllStudents() returns all the students in the database.
addStudent() allows you to add a new student to the database.
updateStudentEmail() allows you update the email address for a student with the specified id.
deleteStudent() deletes the data of the student with the specified id.