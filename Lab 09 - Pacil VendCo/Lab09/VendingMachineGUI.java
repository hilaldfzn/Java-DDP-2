package Lab09;

import javax.swing.*; 
 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
 
public class VendingMachineGUI extends JFrame { 
    private JLabel displayLabel; 
    private JButton moneyButton; 
    private JButton purchaseButton; 
    private double totalMoney = 0.0; 
 
    public VendingMachineGUI() { 
        setTitle("Vending Machine"); 
        setSize(300, 200); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new BorderLayout(10, 10)); 
 
        displayLabel = new JLabel("Please select an option"); 
        displayLabel.setHorizontalAlignment(SwingConstants.CENTER); 
 
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); 
 
        moneyButton = new JButton("Add Money"); 
        moneyButton.addActionListener(new ButtonClickListener()); 
        buttonPanel.add(moneyButton); 
 
        purchaseButton = new JButton("Purchase Product"); 
        purchaseButton.addActionListener(new ButtonClickListener()); 
        buttonPanel.add(purchaseButton); 
 
        add(displayLabel, BorderLayout.CENTER); 
        add(buttonPanel, BorderLayout.SOUTH); 
    } 
 
    private class ButtonClickListener implements ActionListener { 
        public void actionPerformed(ActionEvent e) { 
            String command = e.getActionCommand(); 
 
            switch (command) { 
                case "Add Money": 
                    openMoneyInputFrame(); 
                    break; 
                case "Purchase Product": 
                    openPurchaseFrame(); 
                    break; 
                default: 
                    break; 
            } 
        } 
    } 
 
    private void openMoneyInputFrame() { 
        MoneyInputFrame moneyInputFrame = new MoneyInputFrame(this); 
        moneyInputFrame.setVisible(true); 
    } 
     
    private void openPurchaseFrame() { 
        PurchaseFrame purchaseFrame = new PurchaseFrame(this); 
        purchaseFrame.setVisible(true); 
    }     
 
    public double getTotalMoney() { 
        return totalMoney; 
    } 
 
    public void setTotalMoney(double totalMoney) { 
        this.totalMoney = totalMoney; 
    } 
 
    public void updateTotalMoney(double money) { 
        totalMoney += money; 
        displayLabel.setText("Total Money: Rp." + totalMoney); 
    } 
}
