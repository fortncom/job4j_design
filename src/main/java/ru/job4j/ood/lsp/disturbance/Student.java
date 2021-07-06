package ru.job4j.ood.lsp.disturbance;

import java.time.LocalDateTime;

/**
 * Класс MathStudent нарушает принцип LSP т.к. игнорирует валидацию
 * поля scores у родителя.
 *
 */

public class Student {

    protected int scores;
    protected String name;
    protected LocalDateTime dateEnrollment;

    public Student(int scores, String name, LocalDateTime dateEnrollment) {
        this.scores = scores;
        this.name = name;
        this.dateEnrollment = dateEnrollment;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        if (scores < 0 || scores > 100) {
            throw new IllegalArgumentException("scores are invalid, must be between 0 and 100");
        }
        this.scores = scores;
    }
}

class MathStudent extends Student {

    public MathStudent(int scores, String name, LocalDateTime dateEnrollment) {
        super(scores, name, dateEnrollment);
    }

    @Override
    public void setScores(int scores) {
        this.scores = scores;
    }
}