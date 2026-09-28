/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;
import model.Medicine;
import service.PharmacyData;
import service.CartService;

public class MedicinePanel extends JPanel {

    private JPanel categoryPanel;
    private JPanel cardsContainer;
    private JScrollPane scrollPane;

    public MedicinePanel() {
        initComponentsCustom();
        loadCategoryButtons();
        loadMedicineCards("All");
    }

    private void initComponentsCustom() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        // Top Category Panel
        categoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        categoryPanel.setBackground(new Color(236, 240, 241));
        add(categoryPanel, BorderLayout.NORTH);

        // Center Cards Container inside ScrollPane
        cardsContainer = new JPanel(new GridLayout(0, 3, 15, 15));
        cardsContainer.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadCategoryButtons() {
        String[] categories = {
            "All", "Fever / Pain", "Heart", "Lung / Respiratory", 
            "Orthopedic", "Diabetes", "Antibiotic"
        };

        for (String cat : categories) {
            JButton btn = new JButton(cat);
            btn.setFont(new Font("SansSerif", Font.BOLD, 12));
            btn.setBackground(new Color(52, 152, 219));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);

            btn.addActionListener(e -> loadMedicineCards(cat));
            categoryPanel.add(btn);
        }
    }

    public void loadMedicineCards(String category) {
        cardsContainer.removeAll();

        List<Medicine> list = PharmacyData.getMedicinesByCategory(category);

        for (Medicine med : list) {
            JPanel card = createAttractiveCard(med);
            cardsContainer.add(card);
        }

        cardsContainer.revalidate();
        cardsContainer.repaint();
    }

    private JPanel createAttractiveCard(Medicine med) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230), 1),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        card.setBackground(Color.WHITE);

        // 1. Medicine Image (Class Path Loading from src/images)
JLabel lblImage = new JLabel();
lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

String imagePath = "/images/" + med.getImageName();
java.net.URL imgURL = getClass().getResource(imagePath);

if (imgURL != null) {
    ImageIcon icon = new ImageIcon(imgURL);
    Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    lblImage.setIcon(new ImageIcon(img));
} else {
    lblImage.setText("[ Image Missing ]");
    lblImage.setPreferredSize(new Dimension(100, 100));
}

        // --- 2. MEDICINE DETAILS ---
        JLabel lblName = new JLabel(med.getName());
        lblName.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblName.setForeground(new Color(44, 62, 80));
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblGeneric = new JLabel("(" + med.getGenericName() + ")");
        lblGeneric.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblGeneric.setForeground(Color.GRAY);
        lblGeneric.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblStrength = new JLabel("Strength: " + med.getStrength());
        lblStrength.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblStrength.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPrice = new JLabel("Price: ৳" + med.getPrice());
        lblPrice.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblPrice.setForeground(new Color(39, 174, 96));
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblStock = new JLabel("Stock: " + med.getStock());
        lblStock.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblStock.setForeground(Color.DARK_GRAY);
        lblStock.setAlignmentX(Component.CENTER_ALIGNMENT);

        // --- 3. QUANTITY SELECTION (JSpinner) ---
        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        qtyPanel.setBackground(Color.WHITE);
        
        JLabel lblQty = new JLabel("Qty:");
        JSpinner spinnerQty = new JSpinner(new SpinnerNumberModel(1, 1, med.getStock(), 1)); // Min 1, Max Stock
        qtyPanel.add(lblQty);
        qtyPanel.add(spinnerQty);

        // --- 4. ADD TO CART BUTTON ---
        JButton btnAddToCart = new JButton("🛒 Add to Cart");
        btnAddToCart.setBackground(new Color(46, 204, 113));
        btnAddToCart.setForeground(Color.WHITE);
        btnAddToCart.setFocusPainted(false);
        btnAddToCart.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnAddToCart.setAlignmentX(Component.CENTER_ALIGNMENT);

       btnAddToCart.addActionListener(e -> {
    int qty = (int) spinnerQty.getValue();

    // Cart-e item add kora
    CartService.addToCart(med, qty);

    JOptionPane.showMessageDialog(this, 
        qty + " x " + med.getName() + " added to Cart!\nSubtotal: ৳" + (med.getPrice() * qty), 
        "Added to Cart", JOptionPane.INFORMATION_MESSAGE);
});
       

        // --- ASSEMBLING CARD ---
        card.add(lblImage);
        card.add(Box.createVerticalStrut(8));
        card.add(lblName);
        card.add(lblGeneric);
        card.add(Box.createVerticalStrut(4));
        card.add(lblStrength);
        card.add(lblPrice);
        card.add(lblStock);
        card.add(Box.createVerticalStrut(8));
        card.add(qtyPanel);
        card.add(Box.createVerticalStrut(8));
        card.add(btnAddToCart);

        return card;
    }
}
