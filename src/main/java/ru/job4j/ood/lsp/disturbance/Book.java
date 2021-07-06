package ru.job4j.ood.lsp.disturbance;

/**
 * Класс EnglishReader нарушает принцип LSP тем, что перекрывает
 * метод суперкласса меняя логику проверки входного параметра,
 * и будет выкидывать исключение которого от него не ждут.
 *
 */

public class Book {

    private String name;
    private int pages;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

}

class Reader {

    protected int currentPage = 1;

    public void read(Book book) {
        if (book.getPages() == currentPage) {
            System.out.println("Pages are out");
            return;
        }
        // other logic
    }
}

class EnglishReader extends Reader {

    public void read(Book book) {
        if (book.getPages() == currentPage) {
            throw new IllegalStateException("pages are out");
        }
        // other logic
    }
}