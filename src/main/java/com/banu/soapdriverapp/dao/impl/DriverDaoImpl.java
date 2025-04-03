package com.banu.soapdriverapp.dao.impl;

import com.banu.soapdriverapp.dao.DriverDao;
import com.banu.soapdriverapp.entity.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.banu.soapdriverapp.dao.constants.DaoConstants.Functions.*;

@Slf4j
@Repository
public class DriverDaoImpl implements DriverDao {
    private final JdbcTemplate jdbcTemplate;

    public DriverDaoImpl(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Driver> getAllDrivers() {
        Long startTime = System.currentTimeMillis();
        Connection connection = null;
        CallableStatement callableSt = null;
        ResultSet rs = null;
        List<Driver> drivers = new ArrayList<Driver>();
        try {
            startTime = System.currentTimeMillis();
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableSt = connection.prepareCall(GET_DRIVER);
            callableSt.execute();
            rs = callableSt.getResultSet();

            if (rs != null) {
                while (rs.next()) {
                    Driver driver = new Driver();
                    driver.setId(rs.getInt("id"));
                    driver.setName(rs.getString("name"));
                    driver.setNic(rs.getString("nic"));
                    driver.setVehicle_no(rs.getString("vehicle_no"));
                    driver.setVehicle_type(rs.getString("vehicle_type"));
                    driver.setDate_of_birth(rs.getDate("date_of_birth").toLocalDate());
                    driver.setGender(rs.getString("gender"));
                    driver.setCreated_by(rs.getString("created_by"));
                    driver.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());

                    drivers.add(driver);
                }
            }
            Long endTime = System.currentTimeMillis();
            Long executionTime = endTime - startTime;
            log.info("----------Execution time for getting all drivers : {}", executionTime + "ms");
            return drivers;

        } catch (SQLException e) {
            log.info("An error occurred while request for getting all drivers, Cause :" + e.toString());
            throw new RuntimeException("------Failed to get drivers:" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (callableSt != null) {
                    callableSt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            }
        }
    }
}
