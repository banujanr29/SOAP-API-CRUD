package com.banu.soapdriverapp.dao.constants;

public class DaoConstants {
    public interface Functions{

        String GET_DRIVER = "{call get_all_drivers()}";
        String INSERT_DRIVER = "{call insert_driver(?,?,?,?,?,?,?)}";
        String DELETE_DRIVER_BY_ID = "{call delete_driver_by_id(?)}";
        String GET_DRIVER_BY_ID = "{call get_driver_by_id(?)}";
        String UPDATE_DRIVER_BY_ID = "{call update_driver_by_id(?,?,?,?,?,?,?,?)}";
    }
}
