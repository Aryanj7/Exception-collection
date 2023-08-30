package collection_assesment;

import java.util.*;

class TeamMember {
    private int memberId;
    private String memberName;

    public TeamMember(int memberId, String memberName) {
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    @Override
    public String toString() {
        return "ID: " + memberId + ", Name: " + memberName;
    }
}

class Project {
    private int projectId;
    private String projectName;
    private Set<TeamMember> teamMembers;

    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.teamMembers = new HashSet<>();
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public Set<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMember(TeamMember member) {
        teamMembers.add(member);
    }

    @Override
    public String toString() {
        StringBuilder members = new StringBuilder();
        for (TeamMember member : teamMembers) {
            members.append(member.toString()).append(", ");
        }
        return "Project ID: " + projectId + ", Name: " + projectName + ", Team Members: " + members;
    }
}

public class ProjectManagementSystem {
    public static void main(String[] args) {
        Map<Integer, Project> projectMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Project");
            System.out.println("2. Assign Team Member");
            System.out.println("3. View Project Details");
            System.out.println("4. List All Projects");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Project ID: ");
                    int projectId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Project Name: ");
                    String projectName = scanner.nextLine();

                    Project project = new Project(projectId, projectName);
                    projectMap.put(projectId, project);
                    System.out.println("Project added successfully!");
                    break;
                case 2:
                    System.out.print("Enter Project ID: ");
                    int assignProjectId = scanner.nextInt();
                    System.out.print("Enter Team Member ID: ");
                    int memberId = scanner.nextInt();

                    Project assignProject = projectMap.get(assignProjectId);
                    TeamMember member = new TeamMember(memberId, "Team Member " + memberId);
                    if (assignProject != null) {
                        assignProject.addTeamMember(member);
                        System.out.println("Team member assigned to project!");
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Project ID to view details: ");
                    int viewProjectId = scanner.nextInt();
                    Project viewProject = projectMap.get(viewProjectId);
                    if (viewProject != null) {
                        System.out.println("Project Details: " + viewProject);
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;
                case 4:
                    System.out.println("List of Projects:");
                    for (Project p : projectMap.values()) {
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
