import java.sql.*;
import java.util.*;
//CHRISTIAN GARCIA
//CMPS 182 WINTER 2016

/**
 * The class implements methods of the hospital database
 * interface.
 *
 * All methods of the class receive a Connection object through which all
 * communication to the database should be performed. Note: the
 * Connection object should not be closed by any method.
 *
 * Also, no method should throw any exceptions. In particular, in case
 * that an error occurs in the database, then the method should print an
 * error message and call System.exit(-1);
 */
public class HospitalApplication {

    private Connection connection;
    
    /*
     * Constructor
     */
    public HospitalApplication(Connection connection) {
        this.connection = connection;
    }

    /**
     * Return a list of medicines whose price is no greater than some specified price
     * 
     */
    public List<String> getMedicineNames(int Price) {
        List<String> result = new LinkedList<String>();
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM h_medicines");
            while (rs.next())
            {
                if (Price>rs.getInt(3)){//the third attribute from this query is the price
                result.add("Medicine Name:"+rs.getString(2)+"Price:"+rs.getString(3));//adds to the result string
                }
               
            } rs.close();//closes connection and result set
            st.close();
        }
        catch (java.sql.SQLException e) {
            // you may want to do something useful here
            // maybe even throw new RuntimException();
        }
        //if Price
        
        return result;  
    }

    /**
     * Add a patient to the database. If the patient's name
     * already exists in the database, return the patient’s id. Otherwise,
     * add the patient to the database, and return the new patient id.
     * 
     * Adding a patient (and address, if necessary) should be done
     * within a transaction. The isolation level should be Serializable.
     */
    public int addPatient(String Name,String Address,String email) {
            int patientId = 0;
        
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM h_patients");
            while (rs.next())//checks for duplicates
            {
                String temp = rs.getString(2);
                String[] tokens = temp.split(" ");
                //System.out.println("'"+tokens[0]+"'");
                temp= tokens[0]+" "+tokens[1];
                //System.out.println("'"+temp+"'");
                //EVERYTHING ABOVE HERE BEFORE THE WHILE LOOP gets "string     " and turns it into "string"
                if(Name.equals(temp)){//if the name inputed = the "string" then do this and break the while loop
                    patientId=rs.getInt(1);
                    //if the name exists patient id gets updated
                    break;
                }
            
            }rs.close();
            st.close();
        }
        catch (java.sql.SQLException e) {
            System.out.println("first query not valid");
        }
        if (patientId==0){
            try{
            Statement ms = connection.createStatement();
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO h_patients(name,address,email) VALUES (?, ?, ?)");
                //Puts the values into the ? ? ?
                stmt.setString(1, Name);
                stmt.setString(2, Address);
                stmt.setString(3, email);
                
                stmt.executeUpdate();
            }
            catch(java.sql.SQLException e){
                System.out.println("query not valid");
            }
        }
        return patientId;//if duplicate will return where the duplicate is found else return 0
    }

};
