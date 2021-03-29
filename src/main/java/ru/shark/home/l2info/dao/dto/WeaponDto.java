package ru.shark.home.l2info.dao.dto;

import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;

public class WeaponDto extends BaseDto {
    private String name;
    private WeaponType type;
    private Grade grade;
    private Integer pAtk;
    private Integer mAtk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Integer getpAtk() {
        return pAtk;
    }

    public void setpAtk(Integer pAtk) {
        this.pAtk = pAtk;
    }

    public Integer getmAtk() {
        return mAtk;
    }

    public void setmAtk(Integer mAtk) {
        this.mAtk = mAtk;
    }
}
