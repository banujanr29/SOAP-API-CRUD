//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.04.04 at 03:10:40 PM IST 
//


package com.banu.soapdriverapp.model;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.banu.soapdriverapp.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.banu.soapdriverapp.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllDriversResponse }
     * 
     */
    public GetAllDriversResponse createGetAllDriversResponse() {
        return new GetAllDriversResponse();
    }

    /**
     * Create an instance of {@link GetAllDriversRequest }
     * 
     */
    public GetAllDriversRequest createGetAllDriversRequest() {
        return new GetAllDriversRequest();
    }

    /**
     * Create an instance of {@link GetAllDriversResponse.Drivers }
     * 
     */
    public GetAllDriversResponse.Drivers createGetAllDriversResponseDrivers() {
        return new GetAllDriversResponse.Drivers();
    }

    /**
     * Create an instance of {@link AddDriverRequest }
     * 
     */
    public AddDriverRequest createAddDriverRequest() {
        return new AddDriverRequest();
    }

    /**
     * Create an instance of {@link AddDriverResponse }
     * 
     */
    public AddDriverResponse createAddDriverResponse() {
        return new AddDriverResponse();
    }

    /**
     * Create an instance of {@link GetDriverByIdRequest }
     * 
     */
    public GetDriverByIdRequest createGetDriverByIdRequest() {
        return new GetDriverByIdRequest();
    }

    /**
     * Create an instance of {@link GetDriverByIdResponse }
     * 
     */
    public GetDriverByIdResponse createGetDriverByIdResponse() {
        return new GetDriverByIdResponse();
    }

    /**
     * Create an instance of {@link DeleteDriverRequest }
     * 
     */
    public DeleteDriverRequest createDeleteDriverRequest() {
        return new DeleteDriverRequest();
    }

    /**
     * Create an instance of {@link DeleteDriverResponse }
     * 
     */
    public DeleteDriverResponse createDeleteDriverResponse() {
        return new DeleteDriverResponse();
    }

    /**
     * Create an instance of {@link UpdateDriverRequest }
     * 
     */
    public UpdateDriverRequest createUpdateDriverRequest() {
        return new UpdateDriverRequest();
    }

    /**
     * Create an instance of {@link UpdateDriverResponse }
     * 
     */
    public UpdateDriverResponse createUpdateDriverResponse() {
        return new UpdateDriverResponse();
    }

}
