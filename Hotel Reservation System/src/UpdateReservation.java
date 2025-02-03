import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateReservation {

    public static void updateDetails(Connection connection, Scanner scanner)
    {
        try{
            System.out.print("Enter reservation ID to update: ");
            int reservationID=scanner.nextInt();
            scanner.nextLine();

            if(!reservationExists(connection,reservationID))
            {
                System.out.println("There is no such reservation");
                return ;
            }

            System.out.println("Enter the new name of guest : ");
            String newName= scanner.nextLine();
            System.out.println("Enter the new room number: ");
            int newRoomNumber=scanner.nextInt();
            System.out.println("Enter the updated contact details : ");
            int Phoneno= scanner.nextInt();

            String query="UPDATE reservations SET guest_name = '"+newName+"',"+
                    "room_number= "+newRoomNumber+","+"contact_number = "+Phoneno+" where " +
                    "reservation_id = "+reservationID;

            try(Statement statement= connection.createStatement())
            {
                int affectedRows=statement.executeUpdate(query);

                if(affectedRows>0)
                {
                    System.out.println("Details Updated Successfully !");
                }
                else{
                    System.out.println("Updation Failed");
                }
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    private static boolean reservationExists(Connection connection, int reservationId) {
        try {
            String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                return resultSet.next(); // If there's a result, the reservation exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle database errors as needed
        }
    }
}
