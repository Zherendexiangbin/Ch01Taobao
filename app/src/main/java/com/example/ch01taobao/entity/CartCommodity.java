package com.example.ch01taobao.entity;

import java.util.Objects;

public class CartCommodity {
    private Commodity commodity;
    private int quantity;

    public CartCommodity(Commodity commodity, int quantity) {
        this.commodity = commodity;
        this.quantity = quantity;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartCommodity cartItem = (CartCommodity) o;
        return Objects.equals(commodity, cartItem.commodity);
    }
    @Override
    public int hashCode() {
        return Objects.hash(commodity);
    }
    @Override
    public String toString() {
        return "CartCommodity{" +
                "commodity=" + commodity +
                ", quantity=" + quantity +
                '}';
    }
}
