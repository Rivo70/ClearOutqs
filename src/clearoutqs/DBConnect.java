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

/**
 *
 * @author rrodriguez
 */
public class DBConnect {
        public static Statement st;
        public static Connection con;
        public static String host = "10.10.2.38";
        public static String user = "lawpgm";
        public static String pwd  = "lawpgm";   
   
        public static Statement Network() {
        
        try {
               Class.forName("com.ibm.as400.access.AS400JDBCDriver");
               con = DriverManager.getConnection("jdbc:as400://" + host, user, pwd);
               st = con.createStatement();
                    
           } catch(Exception e){
                  System.out.println(e.toString());
           }
        return st;
    }
}
