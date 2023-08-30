package exceptionhandlings;

import java.util.*;

class EmployeeException extends Exception {
    public EmployeeException(String message) {
        super(message);
    }
}

class DuplicateEmployeeException extends EmployeeException {
    public DuplicateEmployeeException(String message) {
        super(message);
    }
}

class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(message);
    }
}

class Employee {
    private int employeeId;
    private String name;

    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void performTask() {
        // Default implementation
        System.out.println("Performing generic task.");
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name;
    }
}

class FrontDeskEmployee extends Employee {
    public FrontDeskEmployee(int employeeId, String name) {
        super(employeeId, name);
    }

    @Override
    public void performTask() {
        System.out.println("Checking in guests.");
    }
}

class HousekeepingEmployee extends Employee {
    public HousekeepingEmployee(int employeeId, String name) {
        super(employeeId, name);
    }

    @Override
    public void performTask() {
        System.out.println("Cleaning rooms.");
    }
}

class KitchenStaffEmployee extends Employee {
    public KitchenStaffEmployee(int employeeId, String name) {
        super(employeeId, name);
    }

    @Override
    public void performTask() {
        System.out.println("Preparing meals.");
    }
}

class HotelManagementSystem {
    private Map<String, List<Employee>> employeeCategories = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        HotelManagementSystem system = new HotelManagementSystem();
        system.run();
    }

    public void run() {
        while (true) {
            System.out.println("1. Hire Employee");
            System.out.println("2. Assign Task");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    hireEmployee();
                    break;
                case 2:
                    assignTask();
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void hireEmployee() {
        System.out.print("Enter Employee Category (Front Desk/Housekeeping/Kitchen): ");
        String category = scanner.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            Employee employee = createEmployee(category, employeeId, name);
            addEmployeeToCategory(employee, category);
            System.out.println("Employee hired successfully!");
        } catch (EmployeeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void assignTask() {
        System.out.print("Enter Employee Category (Front Desk/Housekeeping/Kitchen): ");
        String category = scanner.nextLine();

        try {
            Employee employee = getAvailableEmployee(category);
            if (employee != null) {
                employee.performTask();
            }
        } catch (EmployeeException | InvalidTaskException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Employee createEmployee(String category, int employeeId, String name) throws EmployeeException {
        switch (category.toLowerCase()) {
            case "front desk":
                return new FrontDeskEmployee(employeeId, name);
            case "housekeeping":
                return new HousekeepingEmployee(employeeId, name);
            case "kitchen":
                return new KitchenStaffEmployee(employeeId, name);
            default:
                throw new EmployeeException("Invalid employee category: " + category);
        }
    }

    private void addEmployeeToCategory(Employee employee, String category) {
        employeeCategories.putIfAbsent(category, new ArrayList<>());
        employeeCategories.get(category).add(employee);
    }

    private Employee getAvailableEmployee(String category) throws EmployeeException, InvalidTaskException {
        List<Employee> employees = employeeCategories.get(category);
        if (employees == null || employees.isEmpty()) {
            throw new EmployeeException("No employees available in " + category);
        }

        for (Employee employee : employees) {
            if (!employee.getName().equalsIgnoreCase("unavailable")) {
                return employee;
            }
        }

        throw new InvalidTaskException("No available employees for the task in " + category);
    }
}
