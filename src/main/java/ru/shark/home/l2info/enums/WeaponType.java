package ru.shark.home.l2info.enums;

public enum WeaponType {
    BOW("Лук"),
    SWORD("Меч"),
    BLUNT("Ударное"),
    KNIFE("Нож"),
    DUAL("Сдвоенное"),
    FIST("Кастеты");

    private String title;

    WeaponType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
