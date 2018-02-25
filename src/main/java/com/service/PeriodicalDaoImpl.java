package com.service;


import com.connection.DataSource;
import com.dao.PeriodicalDao;
import com.model.Periodical;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PeriodicalDaoImpl implements PeriodicalDao {
    private static final Logger log = Logger.getLogger(PeriodicalDaoImpl.class);
    private DataSource dataSource = new DataSource("jdbc:mysql://localhost/papers", "root", "root");

    public void insert(Periodical periodical) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(PeriodicalDao.SQL_INSERT);

            statement.setInt(1, periodical.getId());
            statement.setString(2, periodical.getTitle());
            statement.setInt(3, periodical.getType().getId());
            statement.setBoolean(4, periodical.isMonthly());
            statement.setString(5, periodical.getColor());
            statement.setInt(6, periodical.getPages());
            statement.setInt(7, periodical.getIndex());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
             log.error("SQLException");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                log.error("SQLException");
            }
        }
    }
}
