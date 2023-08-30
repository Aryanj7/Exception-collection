package collection_assesment;
	
	import java.util.*;

	class Employee {
	    private int id;
	    private String name;
	    private double salary;

	    public Employee(int id, String name, double salary) {
	        this.id = id;
	        this.name = name;
	        this.salary = salary;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public double getSalary() {
	        return salary;
	    }

	    @Override
	    public String toString() {
	        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
	    }
	}

	public class EmployeeManagementSystem {
	    public static void main(String[] args) {
	        Map<Integer, Employee> employeeMap = new HashMap<>();

	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("1. Add Employee");
	            System.out.println("2. View Employee Details");
	            System.out.println("3. Search Employee by ID");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter Employee ID: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter Employee Name: ");
	                    String name = scanner.nextLine();
	                    System.out.print("Enter Employee Salary: ");
	                    double salary = scanner.nextDouble();

	                    Employee employee = new Employee(id, name, salary);
	                    employeeMap.put(id, employee);
	                    System.out.println("Employee added successfully!");
	                    break;
	                case 2:
	                    System.out.println("Employee Details:");
	                    for (Employee emp : employeeMap.values()) {
	                        System.out.println(emp);
	                    }
	                    break;
	                case 3:
	                    System.out.print("Enter Employee ID to search: ");
	                    int searchId = scanner.nextInt();
	                    Employee searchResult = employeeMap.get(searchId);
	                    if (searchResult != null) {
	                        System.out.println("Employee found: " + searchResult);
	                    } else {
	                        System.out.println("Employee with ID " + searchId + " not found.");
	                    }
	                    break;
	                case 4:
	                    System.out.println("Exiting the program.");
	                    scanner.close();
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }
	    }
	}


