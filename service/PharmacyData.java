/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.Medicine;

public class PharmacyData {
    private static List<Medicine> medicineList = new ArrayList<>();

    // স্ট্যাটিক ব্লক: প্রোগ্রাম চললে স্বয়ংক্রিয়ভাবে স্যাম্পল মেডিসিনগুলো লোড হবে
    static {
        // Fever / Pain
        medicineList.add(new Medicine("M001", "Napa", "Paracetamol", "Fever / Pain", "500 mg", 2.0, 100, "napa.png"));
        medicineList.add(new Medicine("M002", "Ace", "Paracetamol", "Fever / Pain", "500 mg", 2.0, 150, "ace.png"));

        // Heart
        medicineList.add(new Medicine("M003", "Amlodipine", "Amlodipine Besylate", "Heart", "5 mg", 5.0, 80, "amlodipine.png"));
        medicineList.add(new Medicine("M004", "Atorvastatin", "Atorvastatin Calcium", "Heart", "10 mg", 12.0, 60, "atorvastatin.png"));

        // Lung / Respiratory
        medicineList.add(new Medicine("M005", "Salbutamol", "Salbutamol Inhaler", "Lung / Respiratory", "100 mcg", 180.0, 30, "salbutamol.png"));

        // Orthopedic
        medicineList.add(new Medicine("M006", "Calcium-D", "Calcium + Vitamin D3", "Orthopedic", "500 mg", 8.0, 120, "calcium.png"));

        // Diabetes
        medicineList.add(new Medicine("M007", "Comet", "Metformin HCl", "Diabetes", "500 mg", 6.0, 90, "comet.png"));

        // Antibiotic
        medicineList.add(new Medicine("M008", "Azithrocin", "Azithromycin", "Antibiotic", "500 mg", 35.0, 50, "azithrocin.png"));
    }

    // সব মেডিসিন পাওয়ার জন্য
    public static List<Medicine> getAllMedicines() {
        return medicineList;
    }

    // নির্দিষ্ট ক্যাটাগরির মেডিসিন ফিল্টার করে পাওয়ার জন্য
    public static List<Medicine> getMedicinesByCategory(String category) {
        if (category.equals("All") || category.equals("Home")) {
            return medicineList;
        }
        
        List<Medicine> filteredList = new ArrayList<>();
        for (Medicine med : medicineList) {
            if (med.getCategory().equalsIgnoreCase(category)) {
                filteredList.add(med);
            }
        }
        return filteredList;
    }
}