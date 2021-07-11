package ru.job4j.ood.dip.transfer;



import java.time.LocalDateTime;

public class Bread extends Food {

    public Bread(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
