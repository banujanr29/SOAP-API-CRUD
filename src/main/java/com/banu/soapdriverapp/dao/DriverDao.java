package com.banu.soapdriverapp.dao;

import com.banu.soapdriverapp.entity.Driver;

import java.util.List;

public interface DriverDao {

     List<Driver> getAllDrivers();

     Driver getDriverById(int id);

     Integer addDriver(Driver driver);

     boolean updateDriver(int id, Driver driver);

     void deleteDriver(int id);
}
