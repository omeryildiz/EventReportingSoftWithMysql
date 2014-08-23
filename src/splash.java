
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author OMER
 */
public class splash extends JWindow {
    AbsoluteLayout absoluto;
    AbsoluteConstraints absimage,absbarra;
    ImageIcon image;
    JLabel Jlabel;
    JProgressBar barra;
    
    
    
    public splash(){
     
        absoluto=new AbsoluteLayout();
        absimage=new AbsoluteConstraints(0,0);
        absbarra=new AbsoluteConstraints(0,212);
        Jlabel=new JLabel();
        image=new ImageIcon(this.getClass().getResource("logo.png"));
        Jlabel.setIcon(image);
        barra=new JProgressBar();
        barra.setPreferredSize(new Dimension(919,10));
        this.getContentPane().setLayout(absoluto);
        this.getContentPane().add(Jlabel,absimage);
        this.getContentPane().add(barra,absbarra);
        new Thread()
        {
            public void run(){
                int i=0;
                while(i<101){
                    barra.setValue(i);
                    i+=3;
                    try {
                        sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(splash.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                dispose();
            
            }
        }.start();
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
    }
    
   public static void main(String Args[]){
       new splash();
   } 
}
