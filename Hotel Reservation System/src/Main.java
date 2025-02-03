import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final String url="jdbc:mysql://localhost:3306/hotel_db";
    private static final String username="root";
    private static final String password="2121912";

    public static void main(String[] args) {



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }


        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("          WELCOME to TAJ ");
            System.out.println("************************************");
            Scanner scanner=new Scanner(System.in);
            while(true)
            {
                System.out.println();

                System.out.println("1. Make a reservation");
                System.out.println("2. Check reservation");
                System.out.println("3. Get Details of the Customer");
                System.out.println("4. Update the reservation");
                System.out.println("5. Delete the reservation");
                System.out.println("0. EXIT");
                System.out.println();
                System.out.print("Choose an option : ");

                if (!scanner.hasNextInt()) {  //  For Non-Integer Inputs
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();  // Consume invalid input
                    continue;
                }

                int choice= scanner.nextInt();

                switch(choice)
                {
                    case 1: ReserveRoom.reserveRoom(connection,scanner);
                        break;
                    case 2: RetrieveReservation.viewReservation(connection,scanner);
                        break;

                    case 3: RetrieveReservation.getDetails(connection,scanner);
                        break;

                    case 4: UpdateReservation.updateDetails(connection,scanner);
                        break;

                    case 5: DeleteReservation.deleteReservation(connection,scanner);
                        break;

                    case 0: Exit.exit();
                            scanner.close();
                            return;
                    default:
                        System.out.println("Invalid choice ");
                        System.out.println("Try again !!");
                }
            }
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(InterruptedException e)
        {
            System.out.println(e.getMessage());
        }


    }

}