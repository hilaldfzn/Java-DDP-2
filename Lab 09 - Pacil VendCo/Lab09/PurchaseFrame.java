package Lab09;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
 
import javax.swing.event.DocumentEvent; 
import javax.swing.event.DocumentListener; 
 
public class PurchaseFrame extends JFrame { 
    private VendingMachineGUI vendingMachine; 
    private JComboBox<String> productComboBox; 
    private JTextField quantityTextField; 
    private JTextField priceTextField; 
    private JTextField totalPriceTextField; 
 
    public PurchaseFrame(VendingMachineGUI vendingMachine) { 
        this.vendingMachine = vendingMachine; 
        setTitle("Purchase Product"); 
        setSize(400, 300); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLayout(new BorderLayout(10, 10)); 
 
        productComboBox = new JComboBox<>(getProductNames()); 
 
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); 
        JLabel productLabel = new JLabel("Product:"); 
        JLabel quantityLabel = new JLabel("Quantity:"); 
        quantityTextField = new JTextField(); 
        JLabel priceLabel = new JLabel("Price:"); 
        priceTextField = new JTextField(); 
        priceTextField.setEditable(false); 
        JLabel totalPriceLabel = new JLabel("Total Price:"); 
        totalPriceTextField = new JTextField(); 
        totalPriceTextField.setEditable(false); 
 
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        inputPanel.add(productLabel); 
        inputPanel.add(productComboBox); 
        inputPanel.add(quantityLabel); 
        inputPanel.add(quantityTextField); 
        inputPanel.add(priceLabel); 
        inputPanel.add(priceTextField); 
        inputPanel.add(totalPriceLabel); 
        inputPanel.add(totalPriceTextField); 
 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        JButton purchaseButton = new JButton("Purchase"); 
        purchaseButton.setPreferredSize(new Dimension(100, 30)); 
        buttonPanel.add(purchaseButton); 
 
        add(inputPanel, BorderLayout.CENTER); 
        add(buttonPanel, BorderLayout.SOUTH); 
 
        productComboBox.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                String selectedProduct = (String) productComboBox.getSelectedItem(); 
                double productPrice = getProductPrice(selectedProduct); 
                priceTextField.setText("Rp." + productPrice); 
                updateTotalPrice(); 
            } 
        }); 
 
        purchaseButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                String selectedProduct = (String) productComboBox.getSelectedItem(); 
                double productPrice = getProductPrice(selectedProduct); 
 
                int quantity; 
                try { 
                    quantity = Integer.parseInt(quantityTextField.getText()); 
                } catch (NumberFormatException err) { 
                    JOptionPane.showMessageDialog(PurchaseFrame.this, 
                            "Maaf, jumlah barang yang Anda masukkan tidak valid!", 
                            "Info", JOptionPane.ERROR_MESSAGE); 
                    return; 
                } 
 
                if (quantity <= 0) { 
                    JOptionPane.showMessageDialog(PurchaseFrame.this, 
                            "Maaf, jumlah barang yang Anda masukkan tidak valid!", 
                            "Info", JOptionPane.ERROR_MESSAGE); 
                    return; 
                } 
 
                double totalItemPrice = productPrice * quantity; 
                double totalPurchasePrice = totalItemPrice; 
                totalPriceTextField.setText("Rp." + totalItemPrice); 
 
                if (vendingMachine.getTotalMoney() >= totalPurchasePrice) { 
                    vendingMachine.updateTotalMoney(-totalPurchasePrice); 
 
                    JOptionPane.showMessageDialog(PurchaseFrame.this, 
                            "Berhasil! Kembalian Anda sebesar Rp." + vendingMachine.getTotalMoney(), 
                            "Info", JOptionPane.INFORMATION_MESSAGE); 
 
                    quantityTextField.setText(""); 
                    totalPriceTextField.setText(""); 
                } else { 
                    JOptionPane.showMessageDialog(PurchaseFrame.this, 
                            "Maaf, Uang Anda tidak cukup!", 
                            "Info", JOptionPane.ERROR_MESSAGE); 
                } 
            } 
        }); 
 
        quantityTextField.addKeyListener(new KeyAdapter() { 
            public void keyTyped(KeyEvent e) { 
                char c = e.getKeyChar(); 
                if (!Character.isDigit(c)) { 
                    e.consume(); 
                } 
            } 
        }); 
 
        quantityTextField.getDocument().addDocumentListener(new DocumentListener() { 
            @Override 
            public void insertUpdate(DocumentEvent e) { 
                updateTotalPrice(); 
            } 
 
            @Override 
            public void removeUpdate(DocumentEvent e) { 
                updateTotalPrice(); 
            } 
 
            @Override 
            public void changedUpdate(DocumentEvent e) { 
                updateTotalPrice(); 
            } 
        }); 
 
        String selectedProduct = (String) productComboBox.getSelectedItem(); 
        double productPrice = getProductPrice(selectedProduct); 
        priceTextField.setText("Rp." + productPrice); 
        updateTotalPrice(); 
    } 
 
    private void updateTotalPrice() { 
        String quantityText = quantityTextField.getText(); 
        if (!quantityText.isEmpty()) { 
            int quantity = Integer.parseInt(quantityText); 
            double productPrice = getProductPrice((String) productComboBox.getSelectedItem()); 
            double totalPrice = productPrice * quantity; 
            totalPriceTextField.setText("Rp." + totalPrice); 
        } else { 
            totalPriceTextField.setText(""); 
        } 
    } 
 
    private String[] getProductNames() { 
        String[] productNames = new String[5]; 
        productNames[0] = "Akua"; 
        productNames[1] = "Fruti Apel"; 
        productNames[2] = "Palpi Jeruk"; 
        productNames[3] = "Neskafe Latte"; 
        productNames[4] = "Koka Kola"; 
        return productNames; 
    } 
 
    private double getProductPrice(String product) { 
        if (product.equals("Akua")) { 
            return 5000.0; 
        } else if (product.equals("Fruti Apel")) { 
            return 8000.0; 
        } else if (product.equals("Palpi Jeruk")) { 
            return 7500.0; 
        } else if (product.equals("Neskafe Latte")) { 
            return 11000.0; 
        } else if (product.equals("Koka Kola")) { 
            return 9500.0; 
        } 
        return 0.0; 
    } 
 
    public VendingMachineGUI getVendingMachine() { 
        return vendingMachine; 
    } 
}
