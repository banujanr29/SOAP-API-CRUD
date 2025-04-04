package com.banu.soapdriverapp.utils;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class DateUtil {

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDate localDate) {
        try {
            if(localDate != null) {
                GregorianCalendar calendar = GregorianCalendar.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()));
                return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } else return null;
        } catch (Exception e) {
            // Handle exceptions
            throw new RuntimeException("Error converting LocalDate to XMLGregorianCalendar", e);
        }
    }

    public static LocalDate convertXMLGregorianCalendarToLocalDateTime(XMLGregorianCalendar xmlGregorianCalendar) {

        if(xmlGregorianCalendar != null) {
            GregorianCalendar gregorianCalendar = xmlGregorianCalendar.toGregorianCalendar();
            return gregorianCalendar.toZonedDateTime().toLocalDate();
        } else return null;


    }
}
