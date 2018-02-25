package com.dao;


import com.model.Periodical;

public interface PeriodicalDao {
    public static final String SQL_INSERT = "insert into " + Periodical.TABLE_NAME + " (" + Periodical.ID_COLUMN+" , " +
            Periodical.TITLE+" , "+Periodical.ID_TYPE+" , "+Periodical.MONTHLY+" , "+Periodical.COLOR+" , "+Periodical.PAGES+
            " , "+Periodical.PERIODICAL_INDEX + ") values (?,?,?,?,?,?,?)";
    public void insert(Periodical periodical);
}
