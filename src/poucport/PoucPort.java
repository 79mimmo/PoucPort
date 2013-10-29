/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poucport;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
/**
 *
 * @author Mimmo
 */
public class PoucPort extends JFrame {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    JTextField TFHCM1=new JTextField();
    JTextField TFHCM2=new JTextField();
    JTextField TFHCM3=new JTextField();
    JTextField TFNETIP1=new JTextField();
    JTextField TFNETIP2=new JTextField();
    JTextField TFNETIP3=new JTextField();
    JTextField TFNETIP4=new JTextField();
    MenuItem info=new MenuItem("Info");
    MenuItem esci=new MenuItem("Esci");
    Menu file= new Menu("File");
    URL urlPort;
    URL urlIco;
    URL urlFreccia;
    URL urlClipboard;
    String regex;
    java.awt.datatransfer.Clipboard clipboard;
    public PoucPort() {
        super();
        //Carico la favicon
        urlPort = this.getClass().getResource("/images/favicon.gif");
        ImageIcon icona=new ImageIcon(urlPort);
        this.setIconImage(icona.getImage());
        //Carico immagine freccia
        urlFreccia = this.getClass().getResource("/images/freccia_avanti.png");
        ImageIcon frecciaAvanti=new ImageIcon(urlFreccia);
        //this.setIconImage(icona.getImage());
        urlClipboard = this.getClass().getResource("/images/clipboard2.png");
        ImageIcon clipBoard=new ImageIcon(urlClipboard);
               
        
        this.setTitle("PoucPort");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        JLabel freccia=new JLabel(frecciaAvanti);
        JLabel clipB=new JLabel(clipBoard);
        freccia.setToolTipText("Da HCM a NETIP..");
        clipB.setToolTipText("ConvertiClipboard");
        freccia.addMouseListener(new MioMouseListener());
        clipB.addMouseListener(new MouseListenerForClipboard());
        TFHCM1.setColumns(1);
        
        TFHCM2.setColumns(2);
        TFHCM3.setColumns(3);
        TFNETIP1.setColumns(1);
        TFNETIP2.setColumns(2);
        TFNETIP3.setColumns(1);
        TFNETIP4.setColumns(2);
        add(TFHCM1);
        add(TFHCM2);
        add(TFHCM3);
        add(freccia);
        add(clipB);
        add(TFNETIP1);
        add(TFNETIP2);
        add(TFNETIP3);
        add(TFNETIP4);
        TFNETIP1.setEditable(false);
        TFNETIP2.setEditable(false);
        TFNETIP3.setEditable(false);
        TFNETIP4.setEditable(false);
        this.pack();
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        
        addWindowListener(new FinestramenuWindowListener());
        
    }

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) {
        // TODO code application logic here
        new PoucPort();
    }
    
    class FinestramenuWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {
            
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
        // TODO code application logic here
    }
public class MioMouseListener implements MouseListener{

        public void mouseClicked(MouseEvent e) {

            
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            clipboard = toolkit.getSystemClipboard();
            Transferable clipboardContent = clipboard.getContents(null);
            TFNETIP1.setText("0"+TFHCM1.getText());
            TFNETIP2.setText(TFHCM2.getText());
            Integer ics;
            boolean okInt=true;
            while(TFHCM3.getText().length()>3){
                int lun=TFHCM3.getText().length();
                try {
                    TFHCM3.setText(TFHCM3.getText(0,lun-1));
                } catch (BadLocationException ex) {
                    TFHCM3.setText("");
                }
            }
            try{
            ics = Integer.parseInt(TFHCM3.getText());}
            catch(NumberFormatException nfe){
                okInt=false;
                TFNETIP1.setText("");
                TFNETIP2.setText("");
                TFNETIP3.setText("");
                TFNETIP4.setText("");
            //allora questa stringa non è un numero e successivamente x sarà null
            }

            if(okInt){
                Integer numero=new Integer(TFHCM3.getText());

                if (numero>=0&&numero<=125){
                Integer resto=numero%63+1;
                Integer quoto=numero/63;
                TFNETIP3.setText("0"+quoto.toString());
                TFNETIP4.setText(resto.toString());
                StringSelection contents = new StringSelection(TFNETIP1.getText()+"*"+TFNETIP2.getText()+"*"+TFNETIP3.getText()+"*"+TFNETIP4.getText());
                clipboard.setContents(contents, null);
                }
                else{
                    
                }
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        }
}

public class MouseListenerForClipboard implements MouseListener{

        public void mouseClicked(MouseEvent e) {

            
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            clipboard = toolkit.getSystemClipboard();
            Transferable clipboardContent = clipboard.getContents(null);
if(clipboardContent!=null && (clipboardContent.isDataFlavorSupported (DataFlavor.stringFlavor))) {
  try {
        String text = (String) clipboardContent.getTransferData(DataFlavor.stringFlavor);
        System.out.println(text);
        regex="[0-1]/[0-3]{1,2}/[0-3][0-9]/[0-9]{1,3}";
        Pattern pattern = Pattern.compile(regex);
  Matcher matcher = pattern.matcher(text);

  if (matcher.matches()){
    System.out.println("Formalmente ok");
    String[] stringaHCM=text.split("/");
    if (Integer.parseInt(
        stringaHCM[3])<=125&&Integer.parseInt(
        stringaHCM[3])>=0){
        Integer subrack=Integer.parseInt(stringaHCM[1]);
        TFNETIP1.setText("0"+subrack.toString());
        TFHCM1.setText("0"+subrack.toString());
        Integer slot=Integer.parseInt(stringaHCM[2]);
        TFNETIP2.setText(slot.toString());
        TFHCM2.setText(slot.toString());
        Integer numero=Integer.parseInt(stringaHCM[3]);
        TFHCM3.setText(numero.toString());
        numero=numero/63;
        
        TFNETIP3.setText("0"+numero.toString());
        numero=Integer.parseInt(stringaHCM[3]);
        numero=numero%63+1;
        String porta="";
        if (numero<10) porta="0"+numero.toString();
        else porta=numero.toString();
        TFNETIP4.setText(porta);
        StringSelection contents = new StringSelection(TFNETIP1.getText()+"*"+TFNETIP2.getText()+"*"+TFNETIP3.getText()+"*"+TFNETIP4.getText());
        clipboard.setContents(contents, null);
        //clipboard.setContents(stringaHCM[3], null);
    }
  }
    
  else
    System.out.println("Non ok");
  }catch(Exception ex) {
    
  }
}
            
        }
}
    
    
}
