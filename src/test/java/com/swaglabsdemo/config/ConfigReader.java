//package com.swaglabsdemo.config;
//
//import com.swaglabsdemo.utils.LogUtil;
//import org.apache.logging.log4j.Logger;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static Properties properties;
//    private static Logger logger = LogUtil.getLogger(ConfigReader.class);
//
//    public ConfigReader(){
//        try(FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")){
//            properties = new Properties();
//            properties.load(fileInputStream);
//            logger.info("Config File Loaded successfully.");
//        }catch(IOException e){
//            logger.error("Could not load config.properties file. "+e);
//        }
//    }
//
//
//    // Getter methods for each config entry
//    public static String getLoginPageUrl() {
//        logger.info("urllllll read"+properties.getProperty("loginpage.url"));
//        return properties.getProperty("loginpage.url");
//    }
//
//    public static String getProductListPageUrl() {
//        return properties.getProperty("productlistpage.url");
//    }
//
//    public static String getCartPageUrl() {
//        return properties.getProperty("cartpage.url");
//    }
//
//    public static String getUsername() {
//        return properties.getProperty("username");
//    }
//
//    public static String getPassword() {
//        return properties.getProperty("password");
//    }
//}


package com.swaglabsdemo.config;

import com.swaglabsdemo.utils.LogUtil;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static Logger logger = LogUtil.getLogger(ConfigReader.class);

    // Static block to load the properties once
    static {
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
            logger.info("Config file loaded successfully.");
        } catch (IOException e) {
            logger.error("Could not load config.properties file: " + e);
        }
    }

    // Static getter methods to access config properties

    public static String getLoginPageUrl() {
        logger.info("Reading loginpage.url from properties");
        return properties.getProperty("loginpage.url");
    }

    public static String getProductListPageUrl() {
        logger.info("Reading productlistpage.url from properties");
        return properties.getProperty("productlistpage.url");
    }

    public static String getCartPageUrl() {
        logger.info("Reading cartpage.url from properties");
        return properties.getProperty("cartpage.url");
    }

    public static String getUsername() {
        logger.info("Reading username from properties");
        return properties.getProperty("username");
    }

    public static String getPassword() {
        logger.info("Reading password from properties");
        return properties.getProperty("password");
    }
}



