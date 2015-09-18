/**
 *
 * @author Rafael R Rodriguez
 * 
 */


package clearoutqs;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.OutputQueue;
import com.ibm.as400.access.PrintParameterList;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClearOutqs {

    /**
     * @param args the command line arguments
     * @throws com.ibm.as400.access.AS400SecurityException
     */
    public static void main(String[] args) throws AS400SecurityException {
        
        
        Statement stmt = DBConnect.Network();
        
        OutputQueue outq = new OutputQueue();
        PrintParameterList outqList = new PrintParameterList();
        outqList.setParameter(2, "LAW91TOUTQ");
        //outqList.getStringParameter(2);
        AS400 system400 = new AS400("10.10.2.38", "", "");
        CommandCall cmdObj = null;
                
        try {
            System.out.println("Connecting....");
            system400.connectService(AS400.COMMAND);
            System.out.println("Creating CommandCall object....");
            LocalDate currentDate = LocalDate.now();
            
            
            
            runCmd(cmdObj, "CLNOUTQ OUTQ(RAFAELQ) DATE(" + currentDate + ")");
            
//            outq.clear(outqList);
            
               
        } catch (AS400SecurityException | IOException ex) {
            Logger.getLogger(ClearOutqs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } //End of Main
    
    public static boolean runCmd(CommandCall cmdObj, String cmdString)
        {
            boolean cmdOK = false;
            try
            {
                System.out.println("Calling command " + cmdString + "...");
                cmdOK = cmdObj.run(cmdString);
                System.out.println("Command returned, Result = " + cmdOK);
            }catch (AS400SecurityException | ErrorCompletingRequestException | IOException | InterruptedException | PropertyVetoException exc)
            {
                AS400Message msgs[] = cmdObj.getMessageList();
                if (msgs != null)
                {
                    for (AS400Message msg : msgs)
                    {
                        System.out.println("Message: " + msg.toString() );
                    }
                    
                }
            }
            System.out.println();
            return cmdOK;
        }
    
}
