import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ReserveRoom {
    public static void reserveRoom(Connection connection, Scanner scanner)
    {
         try{
             System.out.print("Enter the guest name : ");
             String Guest_Name =scanner.next();
             scanner.nextLine();

             System.out.print("Enter the room number : ");
             int Room_Number= scanner.nextInt();

             System.out.print("Enter contact details : ");
             int PhoneNo=scanner.nextInt();

             String query ="INSERT INTO reservations(guest_name,room_number,contact_number)" +
                     "VALUES ('"+Guest_Name+"',"+Room_Number+","+PhoneNo+")";

             try(Statement stmt=connection.createStatement())
             {
                 int affectedRows=stmt.executeUpdate(query);
                 if(affectedRows>0)
                 {
                     System.out.println("\n CONGRATULATIONS !");
                     System.out.println("Reservation Successfull !");
                 }
                 else{
                     System.out.println("Reservation Failed !");
                 }
             }
         }catch(SQLException e)
         {
             e.printStackTrace();
         }
    }
}
