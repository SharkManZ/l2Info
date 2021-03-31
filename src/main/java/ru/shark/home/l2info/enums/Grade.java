package ru.shark.home.l2info.enums;


public enum Grade implements WithTitleEnum {
    NG("NG-грейд"),
    D("D-грейд"),
    C("C-грейд"),
    B("B-грейд"),
    A("A-грейд"),
    S("S-грейд");

    private String title;

    Grade(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
