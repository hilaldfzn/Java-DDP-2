package Lab09;

import javax.swing.SwingUtilities; 
 
public class MainGUI { 
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                VendingMachineGUI vendingMachine = new VendingMachineGUI(); 
                vendingMachine.setVisible(true); 
            } 
        }); 
    } 
}
