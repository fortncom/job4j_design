package ru.job4j.ood.dip.transfer;

import java.time.LocalDateTime;

public class Food {

    protected boolean isDiscount = Boolean.FALSE;
    protected String name;
    protected LocalDateTime expiryDate;
    protected LocalDateTime createDate;
    protected int price;
    protected int discount;

    public Food(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public boolean isDiscount() {
        return isDiscount;
    }

    public void setDiscount(boolean discount) {
        isDiscount = discount;
    }
}
