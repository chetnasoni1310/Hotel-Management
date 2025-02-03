import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RetrieveReservation {
    public static void viewReservation(Connection connection, Scanner scanner) throws SQLException {

        String query="SELECT * FROM reservations ;";

        try(Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query))
        {
            System.out.println("Current Reservations :");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID |      Guest      |   Room Number |    Contact Number    |    Reservation Date     |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while(resultSet.next())
            {
                int id=resultSet.getInt("reservation_id");
                int roomNumber=resultSet.getInt("room_number");
                int PhoneNo=resultSet.getInt("contact_number");
                String guestName=resultSet.getString("guest_name");
                String reservationDate=resultSet.getString("reservation_date");


                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n",
                        id,guestName,roomNumber,PhoneNo,reservationDate);
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        }
    }

    public static void getDetails(Connection connection, Scanner scanner)
    {
      try{
          System.out.print("Enter reservation ID : ");
          int reservationId=scanner.nextInt();
          scanner.nextLine();          //Consume for integer input
          System.out.print("Enter guest name : ");
          String guestName=scanner.nextLine();  //  Trim to remove extra spaces

          String query="SELECT room_number FROM reservations " +
                  "WHERE reservation_id ="+reservationId+
                  " AND LOWER(guest_name) = LOWER('" + guestName + "')";

          try(Statement statement=connection.createStatement();
             ResultSet resultSet=statement.executeQuery(query)
          )
          {
              if(resultSet.next())
              {
                  int roomNumber=resultSet.getInt("room_number");
                  System.out.println("Room number of Reservation ID : "+reservationId+
                          "and Guest : "+guestName+" is : "+roomNumber);
              }
              else{
                  System.out.println("There is no reservation for the given ID and Name." +
                          "\nPlease check your details.");
              }
          }
      }catch(SQLException e)
      {
          e.printStackTrace();
      }

    }
}
