package com.banu.soapdriverapp.service.impl;

import com.banu.soapdriverapp.dao.DriverDao;
import com.banu.soapdriverapp.entity.Driver;
import com.banu.soapdriverapp.model.*;
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

    @Override
    public GetDriverByIdResponse getDriverById(GetDriverByIdRequest request) {
        Driver driver = driverDao.getDriverById(request.getId());
        GetDriverByIdResponse response = new GetDriverByIdResponse();
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setNic(driver.getNic());
        response.setVehicleNo(driver.getVehicle_no());
        response.setVehicleType(driver.getVehicle_type());
        response.setDateOfBirth(DateUtil.convertToXMLGregorianCalendar(driver.getDate_of_birth()));
        response.setGender(driver.getGender());

        return response;

    }

    @Override
    public AddDriverResponse addDriver(AddDriverRequest request) {
        Driver driver = new Driver();

        driver.setName(request.getName());
        driver.setNic(request.getNic());
        driver.setVehicle_no(request.getVehicleNo());
        driver.setVehicle_type(request.getVehicleType());
        driver.setDate_of_birth(DateUtil.convertXMLGregorianCalendarToLocalDateTime(request.getDateOfBirth()));
        driver.setGender(request.getGender());
        driver.setCreated_by(request.getCreatedBy());

        Integer driverId = driverDao.addDriver(driver);

        AddDriverResponse response = new AddDriverResponse();
        if (driverId != null) {
            response.setMessage("Driver created successfully with ID : " + driverId);
            response.setStatus("SUCCESS");
        } else {
            response.setMessage("Driver not created");
            response.setStatus("FAILED");
        }

        return response;
    }


}
