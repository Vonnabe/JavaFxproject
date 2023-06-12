
package loginapp;


import java.sql.Connection;
import java.sql.DriverManager;



 //This is a Database connection of the cleanera.sql
public class DatabaseConnection {
    public Connection databaseLink;
    
    //storing the links into variables
    public Connection getConnection(){
  //  String databaseName="cleans";
  //  String databaseUser="root";
   // String databasePassword="";
   // String url="jdbc:mysql://localhost:3306/"+databaseName;
    
    
    //Input of the link for the database connection
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    databaseLink=DriverManager.getConnection("jdbc:mysql://localhost:3306/cleaneraz", "root", "");
    
    }catch(Exception e){
    e.printStackTrace();
    }
    return databaseLink;
    }


}
