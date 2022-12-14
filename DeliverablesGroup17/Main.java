import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.*;

class Main {
    JFrame frame;

    JPanel border;
    JPanel panel;
    JPanel header;
    JPanel body;
    JPanel footer;

    JLabel title;
    JLabel recL;

    JButton play;
    JButton clear;
    JButton recB;

    String[] row1 = {"Order", "Menu", "Ingredient", "Customer", "Supplier"};
    String[] row2 = {"<html>Insert Order</html>", "<html>Insert Menu <br>Item</html>", "<html>List Ingredients <br>in Item</html>", "<html>List orders <br>equal to price</html>", "<html>Delete <br>Ingredient</html>"};
    String[] row3 = {"Update Order", "<html>Delete Menu <br>Item</html>", "<html>Change Menu <br>Item</html>", "<html>List all orders <br>by customer</html>", "<html>List all <br>ingredients <br>by supplier</html>"};


    JButton g1 = new JButton("test1");
    JButton g2 = new JButton("-");
    JButton g3 = new JButton("-");
    JButton g4 = new JButton("-");
    JButton g5 = new JButton("-");
    JButton test1[] = {g1, g2, g3, g4, g5};

    JButton h1 = new JButton("test2");
    JButton h2 = new JButton("-");
    JButton h3 = new JButton("-");
    JButton h4 = new JButton("-");
    JButton h5 = new JButton("-");
    JButton test2[] = {h1, h2, h3, h4, h5};

    JButton b1 = new JButton("test3");
    JButton b2 = new JButton("-");
    JButton b3 = new JButton("-");
    JButton b4 = new JButton("-");
    JButton b5 = new JButton("-");
    JButton test3[] = {b1, b2, b3, b4, b5};

    public static void main(String[] args) {
        Main t = new Main();
        t.buttonSetup();
        t.makeGUI();
    }

    public void buttonSetup() {
        for (JButton b : test1) {
            b.setOpaque(true);
            b.setForeground(Color.green);
            b.setBackground(Color.red);
        }
        for (JButton b : test2) {
            b.setOpaque(true);
            b.setForeground(Color.black);
            b.setBackground(Color.white);
        }
        for (JButton b : test3) {
            b.setOpaque(true);
            b.setForeground(Color.yellow);
            b.setBackground(Color.blue);
        } 
    }

    public void makeGUI() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // background panel
        border = new JPanel();
        border.setBorder(new EmptyBorder(new Insets(10,10,10,10)));
        border.setBackground(Color.black);

        // top panel
        header = new JPanel();
        header.setSize(100,100);
        title = new JLabel("Restaurant Database");
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 36));
        header.add(title);

        // button panel
        body = new JPanel();
        body.setSize(100,100);
        body.setLayout(new GridLayout(3, 5));
        for (int i = 0; i < test1.length; i++) {
            body.add(test1[i]); 
            test1[i].setText(row1[i]);
            test1[i].setFont(new Font("Comic Sans MS", Font.BOLD,24));
        }
        for (int i = 0; i < test2.length; i++) {
            body.add(test2[i]);
            test2[i].setText(row2[i]);
            test2[i].setFont(new Font("Comic Sans MS", Font.BOLD,18));
        }
        for (int i = 0; i < test3.length; i++) {
            body.add(test3[i]);
            test3[i].setText(row3[i]);
            test3[i].setFont(new Font("Comic Sans MS", Font.BOLD,18));
        }

        h1.addActionListener(new firstAction());
        h2.addActionListener(new secondAction()); 
        h3.addActionListener(new thirdAction()); 
        h4.addActionListener(new fourthAction()); 
        h5.addActionListener(new fifthAction()); 
        b1.addActionListener(new sixthAction());
        b2.addActionListener(new seventhAction());
        b3.addActionListener(new eighthAction());
        b4.addActionListener(new ninthAction());
        b5.addActionListener(new tenthAction());


        // bottom panel
        footer = new JPanel();
        recL = new JLabel("1");
        recB = new JButton("2");
        play = new JButton("3");
        clear = new JButton("4");
        play.setOpaque(true);
        play.setForeground(new Color(73,156,84));
        clear.setOpaque(true);
        clear.setForeground(new Color(199,84,80));
  


        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        border.add(panel);
        frame.add(border);
        panel.add(header, BorderLayout.NORTH);
        panel.add(body, BorderLayout.CENTER);
        panel.add(footer, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(800, 600));
        frame.pack();   
     }
    //Insert Menu Item button functionality
     class secondAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            float dollar = 0;
            boolean virus = false;
            String menuItem = JOptionPane.showInputDialog(frame, "Enter Menu Item: ",JOptionPane.PLAIN_MESSAGE);
            while (!virus) {
            try {
            float price = Float.parseFloat(JOptionPane.showInputDialog(frame, "Enter Price of type Float: ",JOptionPane.PLAIN_MESSAGE));
            virus = true;
            dollar = price;
            }
            catch (Exception E) {
                JOptionPane.showMessageDialog(frame,
                "Invalid Input!", "Error",
                JOptionPane.ERROR_MESSAGE);
                virus = false;
            }
        }
            String ingredient = JOptionPane.showInputDialog(frame, "Enter Ingredients, separated by a comma",JOptionPane.PLAIN_MESSAGE);
            String equipment = JOptionPane.showInputDialog(frame, "Enter Equipments, separated by a comma (enter the same amount of ingredients)",JOptionPane.PLAIN_MESSAGE);
            String[] ingArr = ingredient.split(", ");
            String[] eqpArr = equipment.split(", ");

            if (ingArr.length != eqpArr.length || menuItem.length() == 0 || ingArr.length == 0 || eqpArr.length == 0) {
                JOptionPane.showMessageDialog(frame,
                "Invalid Input!", "Error",
                JOptionPane.ERROR_MESSAGE);
            }
            else {
                for(int i=0; i < ingArr.length; i++){
                    try{
                        UseCases.insertMenuItemSP(menuItem, dollar, ingArr[i], eqpArr[i]);
                    } catch(Exception sql){
                        JOptionPane.showMessageDialog(frame,
                "Invalid Input!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
     }

     //Insert Order button functionality
     class firstAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String customerName = JOptionPane.showInputDialog(frame, "Enter Customer Name: ",JOptionPane.PLAIN_MESSAGE);
            String menuItem = JOptionPane.showInputDialog(frame, "Enter Menu Item: ",JOptionPane.PLAIN_MESSAGE);
            if (customerName.length() <= 0 || menuItem.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else {
                try{
                    UseCases.insertOrderSP(customerName, menuItem);
                } catch(Exception sql){
                    JOptionPane.showMessageDialog(frame,
            "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
     }
    
    //findMenuItemIngredients button functionality
     class thirdAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String menuItem = JOptionPane.showInputDialog(frame, "Enter Menu Item: ",JOptionPane.PLAIN_MESSAGE);
            if (menuItem.length() <= 0 || menuItem.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String result = menuItem + " " + UseCases.findMenuItemIngredients(menuItem); 
                    JOptionPane.showMessageDialog(frame,
                    menuItem, "List",
                    JOptionPane.PLAIN_MESSAGE);
                } catch(Exception sql){
                    JOptionPane.showMessageDialog(frame,
            "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
     }
    //findOrderBasedOnCost use case button functionality
     class fourthAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            boolean virus = false;
            float dollar = 0;
            while (!virus) {
                try {
                float price = Float.parseFloat(JOptionPane.showInputDialog(frame, "Enter Price of type Float: ",JOptionPane.PLAIN_MESSAGE));
                virus = true;
                dollar = price;

                String result = UseCases.findOrdersBasedOnCost(dollar);
                JOptionPane.showMessageDialog(frame,
                    result, "List",
                    JOptionPane.PLAIN_MESSAGE);
                }
                catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);
                    virus = false;
                }
            }
        }
    }
    // DeleteIngredient use case button functionality
     class fifthAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String ingredient = JOptionPane.showInputDialog(frame, "Enter ingredient: ",JOptionPane.PLAIN_MESSAGE);
            if (ingredient.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    UseCases.deleteIngredient(ingredient);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);    
                }
            }
        }
     }
    //updateOrderSP use case button functionality
     class sixthAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String menuItem = JOptionPane.showInputDialog(frame, "Enter menuItem ",JOptionPane.PLAIN_MESSAGE);
            int orderId = Integer.parseInt(JOptionPane.showInputDialog(frame, "Order Id ",JOptionPane.PLAIN_MESSAGE));
            if (menuItem.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    UseCases.updateOrderSP(orderId, menuItem);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);    
                }
            }
        }
     }
    //Delete Menu Item use case Button Functionality
     class seventhAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String menuItem = JOptionPane.showInputDialog(frame, "Enter menuItem ",JOptionPane.PLAIN_MESSAGE);
            if (menuItem.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    UseCases.deleteMenuItem(menuItem);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);    
                }
            }
        }
     }
     //change Menu Item use case button Functionality
     class eighthAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String menuItem = JOptionPane.showInputDialog(frame, "Enter Menu Item: ",JOptionPane.PLAIN_MESSAGE);
            String ingredientReplaced = JOptionPane.showInputDialog(frame, "Enter ingredient that is being replaced: ",JOptionPane.PLAIN_MESSAGE);
            String ingredientReplacing = JOptionPane.showInputDialog(frame, "Enter replacing ingredient ",JOptionPane.PLAIN_MESSAGE);
            String equipmentReplaced = JOptionPane.showInputDialog(frame, "Enter equipment being replaced ",JOptionPane.PLAIN_MESSAGE);
            String equipmentReplacing = JOptionPane.showInputDialog(frame, "Enter relacing equipment ",JOptionPane.PLAIN_MESSAGE);
            if (menuItem.length() <= 0 || ingredientReplaced.length() <=0 || ingredientReplacing.length() <=0 || equipmentReplaced.length() <=0 || equipmentReplacing.length() <=0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{   
                    UseCases.changeMenuItem(menuItem, ingredientReplaced, ingredientReplacing, equipmentReplaced, equipmentReplacing);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);    
                }
            }
        }
     }
    //findOrderHistory use case button functionality
     class ninthAction implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            String customerName = JOptionPane.showInputDialog(frame, "Enter Name of Customer ",JOptionPane.PLAIN_MESSAGE);
            if (customerName.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String result = customerName + " " + UseCases.findOrderHistory(customerName);
                    JOptionPane.showMessageDialog(frame,
                        result, "List",
                        JOptionPane.PLAIN_MESSAGE);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);    
                }
            }
        }
     }
    //find supplier ingredients use case button functionality
     class tenthAction implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String supplierName = JOptionPane.showInputDialog(frame, "Enter Name of Supplier ",JOptionPane.PLAIN_MESSAGE);
            if (supplierName.length() <= 0) {
                JOptionPane.showMessageDialog(frame,"INVALID INPUT!", "ERROR",JOptionPane.ERROR_MESSAGE);
            }
            else{
                try{
                    String result = supplierName + " " + UseCases.findSupplierIngredients(supplierName);
                    JOptionPane.showMessageDialog(frame,
                        result, "List",
                        JOptionPane.PLAIN_MESSAGE);
                } catch (Exception E) {
                    JOptionPane.showMessageDialog(frame,
                    "Invalid Input!", "Error",
                    JOptionPane.ERROR_MESSAGE);    
                }

            }
        }
     }
}       
