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
    private static Logger logger;

    // Static block to load the properties once
    static {
        logger = LogUtil.getLogger(ConfigReader.class);
        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
            logger.info("Config file loaded successfully.");
        } catch (IOException e) {
            logger.error("Could not load config.properties file: " + e);
        }
    }

    public static String getValue(String key){
        logger.info("Getting {}'s value from properties file.", key);
        return properties.getProperty(key);
    }


}



