package ru.job4j.ood.lsp.transfer;

import java.time.LocalDateTime;

public class Food {


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
}
