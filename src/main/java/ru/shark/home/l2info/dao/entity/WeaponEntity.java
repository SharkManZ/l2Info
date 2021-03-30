package ru.shark.home.l2info.dao.entity;

import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;

import javax.persistence.*;

@Entity
@Table(name = "L2_WEAPON")
public class WeaponEntity extends BaseEntity {
    private static final String DESCRIPTION = "Оружие";

    /**
     * Уникальный идентификатор записи.
     */
    @Id
    @Column(name = "L2_ID")
    @SequenceGenerator(name = "WeaponGenerator", sequenceName = "L2_WEAPON_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WeaponGenerator")
    private Long id;

    @Column(name = "L2_NAME", nullable = false)
    private String name;

    @Column(name = "L2_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private WeaponType type;

    @Column(name = "L2_GRADE", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "L2_P_ATK", nullable = false)
    private Integer pAtk;

    @Column(name = "L2_M_ATK", nullable = false)
    private Integer mAtk;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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

    public static String getDescription() {
        return DESCRIPTION;
    }
}
