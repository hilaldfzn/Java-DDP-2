package Lab09;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
 
public class MoneyInputFrame extends JFrame { 
    private VendingMachineGUI vendingMachine; 
 
    public MoneyInputFrame(VendingMachineGUI vendingMachine) { 
        this.vendingMachine = vendingMachine; 
 
        setTitle("Money Input"); 
        setSize(300, 200); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout(10, 10)); 
 
        JLabel moneyInputLabel = new JLabel("Enter the amount of money:"); 
        JTextField moneyInputField = new JTextField(); 
 
        JButton submitButton = new JButton("Submit"); 
        submitButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                String inputText = moneyInputField.getText(); 
                if (!inputText.isEmpty()) { 
                    double money = Double.parseDouble(inputText); 
                    vendingMachine.updateTotalMoney(money); 
                } 
                dispose(); 
            } 
        }); 
 
        JPanel panel = new JPanel(new BorderLayout(10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        panel.add(moneyInputLabel, BorderLayout.NORTH); 
        panel.add(moneyInputField, BorderLayout.CENTER); 
        panel.add(submitButton, BorderLayout.SOUTH); 
     
        add(panel); 
    }; 
     
    public VendingMachineGUI getVendingMachine() { 
        return vendingMachine; 
    } 
}
