/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.*;
import javax.swing.*;
import service.CartService;

public class CheckoutPanel extends JPanel {

    private JTextField txtName, txtPhone;
    private JTextArea txtAddress;
    private JComboBox<String> comboPayment;
    private JLabel lblTotalAmount;
    private Dashboard dashboard;

    public CheckoutPanel(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponentsCustom();
    }

    private void initComponentsCustom() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("💳 CHECKOUT & BILLING", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Customer Name:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        txtPhone = new JTextField(20);
        formPanel.add(txtPhone, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Delivery Address:"), gbc);
        gbc.gridx = 1;
        txtAddress = new JTextArea(3, 20);
        txtAddress.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        formPanel.add(new JScrollPane(txtAddress), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Payment Method:"), gbc);
        gbc.gridx = 1;
        String[] payments = {"Cash on Delivery", "bKash / Nagad", "Credit / Debit Card"};
        comboPayment = new JComboBox<>(payments);
        formPanel.add(comboPayment, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Total Payable:"), gbc);
        gbc.gridx = 1;
        lblTotalAmount = new JLabel("৳0.00");
        lblTotalAmount.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTotalAmount.setForeground(new Color(39, 174, 96));
        formPanel.add(lblTotalAmount, gbc);

        add(formPanel, BorderLayout.CENTER);

        JButton btnPlaceOrder = new JButton("✅ Confirm & Place Order");
        btnPlaceOrder.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnPlaceOrder.setBackground(new Color(46, 204, 113));
        btnPlaceOrder.setForeground(Color.WHITE);
        btnPlaceOrder.setFocusPainted(false);
        btnPlaceOrder.setPreferredSize(new Dimension(0, 45));

        btnPlaceOrder.addActionListener(e -> processOrder());
        add(btnPlaceOrder, BorderLayout.SOUTH);
    }

    public void updatePayableAmount() {
        double total = CartService.calculateGrandTotal();
        lblTotalAmount.setText("৳" + String.format("%.2f", total));
    }

    private void processOrder() {
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String address = txtAddress.getText().trim();

        if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String receipt = "=== ORDER SUCCESSFUL ===\n\n" +
                         "Customer: " + name + "\n" +
                         "Phone: " + phone + "\n" +
                         "Address: " + address + "\n" +
                         "Payment: " + comboPayment.getSelectedItem() + "\n" +
                         "Total Paid: " + lblTotalAmount.getText() + "\n\n" +
                         "Thank you for ordering!";

        JOptionPane.showMessageDialog(this, receipt, "Order Placed", JOptionPane.INFORMATION_MESSAGE);

        txtName.setText("");
        txtPhone.setText("");
        txtAddress.setText("");
        CartService.clearCart();

        dashboard.showMedicinePage();
    }
}