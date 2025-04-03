package com.banu.soapdriverapp.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Driver {

    private Integer id;
    private String name;
    private String nic;
    private String vehicle_no;
    private String vehicle_type;
    private LocalDate date_of_birth;
    private String gender;
    private String created_by;
    private LocalDateTime created_at;
}
