package com.banu.soapdriverapp.dao.impl;

import com.banu.soapdriverapp.dao.DriverDao;
import com.banu.soapdriverapp.entity.Driver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    @Override
    public Driver getDriverById(int id) {
        Long startTime = System.currentTimeMillis();
        Connection connection = null;
        CallableStatement callableSt = null;
        ResultSet rs = null;
        Driver driver = null;

        try{
            startTime = System.currentTimeMillis();
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableSt = connection.prepareCall(GET_DRIVER_BY_ID);
            callableSt.setInt(1, id);
            callableSt.execute();
            rs = callableSt.getResultSet();

            if(rs != null && rs.next()){
                driver = new Driver();
                driver.setId(rs.getInt("id"));
                driver.setName(rs.getString("name"));
                driver.setNic(rs.getString("nic"));
                driver.setVehicle_no(rs.getString("vehicle_no"));
                driver.setVehicle_type(rs.getString("vehicle_type"));
                driver.setDate_of_birth(rs.getDate("date_of_birth").toLocalDate());
                driver.setGender(rs.getString("gender"));
                driver.setCreated_by(rs.getString("created_by"));
                driver.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            } else {

                throw new EmptyResultDataAccessException("No driver found with id " + id, 1);
            }

            Long endTime = System.currentTimeMillis();
            log.info("----------Execution time for getting all drivers : {}", (endTime - startTime));
            return driver;

        } catch (SQLException e) {
            log.error("An error occurred while request for getting driver by ID: {}, Cause :" + e.toString());
            throw new RuntimeException("------Failed to get driver with id " + id + ": " + e.getMessage(), e);        }
        finally {
            try {
                if(rs != null) {
                    rs.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(callableSt != null) {
                    callableSt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            if(connection != null){
                DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            }
        }

    }

    @Override
    public Integer addDriver(Driver driver) {
        Long startTime = System.currentTimeMillis();
        Connection connection = null;
        CallableStatement callableSt = null;
        Integer driverId = null;
        ResultSet resultSet = null;

        java.sql.Date sqlDate = (driver.getDate_of_birth() != null) ? Date.valueOf(driver.getDate_of_birth()) : null;

        try{
            startTime = System.currentTimeMillis();
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableSt = connection.prepareCall(INSERT_DRIVER);
            callableSt.setString(1, driver.getName());
            callableSt.setString(2, driver.getNic());
            callableSt.setString(3, driver.getVehicle_no());
            callableSt.setString(4, driver.getVehicle_type());
            callableSt.setDate(5, sqlDate);
            callableSt.setString(6, driver.getGender());
            callableSt.setString(7, driver.getCreated_by());
            callableSt.execute();

            resultSet = callableSt.getResultSet();

            resultSet.next();

            driverId = resultSet.getInt(1);


            Long endTime = System.currentTimeMillis();
            log.info("----------Execution time for getting all drivers : {}",(endTime - startTime));


        } catch (SQLException e) {
            log.info("An error occurred while request for getting all drivers, Cause :" + e.toString());
            throw new RuntimeException("------Failed to add driver " + ": " + e.getMessage(), e);
        } finally {
            try {
                if(callableSt != null) {
                    callableSt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            if(connection != null){
                DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            }
        }

        return driverId;

    }

    @Override
    public boolean updateDriver(int id, Driver driver) throws EmptyResultDataAccessException {
        Connection connection = null;
        CallableStatement callableSt = null;
        ResultSet resultSet = null;

        java.sql.Date sqlDate = (driver.getDate_of_birth() != null) ? Date.valueOf(driver.getDate_of_birth()) : null;

        try{
            Long startTime = System.currentTimeMillis();
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableSt = connection.prepareCall(UPDATE_DRIVER_BY_ID);
            callableSt.setInt(1, id);
            callableSt.setString(2, driver.getName());
            callableSt.setString(3, driver.getNic());
            callableSt.setString(4, driver.getVehicle_no());
            callableSt.setString(5, driver.getVehicle_type());
            callableSt.setDate(6, sqlDate);
            callableSt.setString(7, driver.getGender());
            callableSt.setString(8, driver.getCreated_by());
            callableSt.execute();

            Long endTime = System.currentTimeMillis();
            log.info("----------Execution time for update driver with id {} : {}", id, (endTime - startTime));

            resultSet = callableSt.getResultSet();
            resultSet.next();
            boolean RESULT = resultSet.getBoolean(1);
            return RESULT;

        } catch (SQLException e) {
            log.info("An error occurred while request to update driver with ID {}, Cause : {}", id, e.toString());
            throw new RuntimeException("------Failed to update driver " + ": " + e.getMessage(), e);
        } finally {
            try {
                if(callableSt != null) {
                    callableSt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            if(connection != null){
                DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            }
        }
    }

    @Override
    public void deleteDriver(int id) {
        Long startTime = System.currentTimeMillis();
        Connection connection = null;
        CallableStatement callableSt = null;

        try{
            startTime = System.currentTimeMillis();
            connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            callableSt = connection.prepareCall(DELETE_DRIVER_BY_ID);
            callableSt.setInt(1, id);
            callableSt.execute();

            Long endTime = System.currentTimeMillis();
            log.info("----------Execution time for getting all drivers : {}",(endTime - startTime));


        } catch (SQLException e) {
            log.info("An error occurred while request for deleting driver, Cause :" + e.toString());

            throw new RuntimeException("------Cannot delete driver " + ": " + e.getMessage(), e);
        }finally {
            try {
                if(callableSt != null) {
                    callableSt.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            if(connection != null){
                DataSourceUtils.releaseConnection(connection, jdbcTemplate.getDataSource());
            }
        }

    }
}
