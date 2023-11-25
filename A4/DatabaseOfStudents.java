//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;

public class DatabaseOfStudents {

    private final String url = "jdbc:postgresql://localhost:5432/students"; 
    private String user;
    //private final String user = "postgres";
    private String password;
    //private final String password = "postgres";

    public DatabaseOfStudents(String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }

        this.user = user;
        this.password = password;
    }

    // Add a student
    public void addStudent(String first_name, String last_name, String email, Date enrollment_date) {
        String SQL = "INSERT INTO students(first_name, last_name, email, enrollment_date) VALUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            java.sql.Date sqlDate = new java.sql.Date(enrollment_date.getTime());
            pstmt.setDate(4, sqlDate);
            System.out.println("Student added!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //should be able to return
    public void getAllStudents() {

        String SQL = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.executeUpdate();
            System.out.println("All students' information returned!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    // Update student's email based on id
    public void updateStudentEmail(int student_id, String new_email) {
        String SQL = "UPDATE students SET email=? WHERE student_id=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, new_email);
            pstmt.setInt(2,  student_id);  //set string or set int
            pstmt.executeUpdate();
            System.out.println("Student email updated!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Delete student based on id
    public void deleteStudent(int student_id) {
        String SQL = "DELETE FROM students WHERE student_id=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();
            System.out.println("Student removed!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Get database username and password from the user
        System.out.print("Enter database username: ");
        String dbUser = scanner.nextLine();

        System.out.print("Enter database password: ");
        String dbPassword = scanner.nextLine();

        // Create DatabaseOperations instance with user-input credentials
        DatabaseOfStudents dbOps = new DatabaseOfStudents(dbUser, dbPassword);
    
        //get all students 
        System.out.println("Do you want to retrieve all students' information? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            dbOps.getAllStudents();
        }

        // Add a student
        System.out.println("Do you want to add a student? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            
            System.out.println("What is their first name?");
            String first_name = scanner.nextLine();
            System.out.println("What is their last name?");
            String last_name = scanner.nextLine();
            System.out.println("what is their email? Please use appropriate email format.");
            String email = scanner.nextLine();
            System.out.println("What is their enrollment date? (YYYY-MM-DD)");
            Date enrollmentDate = java.sql.Date.valueOf(scanner.nextLine());
            dbOps.addStudent(first_name, last_name, email, enrollmentDate);

        }
    
        // Update student email
        System.out.println("Do you want to update a student's email? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {

            System.out.println("What is their id?");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("what is the new email? Please use appropriate email format.");
            String newemail = scanner.nextLine();
            dbOps.updateStudentEmail(id, newemail);
            
        }
    
        // Delete student
        System.out.println("Do you want to delete a student? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {

            System.out.println("What is their id?");
            int student_id = Integer.parseInt(scanner.nextLine());
            dbOps.deleteStudent(student_id);
            //room for input from user
        }

        scanner.close();

  }
}