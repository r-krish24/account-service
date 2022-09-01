package com.maveric.accountservice.constants;
import java.time.LocalDateTime;


public class Constants {

    private Constants(){
    }

    public static final String ACCOUNT_NOT_FOUND_CODE="404";
    public static final String ACCOUNT_NOT_FOUND_MESSAGE="Account not found for Id-";
    public static final String BALANCE_NOT_FOUND_MESSAGE="Balance details not found for Id-";
    public static final String ACCOUNT_DELETED_SUCCESS="Account deleted successfully.";
    public static final String METHOD_NOT_ALLOWED_CODE="405";
    public static final String METHOD_NOT_ALLOWED_MESSAGE="Method Not Allowed. Kindly check the Request URL and Request Type.";
    public static final String BAD_REQUEST_CODE="400";
    public static final String BAD_REQUEST_MESSAGE="Invalid inputs!";
    public static final String INCORRECT_URL_CODE="404";
    public static final String INCORRECT_URL_MESSAGE="The server can not find the requested resource.";
    public static LocalDateTime getCurrentDateTime() {
        return (java.time.LocalDateTime.now());
    }
}
