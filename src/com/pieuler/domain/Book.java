package com.pieuler.domain;

import java.time.LocalDateTime;

public class Book implements Entity {
    private int id;
    private String title;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static Book of(String title) {
        int id = LocalDateTime.now().getNano();
        return new Book(id, title);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
