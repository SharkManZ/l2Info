package ru.shark.home.l2info.dao.entity;

import ru.shark.home.common.dao.entity.BaseEntity;

import javax.persistence.*;

/**
 * Сущность "Персонажи".
 */
@Entity
@Table(name = "L2_CHARACTER")
public class CharacterEntity extends BaseEntity {
    private static final String DESCRIPTION = "Персонаж";

    /**
     * Уникальный идентификатор записи.
     */
    @Id
    @Column(name = "L2_ID")
    @SequenceGenerator(name = "CharacterGenerator", sequenceName = "L2_CHARACTER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CharacterGenerator")
    private Long id;

    @Column(name = "L2_NICKNAME", nullable = false)
    private String nickname;

    @ManyToOne
    @JoinColumn(name = "L2_CHAR_CLASS_ID", nullable = false)
    private ClassEntity charClass;

    @Column(name = "L2_LEVEL", nullable = false)
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "L2_WEAPON_ID")
    private WeaponEntity weapon;

    @Column(name = "L2_HELMET_ADMOR_ID")
    private Long helmetId;

    @Column(name = "L2_TOP_ARMOR_ID")
    private Long topId;

    @Column(name = "L2_BOTTOM_ARMOR_ID")
    private Long bottomId;

    @Column(name = "L2_BOOTS_ARMOR_ID")
    private Long bootsId;

    @Column(name = "L2_GLOVES_ARMOR_ID")
    private Long glovesId;

    @Column(name = "L2_SHIELD_ARMOR_ID")
    private Long shieldId;

    @Column(name = "L2_NECKLACE_JEWELRY_ID")
    private Long necklaceId;

    @Column(name = "L2_EARRING_LEFT_JEWERY_ID")
    private Long earringLeftId;

    @Column(name = "L2_EARRING_RIGHT_JEWELRY_ID")
    private Long earringRightId;

    @Column(name = "L2_RING_LEFT_JEWELRY_ID")
    private Long ringLeftId;

    @Column(name = "L2_RING_RIGHT_JEWELRY_ID")
    private Long ringRightId;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClassEntity getCharClass() {
        return charClass;
    }

    public void setCharClass(ClassEntity charClass) {
        this.charClass = charClass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public WeaponEntity getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponEntity weapon) {
        this.weapon = weapon;
    }

    public Long getHelmetId() {
        return helmetId;
    }

    public void setHelmetId(Long helmetId) {
        this.helmetId = helmetId;
    }

    public Long getTopId() {
        return topId;
    }

    public void setTopId(Long topId) {
        this.topId = topId;
    }

    public Long getBottomId() {
        return bottomId;
    }

    public void setBottomId(Long bottomId) {
        this.bottomId = bottomId;
    }

    public Long getBootsId() {
        return bootsId;
    }

    public void setBootsId(Long bootsId) {
        this.bootsId = bootsId;
    }

    public Long getGlovesId() {
        return glovesId;
    }

    public void setGlovesId(Long glovesId) {
        this.glovesId = glovesId;
    }

    public Long getShieldId() {
        return shieldId;
    }

    public void setShieldId(Long shieldId) {
        this.shieldId = shieldId;
    }

    public Long getNecklaceId() {
        return necklaceId;
    }

    public void setNecklaceId(Long necklaceId) {
        this.necklaceId = necklaceId;
    }

    public Long getEarringLeftId() {
        return earringLeftId;
    }

    public void setEarringLeftId(Long earringLeftId) {
        this.earringLeftId = earringLeftId;
    }

    public Long getEarringRightId() {
        return earringRightId;
    }

    public void setEarringRightId(Long earringRightId) {
        this.earringRightId = earringRightId;
    }

    public Long getRingLeftId() {
        return ringLeftId;
    }

    public void setRingLeftId(Long ringLeftId) {
        this.ringLeftId = ringLeftId;
    }

    public Long getRingRightId() {
        return ringRightId;
    }

    public void setRingRightId(Long ringRightId) {
        this.ringRightId = ringRightId;
    }

    public static String getDescription() {
        return DESCRIPTION;
    }
}
