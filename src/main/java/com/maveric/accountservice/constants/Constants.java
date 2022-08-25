package com.maveric.accountservice.constants;
import java.time.LocalDateTime;


public class Constants {

    private Constants()
    {

    }
    public static LocalDateTime getCurrentDateTime() {
        return (java.time.LocalDateTime.now());
    }
}
