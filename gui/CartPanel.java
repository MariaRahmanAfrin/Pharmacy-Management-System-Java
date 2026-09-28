package gui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.CartItem;
import service.CartService;

public class CartPanel extends JPanel {

    private JTable cartTable;
    private DefaultTableModel tableModel;
    private JLabel lblGrandTotal;
    private JButton btnRemove, btnClear, btnCheckout;
    private Dashboard dashboard;

    public CartPanel(Dashboard dashboard) {
        this.dashboard = dashboard;
        initComponentsCustom();
    }

    private void initComponentsCustom() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // 1. Header Title
        JLabel lblTitle = new JLabel("🛒 SHOPPING CART", SwingConstants.CENTER);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblTitle, BorderLayout.NORTH);

        // 2. Center Table inside ScrollPane
        String[] columnNames = {"Medicine Name", "Strength", "Price", "Quantity", "Subtotal"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cartTable = new JTable(tableModel);
        cartTable.setRowHeight(28);
        cartTable.setFont(new Font("SansSerif", Font.PLAIN, 13));
        cartTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        cartTable.getTableHeader().setBackground(new Color(236, 240, 241));

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        add(scrollPane, BorderLayout.CENTER);

        // 3. Bottom Panel (Total & Action Buttons)
        JPanel bottomContainer = new JPanel(new BorderLayout(10, 10));
        bottomContainer.setBackground(Color.WHITE);
        bottomContainer.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblGrandTotal = new JLabel("Grand Total: ৳0.00");
        lblGrandTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblGrandTotal.setForeground(new Color(39, 174, 96));
        bottomContainer.add(lblGrandTotal, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        btnRemove = new JButton("❌ Remove Item");
        btnRemove.setBackground(new Color(231, 76, 60));
        btnRemove.setForeground(Color.WHITE);
        btnRemove.setFocusPainted(false);

        btnClear = new JButton("🗑️ Clear Cart");
        btnClear.setBackground(new Color(149, 165, 166));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);

        btnCheckout = new JButton("💳 Proceed to Checkout");
        btnCheckout.setBackground(new Color(46, 204, 113));
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setFocusPainted(false);
        btnCheckout.setFont(new Font("SansSerif", Font.BOLD, 13));

        buttonPanel.add(btnRemove);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnCheckout);

        bottomContainer.add(buttonPanel, BorderLayout.EAST);
        add(bottomContainer, BorderLayout.SOUTH);

        // --- BUTTON ACTIONS ---
        btnRemove.addActionListener(e -> removeSelectedItem());
        btnClear.addActionListener(e -> clearAllItems());
        btnCheckout.addActionListener(e -> {
            if (CartService.getCartList().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Your cart is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                dashboard.showCheckoutPage();
            }
        });
    }

    public void refreshCartTable() {
        tableModel.setRowCount(0);
        List<CartItem> items = CartService.getCartList();

        for (CartItem item : items) {
            Object[] row = {
                item.getMedicine().getName(),
                item.getMedicine().getStrength(),
                "৳" + item.getMedicine().getPrice(),
                item.getQuantity(),
                "৳" + item.getSubtotal()
            };
            tableModel.addRow(row);
        }

        double grandTotal = CartService.calculateGrandTotal();
        lblGrandTotal.setText("Grand Total: ৳" + String.format("%.2f", grandTotal));
    }

    private void removeSelectedItem() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow != -1) {
            CartItem item = CartService.getCartList().get(selectedRow);
            CartService.removeItem(item);
            refreshCartTable();
            JOptionPane.showMessageDialog(this, "Item removed from cart.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void clearAllItems() {
        if (!CartService.getCartList().isEmpty()) {
            CartService.clearCart();
            refreshCartTable();
            JOptionPane.showMessageDialog(this, "Cart cleared successfully.");
        }
    }
}