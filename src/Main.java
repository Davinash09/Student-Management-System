import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
        	System.out.println("1. Add Student");
        	System.out.println("2. View Students");
        	System.out.println("3. Search Student");
        	System.out.println("4. Update Student");
        	System.out.println("5. Delete Student");
        	System.out.println("6. Exit");
        	

        	System.out.print("Enter your choice: ");

        	if (!sc.hasNextInt()) {
        		System.out.println("Invalid input! Please enter a number.");
        		sc.next();
        		continue;
        	}

        	int choice = sc.nextInt();

        	if (choice < 1 || choice > 6) {
        		System.out.println("Invalid choice! Please select 1 to 6.");
        		continue;
        	}


            if (choice == 6) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            if (choice == 1) {

            	System.out.print("Enter student ID: ");

            	if (!sc.hasNextInt()) {
            		System.out.println("Invalid input! Please enter a numeric ID.");
            		sc.next();
            		continue;
            	}

            	int id = sc.nextInt();

            	if (id <= 0) {
            		System.out.println("Invalid ID! Student ID must be positive.");
            		continue;
            	}


            	sc.nextLine();

            	boolean duplicate = false;

            	for (Student s : students) {
            		if (s.id == id) {
            			duplicate = true;
            			break;
            		}
            	}

            	if (duplicate) {
            		System.out.println("Student ID already exists.");
            		continue;
            	}


            	System.out.print("Enter student name: ");
            	String name = sc.nextLine();

            	System.out.print("Enter course: ");
            	String course = sc.nextLine();




            	double marks;

            	while (true) {
            		System.out.print("Enter marks (0-100): ");

            		if (!sc.hasNextDouble()) {
            			System.out.println("Invalid input! Enter numeric marks.");
            			sc.next();
            			continue;
            		}

            		marks = sc.nextDouble();

            		if (marks < 0 || marks > 100) {
            			System.out.println("Marks must be between 0 and 100.");
            			continue;
            		}

            		break;
            	}



            	Student s = new Student(id, name, course, marks);

            	StudentDAO dao = new StudentDAO();

            	if (dao.addStudent(s)) {
            	    System.out.println("Student added to database successfully!");
            	} else {
            	    System.out.println("Failed to add student.");
            	}
            }


            if (choice == 2) {
            	StudentDAO dao = new StudentDAO();
            	ArrayList<Student> dbStudents = new ArrayList<>(dao.getAllStudents());

            	if (dbStudents.isEmpty()) {
            		System.out.println("No students found.");
            	} else {
            		System.out.println("\n--- Student List ---");

            		for (Student s : dbStudents) {
            			System.out.println("ID: " + s.id);
            			System.out.println("Name: " + s.name);
            			System.out.println("Course: " + s.course);
            			System.out.println("Marks: " + s.marks);
            			System.out.println("--------------------");
            		}
            	}
            }


            if (choice == 3) {
            	System.out.print("Enter student ID to search: ");

            	if (!sc.hasNextInt()) {
            		System.out.println("Invalid input! Enter a numeric ID.");
            		sc.next();
            		continue;
            	}

            	int id = sc.nextInt();

            	if (id <= 0) {
            		System.out.println("Student ID must be positive.");
            		continue;
            	}


            	StudentDAO dao = new StudentDAO();
            	Student student = dao.getStudentById(id);

            	if (student != null) {
            		System.out.println("Student found!");
            		System.out.println("ID: " + student.id);
            		System.out.println("Name: " + student.name);
            		System.out.println("Course: " + student.course);
            		System.out.println("Marks: " + student.marks);
            	} else {
            		System.out.println("Student not found.");
            	}
            }


            if (choice == 4) {

            	System.out.print("Enter student ID to update: ");

            	if (!sc.hasNextInt()) {
            		System.out.println("Invalid input! Please enter a numeric ID.");
            		sc.next();
            		continue;
            	}

            	int updateId = sc.nextInt();
            	
            	if (updateId <= 0) {
            	    System.out.println("Student ID must be positive.");
            	    continue;
            	}


            	boolean found = false;


            	System.out.print("Enter new marks (0–100): ");

            	double newMarks;

            	while (true) {
            		if (!sc.hasNextDouble()) {
            			System.out.println("Invalid input! Enter numeric marks.");
            			sc.next();
            			System.out.print("Enter new marks (0–100): ");
            			continue;
            		}

            		newMarks = sc.nextDouble();

            		if (newMarks < 0 || newMarks > 100) {
            			System.out.println("Invalid marks! Enter marks between 0 and 100.");
            			System.out.print("Enter new marks (0–100): ");
            			continue;
            		}

            		break;
            	}

            	StudentDAO daoo = new StudentDAO();

            	if (daoo.updateStudentMarks(updateId, newMarks)) {
            		System.out.println("Student marks updated successfully!");
            	} else {
            		System.out.println("Student not found or marks unchanged.");
            	}


            	if (!found) {
            		System.out.println("Student not found.");
            	}
            	
            }

            if (choice == 5) {

            	System.out.print("Enter student ID to delete: ");

            	if (!sc.hasNextInt()) {
            		System.out.println("Invalid input! Please enter a numeric ID.");
            		sc.next();
            		continue;
            	}

            	int deleteId = sc.nextInt();
            	
            	if (deleteId <= 0) {
            	    System.out.println("Student ID must be positive.");
            	    continue;
            	}


            	

            	StudentDAO daao = new StudentDAO();

            	if (daao.deleteStudent(deleteId)) {
            	    System.out.println("Student deleted successfully!");
            	} else {
            	    System.out.println("Student not found.");
            	}





            }

        
    
        
       
        }
        sc.close();
    }
}
