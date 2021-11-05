package ru.shark.home.l2info.dao.dto;

public class ClassDto extends BaseDto {
    private String name;
    private RaceDto race;
    private EnumDto type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RaceDto getRace() {
        return race;
    }

    public void setRace(RaceDto race) {
        this.race = race;
    }

    public EnumDto getType() {
        return type;
    }

    public void setType(EnumDto type) {
        this.type = type;
    }
}
