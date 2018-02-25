package com.dao;

import com.model.PeriodicalType;


public interface PeriodicalTypeDao {
    public static final String SQL_INSERT = "insert into " + PeriodicalType.TABLE_NAME + " (" + PeriodicalType.ID_COLUMN+" , " +
            PeriodicalType.NAME_COLUMN+" , "+
            PeriodicalType.GLOSSY + ") values (?,?,?)";
    public void insert(PeriodicalType periodicalType);
}
