package com.banu.soapdriverapp.service;

import com.banu.soapdriverapp.entity.Driver;
import com.banu.soapdriverapp.model.*;

import java.util.List;

public interface DriverService {

    GetAllDriversResponse getAllDrivers(GetAllDriversRequest request);

    GetDriverByIdResponse getDriverById(GetDriverByIdRequest request);

    AddDriverResponse addDriver(AddDriverRequest request);
}