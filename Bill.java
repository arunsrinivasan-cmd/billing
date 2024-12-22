import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Bill extends JFrame {
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Double> price = new ArrayList<Double>();
    ArrayList<Double> quantity = new ArrayList<Double>();
    ArrayList<Double> qp = new ArrayList<>();
    Map<String, String> itemPriceMap = new HashMap<>();
    Map<String, String> itemNameMap = new HashMap<>();
    double Total = 0;

    public void pro() {
        itemPriceMap.put("1", "10.0");
        itemPriceMap.put("2", "10.0");
        itemPriceMap.put("3", "40.0");
        itemPriceMap.put("4", "5.0");
        itemPriceMap.put("5", "5.0");

        itemNameMap.put("1", "Pen");
        itemNameMap.put("2", "Pencil");
        itemNameMap.put("3", "Ink Pen");
        itemNameMap.put("4", "Eraser");
        itemNameMap.put("5", "Sharpner");

        JLabel tile = new JLabel("JBilling");
        Font font2 = new Font("Times New Roman", Font.BOLD, 70);
        tile.setFont(font2);
        tile.setBounds(10, 25, 500, 80);

        JLabel label0 = new JLabel("Item Code:");
        JTextField txt0 = new JTextField();
        label0.setBounds(150, 190, 100, 30);
        txt0.setBounds(270, 190, 100, 30);

        JLabel label1 = new JLabel("Item Name:");
        JTextField txt1 = new JTextField();
        label1.setBounds(150, 230, 100, 30);
        txt1.setBounds(270, 230, 100, 30);

        JLabel label2 = new JLabel("Item Price:");
        JTextField txt2 = new JTextField();
        label2.setBounds(150, 270, 100, 30);
        txt2.setBounds(270, 270, 100, 30);

        JLabel label3 = new JLabel("Item Quantity:");
        JTextField txt3 = new JTextField();
        label3.setBounds(150, 310, 100, 30);
        txt3.setBounds(270, 310, 100, 30);

        JLabel label4 = new JLabel("Sub Total:");
        JTextField txt4 = new JTextField();
        label4.setBounds(150, 390, 100, 30);
        txt4.setBounds(270, 390, 100, 30);

        JLabel label5 = new JLabel("Total:");
        JTextField txt5 = new JTextField();
        label5.setBounds(150, 430, 100, 30);
        txt5.setBounds(270, 430, 100, 30);


        JLabel label6 = new JLabel("Discount:");
        JTextField txt6 = new JTextField();
        label6.setBounds(150, 470, 100, 30);
        txt6.setBounds(270, 470, 100, 30);

        JLabel label7 = new JLabel("Tax:");
        JTextField txt7 = new JTextField();
        label7.setBounds(150, 510, 100, 30);
        txt7.setBounds(270, 510, 100, 30);

        JButton btn = new JButton("add");
        btn.setBounds(220, 350, 70, 20);

        JButton btn1 = new JButton("Add Item");
        btn1.setBounds(390, 190, 100, 20);

        JButton btn2 = new JButton("Get Total");
        btn2.setBounds(390, 430, 100, 20);

        JButton btn3 = new JButton("Generate Bill");
        btn3.setBounds(200, 580, 150, 20);

        JButton btn4 = new JButton("Save as File");
        btn4.setBounds(650, 780, 300, 20);

        JTextArea txta = new JTextArea();
        txta.setBounds(600, 50, 400, 700);
        JScrollPane scrollPane = new JScrollPane(txta);

        ActionListener acl = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name.add(txt1.getText());
                price.add(Double.valueOf(txt2.getText()));
                quantity.add(Double.valueOf(txt3.getText()));
                for (Double num1 : price) {
                    for (Double num2 : quantity) {
                        double item = num1 * num2;
                        txt4.setText(String.valueOf(item));
                    }
                }
                txt1.setText("");
                txt2.setText("");
                txt3.setText("");
            }
        };

        ActionListener acl1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemCode = String.valueOf(txt0.getText());
                if (itemPriceMap.containsKey(itemCode)) {
                    String itemName = itemNameMap.get(itemCode);
                    String price = itemPriceMap.get(itemCode);

                    txt1.setText(itemName);
                    txt2.setText(price);
                    txt0.setText("");
                }
            }
        };

        ActionListener acl2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qp.add(Double.valueOf(txt4.getText()));
                Total = Total + Double.valueOf(txt4.getText());
                txt5.setText(String.valueOf(Total));
                txt4.setText("");
            }
        };

        ActionListener acl3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double lastTotal = Double.parseDouble(txt5.getText());
                double discountAmount = Double.parseDouble(txt6.getText());
                double taxedAmount = Double.parseDouble(txt7.getText());

                double discount = lastTotal * (discountAmount / 100);
                double tax = lastTotal * (taxedAmount / 100);
                double tot = lastTotal + tax - discount;

                StringBuilder bill = new StringBuilder();
                Font font1 = new Font("Times New Roman", Font.ITALIC, 16);
                txta.setFont(font1);
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String formattedTime = now.format(formatter);
                String formatttedDate = now.format(formatter1);


                bill.append("                                           --Bill--");
                bill.append("\n==============================================================================");
                bill.append("\n                                        ASV stores");
                bill.append("\n                             Address:New main road " +
                        "\n                                  Madurai 689630 ");
                bill.append("\n Customer Name:                                      Time:" + formattedTime);
                bill.append("\n                                                                   Date:" + formatttedDate);
                bill.append("\n==============================================================================");
                bill.append(String.format("\n %-40s %-15s %-15s %-15s", "Item", "Quantity", "Price", "Total"));
                bill.append("\n==============================================================================");
                for (int i = 0; i < name.size(); i++) {
                    bill.append(String.format("\n %-40s %-15s %-15s %-15s", name.get(i), quantity.get(i), price.get(i), qp.get(i)));
                }
                bill.append("\n==============================================================================");
                bill.append("\nDiscount:" + Integer.valueOf((int) discount));
                bill.append("\nTax:" + Integer.valueOf((int) tax) + "                                                     " + "Total:" + Integer.valueOf((int) tot));
                bill.append("\n==============================================================================");
                bill.append("\n                                       Thank you!");
                txta.setText(String.valueOf(bill));
            }
        };

        ActionListener acl4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txta.getText();
                try (FileWriter writer = new FileWriter("output.txt")) {
                    Font font1 = new Font("Times New Roman", Font.ITALIC, 16);
                    setFont(font1);
                    writer.write(text);
                    //JOptionPane.showMessageDialog(frame, "File saved successfully.");
                } catch (IOException ex) {
                    // JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
                }
            }
        };

        btn.addActionListener(acl);
        btn1.addActionListener(acl1);
        btn2.addActionListener(acl2);
        btn3.addActionListener(acl3);
        btn4.addActionListener(acl4);

        add(label0);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(tile);

        add(txt0);
        add(txt1);
        add(txt2);
        add(txt3);
        add(txt4);
        add(txt5);
        add(txt6);
        add(txt7);

        add(btn);
        add(btn1);
        add(btn2);
        add(btn3);
        add(btn4);

        add(txta);
        add(scrollPane);

        setSize(1000, 1000);
        setTitle("JBilling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

    }

  public static class  Login extends JFrame {
    Login(){
            Label use = new Label("Username:");
            use.setBounds(500,300,100,30);
            TextField txtuse = new TextField();
            txtuse.setBounds(620,300,100,30);

            Label pass = new Label("Password:");
            pass.setBounds(500,360,100,30);
            JPasswordField txtpas = new JPasswordField();
            txtpas.setBounds(620,360,100,30);

            JButton btnlg = new JButton("Login");
            btnlg.setBounds(630,430,100,20);



            ActionListener aclog = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String user=txtuse.getText();
                    String pass=txtpas.getText();
                    if (user.equals("Arun")&& pass.equals("aruns")){
                        Bill b = new Bill();
                       b.setVisible(true);
                       b.pro();
                    }
                    else{
                        txtuse.setText("Invalid");
                        txtpas.setText("Invalid");
                    }
                }
            };

            btnlg.addActionListener(aclog);


            add(use);
            add(pass);

            add(txtuse);
            add(txtpas);

            add(btnlg);

            setTitle("Login");
            setLayout(null);
            setSize(1000,1000);
            setVisible(true);
       }
       public static void main (String[] args){
        new Login();
        }
    }
}