import java.io.IOException;
import java.util.Scanner;

public class demo17 {
    void main() throws IOException, InterruptedException {

        final Scanner SCANNER = new Scanner(System.in);
        String idList = "";
        int studentId = 0;
        int maxTotalMarks = 0, minTotalMarks = 0;
        String bestStudent = "", worstStudent = "";
        int numberOfStudent = 0;
        String searchList = "", dataList = "";
        final String GREEN = "\033[32m";
        final String RED = "\033[31m";
        final String RESET = "\033[0m";
        main:
        while (true) {
            new ProcessBuilder("clear").inheritIO().start().waitFor();

            System.out.println("================================");
            System.out.println("          STUDENT DB");
            System.out.println("================================");
            System.out.println("1. Add New Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. View All Student");
            System.out.println("5. Exit");
            System.out.print("Enter your command : ");

            switch (SCANNER.nextInt()) {
                case 1 -> {
                    addNewStudent:
                    while (true) {
                        new ProcessBuilder("clear").inheritIO().start().waitFor();
                        System.out.println("================================");
                        System.out.println("        Add new student");
                        System.out.println("================================");
                        // generate the student ID
                        studentId++;
                        idList += (String.format("S%03d", studentId) + ",");
                        SCANNER.skip("\n");
                        addName:
                        while (true) {
                            System.out.print("Enter Student name : ");
                            String name = SCANNER.nextLine();
                            if (name.isBlank() != true && name.isEmpty() != true) {
                                numberOfStudent++;
                                marks:
                                while (true) {
                                    System.out.print("Enter Programming Fundamental Marks : ");
                                    int PFMarks = SCANNER.nextInt();
                                    if (PFMarks < 0 || PFMarks > 100) {
                                        System.out.println("\033[31mInvalid Marks\033[0m");
                                        continue marks;
                                    } else {
                                        os:
                                        while (true) {
                                            System.out.print("Enter Operating System Marks : ");
                                            int OSMarks = SCANNER.nextInt();
                                            if (OSMarks < 0 || OSMarks > 100) {
                                                System.out.println("\033[31m Invalid Marks \033[0m");
                                                continue os;
                                            } else {
                                                char status;
                                                int totalMarks = OSMarks + PFMarks;
                                                double avg = totalMarks / 2.;
                                                //find status of marks
                                                if (avg >= 75) status = 'A';
                                                else if (avg >= 65) status = 'B';
                                                else if (avg >= 55) status = 'C';
                                                else if (avg >= 35) status = 'S';
                                                else status = 'F';

                                                if (numberOfStudent == 1) {
                                                    bestStudent = String.format("S%03d", studentId);
                                                    worstStudent = String.format("S%03d", studentId);
                                                    maxTotalMarks = totalMarks;
                                                    minTotalMarks = totalMarks;
                                                } else {
                                                    // find best student
                                                    if (maxTotalMarks < totalMarks) {
                                                        maxTotalMarks = totalMarks;
                                                        bestStudent = String.format("S%03d", studentId);
                                                    }
                                                    //find worst student
                                                    if (minTotalMarks > totalMarks) {
                                                        minTotalMarks = totalMarks;
                                                        worstStudent = String.format("S%03d", studentId);
                                                    }
                                                }

                                                System.out.print("Do you want to add another student (y\\n) ? ");
                                                idList += String.format("S%03d", studentId) + ",";
                                                dataList += String.format("S%03d", studentId) + "-" + name + "-" + PFMarks + "-" + OSMarks + "-" + totalMarks + "-" + avg + "-" + status + "-,";
                                                searchList += String.format("%-30s:%s\n", "Name", name) + String.format("%-30s:%s\n", "PF Marks", PFMarks) + String.format("%-30s:%s\n", "OS Marks", OSMarks)
                                                        + String.format("%-30s:%s\n", "Total", totalMarks) + String.format("%-30s:%s\n", "Average Marks", avg) + String.format("%-30s:%s\n", "Status", status) + "-";
                                                if ("y".equalsIgnoreCase(SCANNER.next())) continue addNewStudent;
                                                else continue main;
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("\033[31m Invalid name \033[0m");
                                continue addName;
                            }
                        }
                    }
                }
                case 2 -> {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                    System.out.println("================================");
                    System.out.println("          DELETE STUDENT");
                    System.out.println("================================");
                    SCANNER.skip("\n");

                    deleteStudent:
                    while (true) {
                        if (numberOfStudent == 0) {
                            System.out.println("\033[31m  NO AVAILABLE DATA \033[0m");
                            System.out.printf("Enter any key to exit ");
                            String value = SCANNER.nextLine();
                            if (value.isEmpty() == true || value.isBlank() == true || value.isBlank() != true)
                                continue main;

                        } else {
                            System.out.print("Enter Student ID to Delete : ");
                            String id = SCANNER.nextLine();
                            if (id.isBlank() == true || id.isEmpty() == true) {
                                System.out.println("\033[31m Invalid input\033[0m");
                                continue deleteStudent;
                            } else {

                                int commaIndex = 0;
                                int hypenIndex = 0;
                                boolean isExist = false;
                                String idEntered = id.trim().toUpperCase();

                                for (int i = 0; i < numberOfStudent; i++) {

                                    String dataSelected = dataList.substring(commaIndex, dataList.indexOf(",", commaIndex));
                                    String idSelected = dataSelected.substring(0, dataSelected.indexOf("-", 0));

                                    if (idSelected.equalsIgnoreCase(idEntered)) {
                                        String searchDataSelected = searchList.substring(hypenIndex, searchList.indexOf("-", hypenIndex));
                                        isExist = true;
                                        // if (dataSelected.contains(id.trim().toUpperCase()) == true)
                                        idList = idList.replace(idSelected + ",", "");
                                        dataList = dataList.replace(dataSelected + ",", "");
                                        searchList = searchList.replace(searchDataSelected + "-", "");
                                        System.out.println("\033[32mSuccessfully deleted \033[0m");
                                        --numberOfStudent;
                                        if (numberOfStudent > 0)
                                            System.out.print("Do you want to delete another Student (y\\n) ? ");
                                        else System.out.printf("Do you want to exit (y\\n) ?");
                                        String value = SCANNER.next();
                                        if ("y".equalsIgnoreCase(value)) continue deleteStudent;
                                        else continue main;
                                    }

                                    commaIndex = dataList.indexOf(",", commaIndex) + 1;
                                    hypenIndex = searchList.indexOf("-", hypenIndex) + 1;
                                }
                                if (isExist != true) {
                                    System.out.println("NOT FOUND");
                                    System.out.print("Do you want to try again ! (y\\n) ");
                                    if ("y".equalsIgnoreCase(SCANNER.nextLine())) continue deleteStudent;
                                    else continue main;
                                }

                            }
                        }
                    }

                }
                case 3 -> {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                    System.out.println("================================");
                    System.out.println("          SEARCH STUDENT");
                    System.out.println("================================");
                    SCANNER.skip("\n");
                    if (numberOfStudent == 0) {
                        System.out.println("\033[31m    NO AVAILABLE DATA\033[0m");
                        System.out.print("Enter any key to exit ");
                        String value = SCANNER.nextLine();
                        if (value.isEmpty() == true || value.isBlank() == true || value.isBlank() != true)
                            continue main;
                    } else {
                        searchstudent:
                        while (true) {
                            System.out.printf("%-30s:", "Enter Student ID ");
                            String id = SCANNER.nextLine();
                            if (id.isBlank() == true || id.isEmpty() == true) {
                                System.out.println("\033[31mInvalid Input\033[0m");
                                continue searchstudent;
                            } else {
                                int hypenIndex = 0;
                                int commaIndex = 0;
                                boolean isExsist = false;
                                for (int i = 0; i < numberOfStudent; i++) {
                                    String idSelected = idList.substring(commaIndex, idList.indexOf(",", commaIndex));
                                    if (idSelected.equalsIgnoreCase(id)) {
                                        isExsist = true;
                                        String dataSelected = searchList.substring(hypenIndex, searchList.indexOf("-", hypenIndex));
                                        System.out.println(dataSelected);
                                        System.out.print("Do you want to search again (y\\n) ?");
                                        if ("y".equalsIgnoreCase(SCANNER.next()) == true) {
                                            SCANNER.skip("\n");
                                            continue searchstudent;
                                        } else continue main;
                                    }
                                    commaIndex = idList.indexOf(",", commaIndex) + 1;
                                    hypenIndex = searchList.indexOf("-", hypenIndex) + 1;
                                }

                                if (isExsist != true) {
                                    System.out.println("Not found");
                                    System.out.printf("Do you want to try again (y\\n) ?");
                                    if ("y".equalsIgnoreCase(SCANNER.next())) {
                                        SCANNER.skip("\n");
                                        continue searchstudent;
                                    } else continue main;
                                }
                            }

                        }
                    }
                }
                case 4 -> {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                    System.out.println("=".repeat(103));
                    System.out.printf("%55s\n", "VIEW ALL STUDENT");
                    System.out.println("=".repeat(103));
                    SCANNER.skip("\n");
                    while (true) {
                        if (numberOfStudent == 0) {
                            System.out.printf("\033[31m%50s\033[0m\n", "NO DATA AVAILABLE");

                        } else {

                            System.out.println("-".repeat(103));
                            System.out.printf("|%-5s|%-15s|%-15s|%-15s|%-15s|%-15s|%-15s|\n", "ID", "NAME", "PF MARKS", "OS MARKS", "TOTAL", "AVERAGE", "STATUS");
                            System.out.println("-".repeat(103));
                            int commaIndex = 0;

                            for (int i = 0; i < numberOfStudent; i++) {
                                boolean isBest = false, isWorst = false;
                                String studentdata = dataList.substring(commaIndex, dataList.indexOf(",", commaIndex));
                                int hypenInedx = 0;
                                String printStudentData = "";
                                for (int j = 0; j < 7; j++) {
                                    String data = studentdata.substring(hypenInedx, studentdata.indexOf("-", hypenInedx));

                                    if (j == 0) {
                                        if (data.equals(bestStudent)) {
                                            isBest = true;
                                            System.out.printf("|%s%-5s%s|", GREEN, data, RESET);
                                        } else if (data.equals(worstStudent)) {
                                            isWorst = true;
                                            System.out.printf("|%s%-5s%s|", RED, data, RESET);
                                        } else {
                                            System.out.printf("|%-5s|", data);

                                        }
                                    } else {
                                        if (isBest == true) {
                                            System.out.printf("%s%-15s%s|", GREEN, data, RESET);
                                        } else if (isWorst == true) System.out.printf("%s%-15s%s|", RED, data, RESET);
                                        else System.out.printf("%-15s|", data);

                                    }
                                    hypenInedx = studentdata.indexOf("-", hypenInedx) + 1;
                                }
                                commaIndex = dataList.indexOf(",", commaIndex) + 1;
                                System.out.println();
                            }
                            System.out.println("-".repeat(103));


                        }
                        System.out.print("Press any key to exit");
                        String val = SCANNER.nextLine();
                        if (val.isBlank() == true || val.isEmpty() == true || val.contains("\n") == true)
                            continue main;
                    }


                }
                case 5 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("\033[41m Enter valid input\033[0m");
                }
            }

        }

    }
}
