// Singleton design pattern to load configuration only once

package configuration;

import configuration.Types.DriverTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class ProprietiesSingleton {

    private static ProprietiesSingleton instance;
    private static String propertyFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties";
    public Logger log = LogManager.getLogger(this.getClass().getName());

    private static String deezerWebentry;
    private static String deezerApientry;
    private static String deezerLogin;
    private static String deezerPassword;
    private static String browserDriver;

    private ProprietiesSingleton() {}

    public static ProprietiesSingleton getInstance() {
        if(instance==null) {
            instance = new ProprietiesSingleton();
            instance.loadData();
        }

        return instance;
    }

    private void loadData() {
        Properties properties = new Properties();

        try (FileInputStream propfile = new FileInputStream(propertyFilePath)) {
            properties.load(propfile);
        } catch (FileNotFoundException e) {
            log.error("Cannot find property file at " + propertyFilePath);
        } catch (IOException e) {
            log.error("Cannot open property file at " + propertyFilePath);
        }

        deezerWebentry = properties.getProperty("deezer.webentry");
        deezerApientry = properties.getProperty("deezer.apientry");
        deezerLogin = properties.getProperty("deezer.login");
        deezerPassword = properties.getProperty("deezer.password");
        browserDriver = properties.getProperty("browser.driver");

    }

    public String getDeezerWebentry() {
        return deezerWebentry;
    }

    public String getDeezerApientry() {
        return deezerApientry;
    }

    public String getDeezerLogin() {
        return deezerLogin;
    }

    public String getDeezerPassword() {
        return deezerPassword;
    }

    public DriverTypes getBrowserDriver() {
        return DriverTypes.valueOf(browserDriver);
    }
}
