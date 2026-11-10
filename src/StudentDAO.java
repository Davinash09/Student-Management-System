import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (id, name, course, marks) VALUES (?, ?, ?, ?)";

        try (Connection con = DBonnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.id);
            ps.setString(2, student.name);
            ps.setString(3, student.course);
            ps.setDouble(4, student.marks);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }


public static void main(String[] args) {
    StudentDAO dao = new StudentDAO();

    for (Student s : dao.getAllStudents()) {
        System.out.println(
            s.id + " | " + s.name + " | " +
            s.course + " | " + s.marks
        );
    }
}


    public List<Student> getAllStudents() {
    	List<Student> students = new ArrayList<>();

    	String sql = "SELECT id, name, course, marks FROM students";

    	try (Connection con = DBonnection.getConnection();
    			PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

    		while (rs.next()) {
    			int id = rs.getInt("id");
    			String name = rs.getString("name");
    			String course = rs.getString("course");
    			double marks = rs.getDouble("marks");

    			Student student = new Student(id, name, course, marks);
    			students.add(student);
    		}

    	} catch (SQLException e) {
    		System.out.println("Error retrieving students: " + e.getMessage());
    	}

    	return students;
    }


public boolean updateStudentMarks(int id, double marks) {
    String sql = "UPDATE students SET marks = ? WHERE id = ?";

    try (Connection con = DBonnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setDouble(1, marks);
        ps.setInt(2, id);

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error updating student: " + e.getMessage());
        return false;
    }
}

public boolean deleteStudent(int id) {
    String sql = "DELETE FROM students WHERE id = ?";

    try (Connection con = DBonnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error deleting student: " + e.getMessage());
        return false;
    }
}

public Student getStudentById(int id) {
    String sql = "SELECT id, name, course, marks FROM students WHERE id = ?";

    try (Connection con = DBonnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("course"),
                    rs.getDouble("marks")
                );
            }
        }

    } catch (SQLException e) {
        System.out.println("Error searching student: " + e.getMessage());
    }

    return null;
}



}
