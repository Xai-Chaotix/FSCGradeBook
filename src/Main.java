
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Make Scanner
        Scanner input = new Scanner(System.in);

        // Other variables
        int choice; // user choice
        int value;  // value to insert, delete, or search for
        int numCourses;
        String[] command;

        // Make a new Linked List called myList
        numCourses = Integer.parseInt(input.nextLine());
        FSCcourseRoster[] courses = new FSCcourseRoster[numCourses];

        for (int i = 0; i < numCourses; i++) {
            courses[i] = new FSCcourseRoster();
            courses[i].setCourseNumber(input.nextLine());
        }
        System.out.println("Welcome to the FSC Grade Book.\n");
        System.out.println("The following course(s) have been added to the database:");
        for (FSCcourseRoster course : courses) {
            System.out.println("	" + course.getCourseNumber());
        }
        System.out.println("");
        while (true) {
            command = input.nextLine().split(" ");

            if (command[0].equals("ADDRECORD")) {
                //Assign Course Number
                String courseNumber = command[1];
                //Assign ID
                int ID = Integer.parseInt(command[2]);
                //Assign First Name
                String firstName = command[3];
                //Assign Last Name
                String lastName = command[4];
                //Assign Exam Grade 1
                int examGrade1 = Integer.parseInt(command[5]);
                //Assign Exam Grade 2
                int examGrade2 = Integer.parseInt(command[6]);
                //Assign Exam Grade 3
                int examGrade3 = Integer.parseInt(command[7]);
                //Assign Final Grades
                double finalGrade = ((((examGrade1) / 100.0) * 30.0) + (((examGrade2) / 100.0) * 30.0) + (((examGrade3) / 100.0) * 40.0));
                //Assign Letter Grades
                char letterGrade = getLetterGrade(finalGrade);

                addRecord(courseNumber, ID, firstName, lastName, examGrade1, examGrade2, examGrade3, finalGrade, letterGrade, courses);

            } else if (command[0].equals("DELETERECORD")) {
                int ID = Integer.parseInt(command[1]);
                deleteRecord(ID, courses);
            } else if (command[0].equals("SEARCHBYID")) {
                int ID = Integer.parseInt(command[1]);
                searchByID(ID, courses);
            } else if (command[0].equals("SEARCHBYNAME")) {
                //Assign First Name
                String firstName = command[1];
                //Assign Last Name
                String lastName = command[2];
                searchByName(firstName, lastName, courses);
            } else if ((command[0].equals("DISPLAYSTATS"))) {
                if (command[1].equals("ALL")) {
                    displayAllStats(courses);
                } else {
                    String courseNumber = command[1];
                    displayStats(courseNumber, courses);
                }
            } else if ((command[0].equals("DISPLAYSTUDENTS"))) {
                if (command[1].equals("ALL")) {
                    displayAllStudents(courses);
                } else {
                    String courseNumber = command[1];
                    displayStudents(courseNumber, courses);
                }
            } else if (command[0].equals("QUIT")) {
                System.out.println("Thank you for using the the FSC Grade Book.\n"
                        + "\n"
                        + "Goodbye.\n");
                break;
            } else {
                System.out.println("WE Messed up");
            }
        }
    }

    public static char getLetterGrade(double finalGrade) {
        //Assign letter grade to number final grade
        if (finalGrade >= 90) {
            return 'A';
        } else if (finalGrade >= 80) {
            return 'B';
        } else if (finalGrade >= 70) {
            return 'C';
        } else if (finalGrade >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void addRecord(String courseNumber, int ID, String firstName, String lastName, int examGrade1, int examGrade2, int examGrade3, double finalGrade, char letterGrade, FSCcourseRoster[] courses) {
        for (FSCcourseRoster course : courses) {
            if (course.getCourseNumber().equals(courseNumber)) {
                // Create a new student and calculate their final grade
                int[] examGrades = new int[]{examGrade1, examGrade2, examGrade3};
                // Insert the student into the course roster
                course.insert(courseNumber, ID, firstName, lastName, examGrades, finalGrade, letterGrade);

                // Create a new MocsMember object for the customer
                System.out.printf("Command: ADDRECORD\n"
                        + "	%s %s (ID# %d) has been added to %s.\n"
                        + "	Final Grade: %.2f (%c).\n\n", firstName, lastName, ID, courseNumber, finalGrade, letterGrade);
                return;

            }

        }
        System.out.printf("Command: ADDRECORD\n"
                + "	ERROR: cannot add student. Course \"%s\" does not exist.\n\n", courseNumber);
    }

    public static void deleteRecord(int ID, FSCcourseRoster[] courses) {
        System.out.println("Command: DELETERECORD");
        int count = -1;
        for (FSCcourseRoster course : courses) {
            Student tempStudent = course.findNodeByID(ID);
            if (course.findNodeByID(ID) != null) {
                course.delete(ID);
                System.out.printf("	%s %s (ID# %d) has been deleted from %s.\n\n", tempStudent.getFirstName(), tempStudent.getLastName(), ID, course.getCourseNumber());
                count = 0;
            }
            // Decrease the total number of students 
        }
        if (count == 0) {
            Student.setNumStudents(Student.getNumStudents() - 1);
        } else {
            System.out.printf("	ERROR: cannot delete student. ID# %d does not exist.\n\n", ID);
        }

    }

    private static void searchByID(int ID, FSCcourseRoster[] courses) {
        System.out.println("Command: SEARCHBYID");
        for (FSCcourseRoster course : courses) {
            Student tempStudent = course.findNodeByID(ID);
            if (course.findNodeByID(ID) != null) {
                System.out.printf("Student Record for %s %s (ID# %d):\n"
                        + "	Course: %s\n"
                        + "		Exam 1:       %d\n"
                        + "		Exam 2:       %d\n"
                        + "		Final Exam:   %d\n"
                        + "		Final Grade:  %.2f\n"
                        + "		Letter Grade: %c\n\n", tempStudent.getFirstName(), tempStudent.getLastName(), ID, tempStudent.getCourseNumber(), tempStudent.getExamGrades()[0], tempStudent.getExamGrades()[1], tempStudent.getExamGrades()[2], tempStudent.getFinalGrade(), tempStudent.getLetterGrade());
                return;
            }
        }
        System.out.printf("	ERROR: there is no record for student ID# %d.\n\n", ID);
    }

    private static void searchByName(String firstName, String lastName, FSCcourseRoster[] courses) {
        System.out.println("Command: SEARCHBYNAME");
        for (FSCcourseRoster course : courses) {
            Student tempStudent = course.findNodeByName(firstName, lastName);
            if (course.findNodeByName(firstName, lastName) != null) {
                System.out.printf("Student Record for %s %s (ID# %d):\n"
                        + "	Course: %s\n"
                        + "		Exam 1:       %d\n"
                        + "		Exam 2:       %d\n"
                        + "		Final Exam:   %d\n"
                        + "		Final Grade:  %.2f\n"
                        + "		Letter Grade: %c\n\n", tempStudent.getFirstName(), tempStudent.getLastName(), tempStudent.getID(), tempStudent.getCourseNumber(), tempStudent.getExamGrades()[0], tempStudent.getExamGrades()[1], tempStudent.getExamGrades()[2], tempStudent.getFinalGrade(), tempStudent.getLetterGrade());
                return;
            }
        }
        System.out.printf("	ERROR: there is no record for student \"%s %s\".\n\n", firstName, lastName);

    }

    private static void displayAllStats(FSCcourseRoster[] courses) {
        double finalGradeall = 0.0000000;
        double[] finalGrade = new double[Student.getNumStudents()];
        char[] letterGrade = new char[Student.getNumStudents()];
        if (Student.getNumStudents() == 0) {
            System.out.println("Command: DISPLAYSTATS (ALL)\n"
                    + "Statistical Results for All Courses:\n"
                    + "	Total number of student records: 0\n"
                    + "	Average Score: 0.00\n"
                    + "	Highest Score: 0.00\n"
                    + "	Lowest Score:  0.00\n"
                    + "	Total 'A' Grades: 0 (0.00% of class)\n"
                    + "	Total 'B' Grades: 0 (0.00% of class)\n"
                    + "	Total 'C' Grades: 0 (0.00% of class)\n"
                    + "	Total 'D' Grades: 0 (0.00% of class)\n"
                    + "	Total 'F' Grades: 0 (0.00% of class)\n");
            return;
        }
        int count = 0;
        for (FSCcourseRoster course : courses) {   
            Student helpPtr = course.getHead();
            while(helpPtr!=null){
                finalGradeall+=helpPtr.getFinalGrade();
                finalGrade[count] =helpPtr.getFinalGrade();
                letterGrade[count]= helpPtr.getLetterGrade();
                count++;
                helpPtr = helpPtr.getNext();
                
            }
      
        }

        System.out.printf("Command: DISPLAYSTATS (ALL)\n");
        System.out.println("Statistical Results for All Courses:");
        System.out.printf("	Total number of student records: %d\n", Student.getNumStudents());
        System.out.printf("	Average Score: %.2f\n", finalGradeall/Student.getNumStudents());
        System.out.printf("	Highest Score: %.2f\n", maxValue(finalGrade));
        System.out.printf("	Lowest Score:  %.2f\n", minValue(finalGrade));
        System.out.printf("	Total 'A' Grades: %d (%.2f%% of class)\n", numOfGrades('A', letterGrade), (((numOfGrades('A', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'B' Grades: %d (%.2f%% of class)\n", numOfGrades('B', letterGrade), (((numOfGrades('B', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'C' Grades: %d (%.2f%% of class)\n", numOfGrades('C', letterGrade), (((numOfGrades('C', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'D' Grades: %d (%.2f%% of class)\n", numOfGrades('D', letterGrade), (((numOfGrades('D', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'F' Grades: %d (%.2f%% of class)\n", numOfGrades('F', letterGrade), (((numOfGrades('F', letterGrade) * 100.00) / Student.getNumStudents())));

    }

    private static void displayStats(String courseNumber, FSCcourseRoster[] courses) {
        for (FSCcourseRoster course : courses) {
            if (courseNumber.equals(course.getCourseNumber())) {
                int numStudents = course.countNodes();
                double[] finalGrade = course.finalGrade(numStudents);
                char[] letterGrade = course.letterGrade(numStudents);
                if (numStudents != 0) {
                    System.out.printf("Command: DISPLAYSTATS (%s)\n", courseNumber);
                    System.out.printf("Statistical Results of %s:\n", courseNumber);
                    System.out.printf("	Total number of student records: %d\n", numStudents);
                    System.out.printf("	Average Score: %.2f\n", avgElements(finalGrade));
                    System.out.printf("	Highest Score: %.2f\n", maxValue(finalGrade));
                    System.out.printf("	Lowest Score:  %.2f\n", minValue(finalGrade));
                    System.out.printf("	Total 'A' Grades: %d (%.2f%% of class)\n", numOfGrades('A', letterGrade), (((numOfGrades('A', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'B' Grades: %d (%.2f%% of class)\n", numOfGrades('B', letterGrade), (((numOfGrades('B', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'C' Grades: %d (%.2f%% of class)\n", numOfGrades('C', letterGrade), (((numOfGrades('C', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'D' Grades: %d (%.2f%% of class)\n", numOfGrades('D', letterGrade), (((numOfGrades('D', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'F' Grades: %d (%.2f%% of class)\n\n", numOfGrades('F', letterGrade), (((numOfGrades('F', letterGrade) * 100.00) / numStudents)));
                } else {
                    if (numStudents == 0) {
                        System.out.printf("Command: DISPLAYSTATS (%s)\n", courseNumber);
                    System.out.printf("Statistical Results of %s:\n", courseNumber);
                    System.out.printf("	Total number of student records: 0\n");
                    System.out.printf("	Average Score: 0.00\n");
                    System.out.printf("	Highest Score: 0.00\n");
                    System.out.printf("	Lowest Score:  0.00\n");
                    System.out.printf("	Total 'A' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'B' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'C' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'D' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'F' Grades: 0 (0.00%% of class)\n\n");
                
                        
                        
       
                        return;
                    }
                }

            }

        }
    }

    private static void displayAllStudents(FSCcourseRoster[] courses) {
        if (Student.getNumStudents() == 0) {
            System.out.println("Command: DISPLAYSTUDENTS (ALL)\n"
                    + "	ERROR: there are no students currently in the system.\n");
            return;
        }
        System.out.println("Command: DISPLAYSTUDENTS (ALL)");
        for (FSCcourseRoster course : courses) {
            int numStudents = course.countNodes();
            if (numStudents>0){
            System.out.printf("Course Roster for %s:\n", course.getCourseNumber());

            course.PrintRoster();
            }
        }
        System.out.println();
    }

    private static void displayStudents(String courseNumber, FSCcourseRoster[] courses) {
        int count = 0;
        System.out.printf("Command: DISPLAYSTUDENTS (%s)\n", courseNumber);

        for (FSCcourseRoster course : courses) {
            int numStudents = course.countNodes();
            if (course.getCourseNumber().equals(courseNumber)) {
                if (numStudents != 0) {
                    System.out.printf("Course Roster for %s:\n", course.getCourseNumber());

                    course.PrintRoster();
                } else {
                    System.out.printf("	ERROR: there are no student records for %s.\n", courseNumber);
                }
            }
        }
        System.out.println();
    }

    public static double avgElements(double[] array) {
        //Find the average
        double sum = 0.00;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];

        }
        return (sum * 1.0) / array.length;
    }

    public static double minValue(double[] nums) {
        //Find the minimum
        double min = 10000000.00;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }

        }
        return min;
    }

    public static double maxValue(double[] nums) {
        //Find the maximum
        double max = -10000000.00;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }

        }
        return max;
    }

    public static int numOfGrades(char letter, char[] letterGrade) {
        //Find the grade number
        int count = 0;
        for (int i = 0; i < letterGrade.length; i++) {
            if (letterGrade[i] == letter) {
                count++;
            }
        }
        return count;
    }
}
/*
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Make Scanner
        Scanner input = new Scanner(System.in);

        // Other variables
        int choice; // user choice
        int value;  // value to insert, delete, or search for
        int numCourses;
        String[] command;

        // Make a new Linked List called myList
        numCourses = Integer.parseInt(input.nextLine());
        FSCcourseRoster[] courses = new FSCcourseRoster[numCourses];

        for (int i = 0; i < numCourses; i++) {
            courses[i] = new FSCcourseRoster();
            courses[i].setCourseNumber(input.nextLine());
        }
        System.out.println("Welcome to the FSC Grade Book.\n");
        System.out.println("The following course(s) have been added to the database:");
        for (FSCcourseRoster course : courses) {
            System.out.println("	" + course.getCourseNumber());
        }
        System.out.println("");
        while (true) {
            command = input.nextLine().split(" ");

            if (command[0].equals("ADDRECORD")) {
                //Assign Course Number
                String courseNumber = command[1];
                //Assign ID
                int ID = Integer.parseInt(command[2]);
                //Assign First Name
                String firstName = command[3];
                //Assign Last Name
                String lastName = command[4];
                //Assign Exam Grade 1
                int examGrade1 = Integer.parseInt(command[5]);
                //Assign Exam Grade 2
                int examGrade2 = Integer.parseInt(command[6]);
                //Assign Exam Grade 3
                int examGrade3 = Integer.parseInt(command[7]);
                //Assign Final Grades
                double finalGrade = ((((examGrade1) / 100.0) * 30.0) + (((examGrade2) / 100.0) * 30.0) + (((examGrade3) / 100.0) * 40.0));
                //Assign Letter Grades
                char letterGrade = getLetterGrade(finalGrade);

                addRecord(courseNumber, ID, firstName, lastName, examGrade1, examGrade2, examGrade3, finalGrade, letterGrade, courses);

            } else if (command[0].equals("DELETERECORD")) {
                int ID = Integer.parseInt(command[1]);
                deleteRecord(ID, courses);
            } else if (command[0].equals("SEARCHBYID")) {
                int ID = Integer.parseInt(command[1]);
                searchByID(ID, courses);
            } else if (command[0].equals("SEARCHBYNAME")) {
                //Assign First Name
                String firstName = command[1];
                //Assign Last Name
                String lastName = command[2];
                searchByName(firstName, lastName, courses);
            } else if ((command[0].equals("DISPLAYSTATS"))) {
                if (command[1].equals("ALL")) {
                    displayAllStats(courses);
                } else {
                    String courseNumber = command[1];
                    displayStats(courseNumber, courses);
                }
            } else if ((command[0].equals("DISPLAYSTUDENTS"))) {
                if (command[1].equals("ALL")) {
                    displayAllStudents(courses);
                } else {
                    String courseNumber = command[1];
                    displayStudents(courseNumber, courses);
                }
            } else if (command[0].equals("QUIT")) {
                System.out.println("Thank you for using the the FSC Grade Book.\n"
                        + "\n"
                        + "Goodbye.\n");
                break;
            } else {
                System.out.println("WE Messed up");
            }
        }
    }

    public static char getLetterGrade(double finalGrade) {
        //Assign letter grade to number final grade
        if (finalGrade >= 90) {
            return 'A';
        } else if (finalGrade >= 80) {
            return 'B';
        } else if (finalGrade >= 70) {
            return 'C';
        } else if (finalGrade >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void addRecord(String courseNumber, int ID, String firstName, String lastName, int examGrade1, int examGrade2, int examGrade3, double finalGrade, char letterGrade, FSCcourseRoster[] courses) {
        for (FSCcourseRoster course : courses) {
            if (course.getCourseNumber().equals(courseNumber)) {
                // Create a new student and calculate their final grade
                int[] examGrades = new int[]{examGrade1, examGrade2, examGrade3};
                // Insert the student into the course roster
                course.insert(courseNumber, ID, firstName, lastName, examGrades, finalGrade, letterGrade);

                // Create a new MocsMember object for the customer
                System.out.printf("Command: ADDRECORD\n"
                        + "	%s %s (ID# %d) has been added to %s.\n"
                        + "	Final Grade: %.2f (%c).\n\n", firstName, lastName, ID, courseNumber, finalGrade, letterGrade);
                return;

            }

        }
        System.out.printf("Command: ADDRECORD\n"
                + "	ERROR: cannot add student. Course \"%s\" does not exist.\n\n", courseNumber);
    }

    public static void deleteRecord(int ID, FSCcourseRoster[] courses) {
        System.out.println("Command: DELETERECORD");
        int count = -1;
        for (FSCcourseRoster course : courses) {
            Student tempStudent = course.findNodeByID(ID);
            if (course.findNodeByID(ID) != null) {
                course.delete(ID);
                System.out.printf("	%s %s (ID# %d) has been deleted from %s.\n\n", tempStudent.getFirstName(), tempStudent.getLastName(), ID, course.getCourseNumber());
                count = 0;
            }
            // Decrease the total number of students 
        }
        if (count == 0) {
            Student.setNumStudents(Student.getNumStudents() - 1);
        } else {
            System.out.printf("	ERROR: cannot delete student. ID# %d does not exist.\n\n", ID);
        }

    }

    private static void searchByID(int ID, FSCcourseRoster[] courses) {
        System.out.println("Command: SEARCHBYID");
        for (FSCcourseRoster course : courses) {
            Student tempStudent = course.findNodeByID(ID);
            if (course.findNodeByID(ID) != null) {
                System.out.printf("Student Record for %s %s (ID# %d):\n"
                        + "	Course: %s\n"
                        + "		Exam 1:       %d\n"
                        + "		Exam 2:       %d\n"
                        + "		Final Exam:   %d\n"
                        + "		Final Grade:  %.2f\n"
                        + "		Letter Grade: %c\n\n", tempStudent.getFirstName(), tempStudent.getLastName(), ID, tempStudent.getCourseNumber(), tempStudent.getExamGrades()[0], tempStudent.getExamGrades()[1], tempStudent.getExamGrades()[2], tempStudent.getFinalGrade(), tempStudent.getLetterGrade());
                return;
            }
        }
        System.out.printf("	ERROR: there is no record for student ID# %d.\n\n", ID);
    }

    private static void searchByName(String firstName, String lastName, FSCcourseRoster[] courses) {
        System.out.println("Command: SEARCHBYNAME");
        for (FSCcourseRoster course : courses) {
            Student tempStudent = course.findNodeByName(firstName, lastName);
            if (course.findNodeByName(firstName, lastName) != null) {
                System.out.printf("Student Record for %s %s (ID# %d):\n"
                        + "	Course: %s\n"
                        + "		Exam 1:       %d\n"
                        + "		Exam 2:       %d\n"
                        + "		Final Exam:   %d\n"
                        + "		Final Grade:  %.2f\n"
                        + "		Letter Grade: %c\n\n", tempStudent.getFirstName(), tempStudent.getLastName(), tempStudent.getID(), tempStudent.getCourseNumber(), tempStudent.getExamGrades()[0], tempStudent.getExamGrades()[1], tempStudent.getExamGrades()[2], tempStudent.getFinalGrade(), tempStudent.getLetterGrade());
                return;
            }
        }
        System.out.printf("	ERROR: there is no record for student \"%s %s\".\n\n", firstName, lastName);

    }

    private static void displayAllStats(FSCcourseRoster[] courses) {
        double finalGradeall = 0.0000000;
        double[] finalGrade = new double[Student.getNumStudents()];
        char[] letterGrade = new char[Student.getNumStudents()];
        if (Student.getNumStudents() == 0) {
            System.out.println("Command: DISPLAYSTATS (ALL)\n"
                    + "Statistical Results for All Courses:\n"
                    + "	Total number of student records: 0\n"
                    + "	Average Score: 0.00\n"
                    + "	Highest Score: 0.00\n"
                    + "	Lowest Score:  0.00\n"
                    + "	Total 'A' Grades: 0 (0.00% of class)\n"
                    + "	Total 'B' Grades: 0 (0.00% of class)\n"
                    + "	Total 'C' Grades: 0 (0.00% of class)\n"
                    + "	Total 'D' Grades: 0 (0.00% of class)\n"
                    + "	Total 'F' Grades: 0 (0.00% of class)\n");
            return;
        }
        int count = 0;
        for (FSCcourseRoster course : courses) {   
            Student helpPtr = course.getHead();
            while(helpPtr!=null){
                finalGradeall+=helpPtr.getFinalGrade();
                finalGrade[count] =helpPtr.getFinalGrade();
                letterGrade[count]= helpPtr.getLetterGrade();
                count++;
                helpPtr = helpPtr.getNext();
                
            }
      
        }

        System.out.printf("Command: DISPLAYSTATS (ALL)\n");
        System.out.println("Statistical Results for All Courses:");
        System.out.printf("	Total number of student records: %d\n", Student.getNumStudents());
        System.out.printf("	Average Score: %.2f\n", finalGradeall/Student.getNumStudents());
        System.out.printf("	Highest Score: %.2f\n", maxValue(finalGrade));
        System.out.printf("	Lowest Score:  %.2f\n", minValue(finalGrade));
        System.out.printf("	Total 'A' Grades: %d (%.2f%% of class)\n", numOfGrades('A', letterGrade), (((numOfGrades('A', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'B' Grades: %d (%.2f%% of class)\n", numOfGrades('B', letterGrade), (((numOfGrades('B', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'C' Grades: %d (%.2f%% of class)\n", numOfGrades('C', letterGrade), (((numOfGrades('C', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'D' Grades: %d (%.2f%% of class)\n", numOfGrades('D', letterGrade), (((numOfGrades('D', letterGrade) * 100.00) / Student.getNumStudents())));
        System.out.printf("	Total 'F' Grades: %d (%.2f%% of class)\n", numOfGrades('F', letterGrade), (((numOfGrades('F', letterGrade) * 100.00) / Student.getNumStudents())));

    }

    private static void displayStats(String courseNumber, FSCcourseRoster[] courses) {
        for (FSCcourseRoster course : courses) {
            if (courseNumber.equals(course.getCourseNumber())) {
                int numStudents = course.countNodes();
                double[] finalGrade = course.finalGrade(numStudents);
                char[] letterGrade = course.letterGrade(numStudents);
                if (numStudents != 0) {
                    System.out.printf("Command: DISPLAYSTATS (%s)\n", courseNumber);
                    System.out.printf("Statistical Results of %s:\n", courseNumber);
                    System.out.printf("	Total number of student records: %d\n", numStudents);
                    System.out.printf("	Average Score: %.2f\n", avgElements(finalGrade));
                    System.out.printf("	Highest Score: %.2f\n", maxValue(finalGrade));
                    System.out.printf("	Lowest Score:  %.2f\n", minValue(finalGrade));
                    System.out.printf("	Total 'A' Grades: %d (%.2f%% of class)\n", numOfGrades('A', letterGrade), (((numOfGrades('A', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'B' Grades: %d (%.2f%% of class)\n", numOfGrades('B', letterGrade), (((numOfGrades('B', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'C' Grades: %d (%.2f%% of class)\n", numOfGrades('C', letterGrade), (((numOfGrades('C', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'D' Grades: %d (%.2f%% of class)\n", numOfGrades('D', letterGrade), (((numOfGrades('D', letterGrade) * 100.00) / numStudents)));
                    System.out.printf("	Total 'F' Grades: %d (%.2f%% of class)\n\n", numOfGrades('F', letterGrade), (((numOfGrades('F', letterGrade) * 100.00) / numStudents)));
                } else {
                    if (numStudents == 0) {
                        System.out.printf("Command: DISPLAYSTATS (%s)\n", courseNumber);
                    System.out.printf("Statistical Results of %s:\n", courseNumber);
                    System.out.printf("	Total number of student records: 0\n");
                    System.out.printf("	Average Score: 0.00\n");
                    System.out.printf("	Highest Score: 0.00\n");
                    System.out.printf("	Lowest Score:  0.00\n");
                    System.out.printf("	Total 'A' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'B' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'C' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'D' Grades: 0 (0.00%% of class)\n");
                    System.out.printf("	Total 'F' Grades: 0 (0.00%% of class)\n\n");
                
                        
                        
       
                        return;
                    }
                }

            }

        }
    }

    private static void displayAllStudents(FSCcourseRoster[] courses) {
        if (Student.getNumStudents() == 0) {
            System.out.println("Command: DISPLAYSTUDENTS (ALL)\n"
                    + "	ERROR: there are no students currently in the system.\n");
            return;
        }
        System.out.println("Command: DISPLAYSTUDENTS (ALL)");
        for (FSCcourseRoster course : courses) {
            int numStudents = course.countNodes();
            if (numStudents>0){
            System.out.printf("Course Roster for %s:\n", course.getCourseNumber());

            course.PrintRoster();
            }
        }
        System.out.println();
    }

    private static void displayStudents(String courseNumber, FSCcourseRoster[] courses) {
        int count = 0;
        System.out.printf("Command: DISPLAYSTUDENTS (%s)\n", courseNumber);

        for (FSCcourseRoster course : courses) {
            int numStudents = course.countNodes();
            if (course.getCourseNumber().equals(courseNumber)) {
                if (numStudents != 0) {
                    System.out.printf("Course Roster for %s:\n", course.getCourseNumber());

                    course.PrintRoster();
                } else {
                    System.out.printf("	ERROR: there are no student records for %s.\n", courseNumber);
                }
            }
        }
        System.out.println();
    }

    public static double avgElements(double[] array) {
        //Find the average
        double sum = 0.00;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];

        }
        return (sum * 1.0) / array.length;
    }

    public static double minValue(double[] nums) {
        //Find the minimum
        double min = 10000000.00;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }

        }
        return min;
    }

    public static double maxValue(double[] nums) {
        //Find the maximum
        double max = -10000000.00;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }

        }
        return max;
    }

    public static int numOfGrades(char letter, char[] letterGrade) {
        //Find the grade number
        int count = 0;
        for (int i = 0; i < letterGrade.length; i++) {
            if (letterGrade[i] == letter) {
                count++;
            }
        }
        return count;
    }
}
*/