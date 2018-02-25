package com.service;


import com.connection.DataSource;
import com.dao.PeriodicalTypeDao;
import com.model.PeriodicalType;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PeriodicalTypeDaoImpl implements PeriodicalTypeDao {
    private static final Logger log = Logger.getLogger(PeriodicalTypeDaoImpl.class);
    private DataSource dataSource = new DataSource("jdbc:mysql://localhost/papers", "root", "root");

    public void insert(PeriodicalType periodicalType) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(PeriodicalTypeDao.SQL_INSERT);
            statement.setInt(1, periodicalType.getId());
            statement.setString(2, periodicalType.getName());
            statement.setBoolean(3, periodicalType.isGlossy());
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
