package com.banu.soapdriverapp.config;

import com.banu.soapdriverapp.dao.DriverDao;
import com.banu.soapdriverapp.dao.impl.DriverDaoImpl;
import com.banu.soapdriverapp.endpoint.DriverEndpoint;
import com.banu.soapdriverapp.service.DriverService;
import com.banu.soapdriverapp.service.impl.DriverServiceImpl;
import jakarta.xml.soap.MessageFactory;
import jakarta.xml.soap.SOAPConstants;
import jakarta.xml.soap.SOAPException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadRootSmartSoapEndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

    private static final String NAMESPACE_URI = "http://www.banu.com/soapdriverapp/model";

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);  // This enables automatic WSDL generation
        return new ServletRegistrationBean<>(servlet, "/ws/*");
  }
//
//    @Bean(name = "soap12")
//    public MessageFactory messageFactory() throws SOAPException {
//        return MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
//    }



    @Bean
    public XsdSchema driverSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/driver.xsd"));
    }

    @Bean(name = "driver")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("DriverPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace(NAMESPACE_URI);
        wsdl11Definition.setSchema(schema);
        wsdl11Definition.setCreateSoap11Binding(true); // Explicitly enable SOAP 1.1 binding
        return wsdl11Definition;
    }

    @Bean
    public DriverEndpoint driverEndpoint(DriverService driverService) {
        return new DriverEndpoint(driverService);
    }

    @Bean
    public DriverService driverService(DriverDao driverDao) {
        return new DriverServiceImpl(driverDao);
    }

    @Bean
    public DriverDao driverDao(JdbcTemplate jdbcTemplate) {
        return new DriverDaoImpl(jdbcTemplate);
    }

    @Bean
    public Jaxb2Marshaller getJaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("com.banu.soapdriverapp.model");
        return jaxb2Marshaller;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller jaxb2Marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(jaxb2Marshaller);
        webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
        return webServiceTemplate;
    }

//    @Bean
//    public PayloadRootSmartSoapEndpointInterceptor interceptor() {
//        PayloadLoggingInterceptor loggingInterceptor = new PayloadLoggingInterceptor();
//        String localPart = "getAllDriversRequest";  // Adjust for your SOAP operation
//
//        // Pass marshaller, unmarshaller, namespace, and local part to the interceptor
//        return new PayloadRootSmartSoapEndpointInterceptor(loggingInterceptor, NAMESPACE_URI, localPart);
//    }
}
