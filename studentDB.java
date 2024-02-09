import java.io.IOException;
import java.util.Scanner;

public class demo15 {
    public static void main() throws InterruptedException, IOException {
        final Scanner SCANNER = new Scanner(System.in);
        String nameList = "";
        int count = 0;

        main:
        while (true) {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
            System.out.println("============================");
            System.out.println("   WELCOME TO STUDENT DB");
            System.out.println("============================");

            System.out.println("1. Add New Student ");
            System.out.println("2. Delete Student");
            System.out.println("3. View All Student");
            System.out.println("4. Exit ");

            System.out.println();
            System.out.print("Enter Your Command : ");

            switch (SCANNER.nextInt()) {
                case 1 -> {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                    System.out.println("============================");
                    System.out.println("     ADD NEW STUDENT ");
                    System.out.println("============================");

                    SCANNER.skip("\n");
                    addStudent:
                    while (true) {

                        System.out.print("Enter Student Name : ");
                        String name = SCANNER.nextLine();
                        if (name.isBlank() == true || name.isEmpty() == true)
                            System.out.println("\033[31m Invalid name!\033[0m");
                        else {
                            count++;
                            nameList += (name + ",");
                            System.out.println("Added Successfully");
                            System.out.print("Do you want to add another student (y\\n) ? ");
                            String value = SCANNER.next();
                            SCANNER.skip("\n");
                            if ("y".equalsIgnoreCase(value)) continue addStudent;
                            else continue main;
                        }

                    }

                }
                case 2 -> {

                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                    System.out.println("============================");
                    System.out.println("     DELETE STUDENT ");
                    System.out.println("============================");

                    SCANNER.skip("\n");
                    deleteStudent:
                    while (true) {
                        System.out.print("Enter Student Name to Delete ");
                        String deleteName = SCANNER.nextLine();
                        if(deleteName.isEmpty()!=true&&deleteName.isBlank()!=true){
                            int commaIndex=0;
                            boolean isExist=false;
                            for (int i = 0; i < count; i++) {
                                String name=nameList.substring(commaIndex,nameList.indexOf(",",commaIndex));
                                commaIndex=nameList.indexOf(",",commaIndex)+1;
                                if(name.equalsIgnoreCase(deleteName)==true){
                                    isExist=true;
                                    --count;
                                    nameList = nameList.replace((deleteName + ","), "");
                                    System.out.println("Successfully deleted ");
                                    System.out.print("Do you want to delete another student (y\\n)? ");
                                    String value = SCANNER.next();
                                    SCANNER.skip("\n");
                                    if ("y".equalsIgnoreCase(value)) continue deleteStudent;
                                    else continue main;
                                }

                            }
                            if(isExist==false){
                                System.out.println("Not Found");
                                System.out.print("Do you want to try again (y\\n) ?");
                                String value = SCANNER.next();
                                SCANNER.skip("\n");
                                if ("y".equalsIgnoreCase(value)) continue deleteStudent;
                                else continue main;
                            }

                        }else{
                            System.out.println("INVALID INPUT");
                        }


                    }

                }
                case 3 -> {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                    System.out.println("============================");
                    System.out.println("     VIEW STUDENT DETAILS ");
                    System.out.println("============================");
                    int commaIndex = 0;
                    for (int i = 0; i < count; i++) {
                        String printName = nameList.substring(commaIndex, nameList.indexOf(",", commaIndex));
                        commaIndex = nameList.indexOf(",", commaIndex) + 1;
                        System.out.println(printName);

                    }
                    System.out.print("Do you want to exit (y\\n)");
                    String value = SCANNER.next();
                    SCANNER.skip("\n");
                    if ("y".equalsIgnoreCase("y") == true) continue main;
                    else System.exit(0);

                }
                case 4 -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid screen number");
                }
            }
        }

    }
}
