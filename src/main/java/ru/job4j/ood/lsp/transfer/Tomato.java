package ru.job4j.ood.lsp.transfer;



import java.time.LocalDateTime;

public class Tomato extends Food {

    public Tomato(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }

}
