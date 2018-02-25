package com.model;


public class Periodical {
    public static final String TABLE_NAME = "periodical";
    public static final String ID_COLUMN = "id";
    public static final String TITLE = "title";
    public static final String ID_TYPE = "id_type";
    public static final String MONTHLY = "monthly";
    public static final String COLOR = "color";
    public static final String PAGES = "pages";
    public static final String PERIODICAL_INDEX ="index_of_periodical";
    private int id;
    private String title;
    private PeriodicalType type;
    private boolean isMonthly;
    private  String color;
    private  int pages;
    private int index;

    public Periodical(int id, String title, PeriodicalType type, boolean isMonthly, String color, int pages, int index) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.isMonthly = isMonthly;
        this.color = color;
        this.pages = pages;
        this.index = index;
    }

    public Periodical() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PeriodicalType getType() {
        return type;
    }

    public void setType(PeriodicalType type) {
        this.type = type;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", isMonthly=" + isMonthly +
                ", color='" + color + '\'' +
                ", pages=" + pages +
                ", index=" + index +
                '}';
    }
}
