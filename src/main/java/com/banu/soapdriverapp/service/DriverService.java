package com.banu.soapdriverapp.service;

import com.banu.soapdriverapp.entity.Driver;
import com.banu.soapdriverapp.model.GetAllDriversRequest;
import com.banu.soapdriverapp.model.GetAllDriversResponse;

import java.util.List;

public interface DriverService {

    public GetAllDriversResponse getAllDrivers(GetAllDriversRequest request);

}