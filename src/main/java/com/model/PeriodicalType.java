package com.model;

public class PeriodicalType {
    public static final String TABLE_NAME = "periodical_type";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name_of_periodical";
    public static final String GLOSSY = "glossy";
    private int id;
    private String name;
    private boolean isGlossy;

    public PeriodicalType() {
    }

    public PeriodicalType(int id, String name, boolean isGlossy) {
        this.id = id;
        this.name = name;
        this.isGlossy = isGlossy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGlossy() {
        return isGlossy;
    }

    public void setGlossy(boolean glossy) {
        isGlossy = glossy;
    }

    @Override
    public String toString() {
        return "PeriodicalType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isGlossy=" + isGlossy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodicalType that = (PeriodicalType) o;

        if (id != that.id) return false;
        if (isGlossy != that.isGlossy) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isGlossy ? 1 : 0);
        return result;
    }
}
