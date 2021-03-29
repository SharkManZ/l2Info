package ru.shark.home.l2info.dao.entity;

import ru.shark.home.l2info.enums.Grade;
import ru.shark.home.l2info.enums.WeaponType;

import javax.persistence.*;

@Entity
@Table(name = "L2_WEAPON")
public class WeaponEntity extends BaseEntity {

    /**
     * Уникальный идентификатор записи.
     */
    @Id
    @Column(name = "L2_ID")
    @SequenceGenerator(name = "WeaponGenerator", sequenceName = "L2_WEAPON_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WeaponGenerator")
    public Long id;

    @Column(name = "L2_NAME", nullable = false)
    public String name;

    @Column(name = "L2_TYPE", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    public WeaponType type;

    @Column(name = "L2_GRADE", nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    public Grade grade;

    @Column(name = "L2_P_ATK", nullable = false)
    public Integer pAtk;

    @Column(name = "L2_M_ATK", nullable = false)
    public Integer mAtk;

    @Override
    public Long getId() {
        return id;
    }
}
