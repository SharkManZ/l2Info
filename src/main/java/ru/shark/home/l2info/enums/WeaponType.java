package ru.shark.home.l2info.enums;

/**
 * Тип оружия.
 */
public enum WeaponType implements WithTitleEnum {
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

    @Override
    public String getTitle() {
        return title;
    }
}
