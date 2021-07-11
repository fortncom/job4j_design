package ru.job4j.ood.dip.transfer;


import java.time.LocalDateTime;

public class Chicken extends Food {

    public Chicken(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }

}
