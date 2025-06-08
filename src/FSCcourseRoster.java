
public class FSCcourseRoster {

    // head: reference variable to the first node of the list
    private Student head;
    private String courseNumber;

    // CONSTRUCTORS
    public FSCcourseRoster() {
        head = null;
    }

    public FSCcourseRoster(Student head, String courseNumber) {
        this.head = head;
        this.courseNumber = courseNumber;
    }

    /* Below are MANY methods that are used on Linked Lists.
	 * 
	 * Examples:
	 * search, insert, delete, isEmpty, sumNodes, and many more
     */
    //
    // boolean | isEmpty()
    //
    public boolean isEmpty() {
        return head == null;
    }

//    //
//    // boolean | searchID(int)
//    //
//    public boolean searchID(int id) {
//        return searchID(head, id);
//    }
//    //
//    // boolean | search(Student, int)
//    //
//
//    private boolean searchID(Student p, int id) {
//        Student helpPtr = p;
//        while (helpPtr != null) {
//            if (helpPtr.getID() == id) {
//                return true;
//            }
//            helpPtr = helpPtr.getNext();
//        }
//        return false;
//    }
//
//    //
//    // boolean | searchName(int)
//    //
//    public boolean searchName(String firstName, String lastName) {
//        return searchName(head, firstName, lastName);
//    }
//    //
//    // boolean | search(Student, int)
//    //
//
//    private boolean searchName(Student p, String firstName, String lastName) {
//        Student helpPtr = p;
//        while (helpPtr != null) {
//            if ((helpPtr.getFirstName().equals(firstName)) && (helpPtr.getLastName().equals(lastName))) {
//                return true;
//            }
//            helpPtr = helpPtr.getNext();
//        }
//        return false;
//    }
    //
    // Student | findNodeByID(int)
    //
    public Student findNodeByID(int id) {
        return findNodeByID(head, id);
    }
    //
    // Student | findNodeByID(Student, int)
    //

    private Student findNodeByID(Student p, int id) {
        Student helpPtr = p;
        while (helpPtr != null) {
            if (helpPtr.getID() == id) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    //
    // Student | findNodeByName(int)
    //
    public Student findNodeByName(String firstName, String lastName) {
        return findNodeByName(head, firstName, lastName);
    }
    //
    // Student | findNodeByID(Student, int)
    //

    private Student findNodeByName(Student p, String firstName, String lastName) {
        Student helpPtr = p;
        while (helpPtr != null) {
            if ((helpPtr.getFirstName().equals(firstName)) && (helpPtr.getLastName().equals(lastName))) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    //
    // void | PrintRoster()
    //
    public void PrintRoster() {
        PrintRoster(head);
    }
    //
    // void | PrintRoster(Student)
    //

    private void PrintRoster(Student head) {
        // We need to traverse...so we need a help ptr
        Student helpPtr = head;
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Print the id value of the node
            System.out.printf("	%s %s (ID# %d):\n"
                    + "		Exam 1:       %d\n"
                    + "		Exam 2:       %d\n"
                    + "		Final Exam:   %d\n"
                    + "		Final Grade:  %.2f\n"
                    + "		Letter Grade: %c\n", helpPtr.getFirstName(), helpPtr.getLastName(), helpPtr.getID(), helpPtr.getExamGrades()[0], helpPtr.getExamGrades()[1], helpPtr.getExamGrades()[2], helpPtr.getFinalGrade(), helpPtr.getLetterGrade());
            // Step one node over
            helpPtr = helpPtr.getNext();
        }

    }

    //
    // void | PrintRoster()
    //
    //
    // void | PrintStats()
    //
    public void PrintStats() {
        PrintStats(head);
    }
    //
    // void | PrintStats(Student)
    //

    private void PrintStats(Student head) {
        // We need to traverse...so we need a help ptr
        Student helpPtr = head;
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Print the id value of the node
            System.out.print(helpPtr.getID() + ", ");
            // Step one node over
            helpPtr = helpPtr.getNext();
        }
        System.out.println();
    }

    //
    // void | PrintStats()
    //
    public void ModifyAllNodes() {
        ModifyAllNodes(head);
    }
    //
    // void | PrintList(Student)
    //

    private void ModifyAllNodes(Student head) {
        // We need to traverse...so we need a help ptr
        Student helpPtr = head;
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // We add 10 to the id value of each node
            helpPtr.setID(helpPtr.getID() + 10);
            // Step one node over
            helpPtr = helpPtr.getNext();
        }
    }

    //
    // int | sumNodes()
    //
    public int sumNodesFinal() {
        return sumNodesFinal(head);
    }
    //
    // int | sumNodes(BSTnode)
    //

    private int sumNodesFinal(Student head) {
        // We need to traverse...so we need a help ptr
        Student helpPtr = head;
        int sum = 0; // counter
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Increase sum
            sum += helpPtr.getFinalGrade();
            // Step one node over
            helpPtr = helpPtr.getNext();
        }
        return sum;
    }

    //
    // int | countNodes()
    //
    public int countNodes() {
        return countNodes(head);
    }
    //
    // int | countNodes(BSTnode)
    //

    private int countNodes(Student head) {
        // We need to traverse...so we need a help ptr
        Student helpPtr = head;
        int count = 0; // counter
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Increase sum
            count += 1;
            // Step one node over
            helpPtr = helpPtr.getNext();
        }
        return count;
    }

    //
    // int | countNodes()
    //
    public double[] finalGrade(int numStudents) {
        return finalGrade(head,numStudents);
    }
    //
    // int | finalGrade(BSTnode)
    //

    private double[] finalGrade(Student head,int numStudents) {
        // We need to traverse...so we need a help ptr
        int count = 0;
        Student helpPtr = head;
        double[] finalGrade = new double[numStudents]; // counter
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Increase sum
            finalGrade[count] = helpPtr.getFinalGrade();
            // Step one node over
            helpPtr = helpPtr.getNext();
            count++;
        }
        return finalGrade;
    }

    public char[] letterGrade(int numStudents) {
        return letterGrade(head,numStudents);
    }
    //
    // int | finalGrade(BSTnode)
    //

    private char[] letterGrade(Student head,int numStudents) {
        // We need to traverse...so we need a help ptr
        int count = 0;
        Student helpPtr = head;
        char[] letterGrade = new char[numStudents]; // counter
        // Traverse to correct insertion point
        while (helpPtr != null) {
            // Increase sum
            letterGrade[count] = helpPtr.getLetterGrade();
            // Step one node over
            helpPtr = helpPtr.getNext();
            count++;
        }
        return letterGrade;
    }

    //
    // void | insert(int)
    //
    public void insert(String courseNumber, int ID, String firstName, String lastName, int[] examGrades, double finalGrade, char letterGrade) {
        head = insert(head, courseNumber, ID, firstName, lastName, examGrades, finalGrade, letterGrade);
    }
    //
    // Student | insert(Student, value)
    //

    private Student insert(Student head, String courseNumber, int ID, String firstName, String lastName, int[] examGrades, double finalGrade, char letterGrade) {
        // IF there is no list, newNode will be the first node, so just return it
        if (head == null || head.getID() > ID) {
            head = new Student(courseNumber, ID, firstName, lastName, examGrades, finalGrade, letterGrade, head);
            return head;
        } // ELSE, we have a list. Insert the new node at the correct location
        else {
            // We need to traverse to the correct insertion location...so we need a help ptr
            Student helpPtr = head;
            // Traverse to correct insertion point
            while (helpPtr.getNext() != null) {
                if (helpPtr.getNext().getID() > ID) {
                    break; // we found our spot and should break out of the while loop
                }
                helpPtr = helpPtr.getNext();
            }
            // Now make the new node. Set its next to point to the successor node.
            // And then make the predecessor node point to the new node
            Student newNode = new Student(courseNumber, ID, firstName, lastName, examGrades, finalGrade, letterGrade, helpPtr.getNext());
            helpPtr.setNext(newNode);
        }
        // Return head
        return head;
    }

    //
    // void | (int)
    //
    public void delete(int id) {
        head = delete(head, id);
    }
    //
    // Student | delete(Student, value)
    //

    private Student delete(Student head, int id) {
        // We can only delete if the list has nodes (is not empty)
        if (!isEmpty()) {
            // IF the first node (at the head) has the id value we are wanting to delete
            // we found it. Delete by skipping the node and making head point to the next node.
            if (head.getID() == id) {
                head = head.getNext();
            } // ELSE, the id is perhaps somewhere else in the list...so we must traverse and look for it
            else {
                // We need to traverse to find the id we want to delete...so we need a help ptr
                Student helpPtr = head;
                // Traverse to correct deletion point
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getID() == id) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break; // we deleted the value and should break out of the while loop
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
            // return the possibly updated head of the list
            return head;
        }
        return head;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public static double avgElements(double[] array, FSCcourseRoster[] courses) {
        //Find the average
        double sum = 0.00;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];

        }
        return (sum * 1.0) / array.length;
    }

    public static double minValue(double[] nums, FSCcourseRoster[] courses) {
        //Find the minimum
        double min = 10000000.00;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }

        }
        return min;
    }

    public static double maxValue(double[] nums, FSCcourseRoster[] courses) {
        //Find the maximum
        double max = -10000000.00;
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }

        }
        return max;
    }

    public static int numOfGrades(char letter, char[] letterGrade, FSCcourseRoster[] courses) {
        //Find the grade number
        int count = 0;
        for (int i = 0; i < letterGrade.length; i++) {
            if (letterGrade[i] == letter) {
                count++;
            }
        }
        return count;
    }

    public Student getHead() {
        return head;
    }

    public void setHead(Student head) {
        this.head = head;
    }
    
    
}
