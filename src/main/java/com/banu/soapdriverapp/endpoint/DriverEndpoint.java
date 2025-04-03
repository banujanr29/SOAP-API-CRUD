package com.banu.soapdriverapp.endpoint;

import com.banu.soapdriverapp.entity.Driver;
import com.banu.soapdriverapp.model.GetAllDriversRequest;
import com.banu.soapdriverapp.model.GetAllDriversResponse;
import com.banu.soapdriverapp.service.DriverService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class DriverEndpoint {

    private static final String NAMESPACE_URI = "http://www.banu.com/soapdriverapp/model";
    private final DriverService driverService;

    public DriverEndpoint(DriverService driverService) {
        this.driverService = driverService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllDriversRequest")
    @ResponsePayload
    public GetAllDriversResponse getAllDrivers(@RequestPayload GetAllDriversRequest request) {
        return driverService.getAllDrivers(request);
    }

}
