package com.company.application.uitest.utilities;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/***
 * Read the config properties file. These properties can be over written by command line arguments.
 */
public class PropertyUtilities
{
    /********************** Properties ******************/

    private static final String DEFAULT_CONFIG_FILE_LOCATION = "src/uitestconfig.properties";

    public static String BASE_URL ="https://www.spicejet.com/";

    public static String FLIGHT_RESULT = "Select your Departure to Hyderabad";


}
