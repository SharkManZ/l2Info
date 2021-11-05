package ru.shark.home.l2info.enums;

/**
 * Тип класса
 */
public enum ClassType {
    FIGHTER("Боец"),
    MAGE("Маг");

    private String title;

    ClassType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
