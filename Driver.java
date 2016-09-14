import java.sql.*;
import java.io.*;
import java.util.*;
//CHRISTIAN GARCIA
/**
 * A sample class that connects to PostgreSQL and runs a simple query.
 *
 * Note: Your database name is your login name, so for login jsmith,
 * you would have:
 *    
 *    getConnection("jdbc:postgresql://cmps182-db.lt.ucsc.edu/jsmith",
 *                  "chalgarc", "password");
 *
 */
public class Driver
{
    public static void main(String[] args) throws ClassNotFoundException,
        FileNotFoundException, IOException, SQLException {
    
    Connection connection;
       Class.forName("org.postgresql.Driver");  //Registering the driver

    connection = DriverManager.getConnection(
            "jdbc:postgresql://cmps182-db.lt.ucsc.edu/chalgarc",
            "chalgarc", "rollback69manager");  //Making the Connection 

    HospitalApplication app = new HospitalApplication(connection);

    int price = 40;
    List<String> names = app.getMedicineNames(price);
    for (String Name : names)
        System.out.println(Name);
    
    String Name = "John Farnsworth";
    String email = "john.farnsworth@customer.org";
    String Address = "41 El Alto Parkway,San Jose";
    ;
    app.addPatient(Name,Address, email);

    connection.close(); //Closing Connection
    }
}
