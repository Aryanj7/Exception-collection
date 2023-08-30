package collection_assesment;

import java.util.*;

class Patient {
    private int id;
    private String name;
    private int age;
    private String diagnosis;

    public Patient(int id, String name, int age, String diagnosis) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Diagnosis: " + diagnosis;
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Map<Integer, Patient> patientMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient Details");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. List All Patients");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Patient Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Patient Diagnosis: ");
                    String diagnosis = scanner.nextLine();

                    Patient patient = new Patient(id, name, age, diagnosis);
                    patientMap.put(id, patient);
                    System.out.println("Patient added successfully!");
                    break;
                case 2:
                    System.out.println("Patient Details:");
                    for (Patient p : patientMap.values()) {
                        System.out.println(p);
                    }
                    break;
                case 3:
                    System.out.print("Enter Patient ID to search: ");
                    int searchId = scanner.nextInt();
                    Patient searchResult = patientMap.get(searchId);
                    if (searchResult != null) {
                        System.out.println("Patient found: " + searchResult);
                    } else {
                        System.out.println("Patient with ID " + searchId + " not found.");
                    }
                    break;
                case 4:
                    System.out.println("List of Patients:");
                    for (Patient p : patientMap.values()) {
                        System.out.println(p);
                    }
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}


