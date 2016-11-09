package keylogger;
/**
 *
 * @author Reda ben
 */
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CNX {
    public java.sql.Connection con;
    public CNX(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (java.sql.Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/keylog","root","");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CNX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
