/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package clearoutqs;

/**
 *
 * @author rrodriguez
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CommandCall;
import java.sql.SQLException;

/**
 *
 * @author rrodriguez
 */
public class DBConnect {
        public static Statement st;
        public static Connection con;
        public static String host = "10.10.2.38";
        public static String user = "";
        public static String pwd  = "";   
   
        public static Statement Network() {
        
        try {
               Class.forName("com.ibm.as400.access.AS400JDBCDriver");
               con = DriverManager.getConnection("jdbc:as400://" + host, user, pwd);
               st = con.createStatement();
                    
           } catch(ClassNotFoundException | SQLException e){
                  System.out.println(e.toString());
           }
        return st;
    }
        
     
        
}
