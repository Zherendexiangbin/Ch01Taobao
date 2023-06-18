package com.example.ch01taobao.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final List<CartCommodity> itemList;
    private static double sumPrice = 0.0;

    static {
        itemList = new ArrayList<>();
    }
    public static List<CartCommodity> getItemList(){
        return itemList;
    }

    public static double getTotalPrice(){
        double total = 0;
        for(CartCommodity item : itemList){
            String priceString = item.getCommodity().getsNo();
            double price = 0.0;
            if(priceString != null && !priceString.isEmpty()){
                String number = priceString.substring(1);
                price = Double.parseDouble(number);
            }
            total += price * item.getQuantity();
        }
        return total;
    }
    public static void newCart(){
        itemList.clear();
    }
    public static double getSumPrice() {
        return sumPrice;
    }
    public static void setSumPrice(double sumPriceNew) {
        sumPrice = sumPriceNew;
    }
    public static void priceIncease(String price){
        double prices = 0;
        if(price != null && !price.isEmpty()) {
            String number = price.substring(1);
            prices = Double.parseDouble(number);
        }
        sumPrice += prices;
    }
    public static void priceDecrease(String price){
        double prices = 0;
        if(price != null && !price.isEmpty()) {
            String number = price.substring(1);
            prices = Double.parseDouble(number);
        }
        sumPrice -= prices;
    }
    public static void oneRemove (CartCommodity item){
        itemList.remove(item);
    }
    public static boolean isEmpty(){
        return itemList.isEmpty();
    }
}
