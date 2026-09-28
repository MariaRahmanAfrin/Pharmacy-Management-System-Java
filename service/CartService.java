/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.Medicine;

public class CartService {
    private static List<CartItem> cartList = new ArrayList<>();

    // কার্টে ওষুধ যোগ করা (যদি আগে থেকে থাকে তবে কোয়ান্টিটি বাড়িয়ে দেওয়া)
    public static void addToCart(Medicine medicine, int quantity) {
        for (CartItem item : cartList) {
            if (item.getMedicine().getId().equalsIgnoreCase(medicine.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartList.add(new CartItem(medicine, quantity));
    }

    // কার্টের সব আইটেম নেওয়া
    public static List<CartItem> getCartList() {
        return cartList;
    }

    // নির্দিষ্ট আইটেম মুছে ফেলা
    public static void removeItem(CartItem item) {
        cartList.remove(item);
    }

    // কার্ট খালি করা
    public static void clearCart() {
        cartList.clear();
    }

    // কার্টের সর্বমোট দাম (Grand Total) হিসাব করা
    public static double calculateGrandTotal() {
        double total = 0;
        for (CartItem item : cartList) {
            total += item.getSubtotal();
        }
        return total;
    }
}