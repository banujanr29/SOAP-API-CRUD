package com.banu.soapdriverapp.service.impl;

import com.banu.soapdriverapp.dao.DriverDao;
import com.banu.soapdriverapp.entity.Driver;
import com.banu.soapdriverapp.model.GetAllDriversRequest;
import com.banu.soapdriverapp.model.GetAllDriversResponse;
import com.banu.soapdriverapp.service.DriverService;
import com.banu.soapdriverapp.utils.DateUtil;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverDao driverDao;

    public DriverServiceImpl(DriverDao driverDao) {
        this.driverDao = driverDao;
    }


    @Override
    public GetAllDriversResponse getAllDrivers(GetAllDriversRequest request) {
        List<Driver> drivers = driverDao.getAllDrivers();
        GetAllDriversResponse response = new GetAllDriversResponse();
        for(Driver driver : drivers) {
            GetAllDriversResponse.Drivers driverResponse = new GetAllDriversResponse.Drivers();
            driverResponse.setId(driver.getId());
            driverResponse.setName(driver.getName());
            driverResponse.setNic(driver.getNic());
            driverResponse.setVehicleNo(driver.getVehicle_no());
            driverResponse.setVehicleType(driver.getVehicle_type());
            driverResponse.setDateOfBirth(DateUtil.convertToXMLGregorianCalendar(driver.getDate_of_birth()));
            driverResponse.setGender(driver.getGender());

            response.getDrivers().add(driverResponse);
        }
        return response;
    }
}
