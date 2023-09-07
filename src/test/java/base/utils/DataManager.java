package base.utils;

import java.io.InputStream;
import java.util.Properties;

public class DataManager {

    private static DataManager manager;
    private static final Properties prop = new Properties();

    private DataManager() {
        try {
            InputStream file = DataManager.class.getResourceAsStream("/config.properties");
            prop.load(file);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DataManager getInstance(){
        if(manager==null){
            synchronized (DataManager.class){
                    manager = new DataManager();
            }
        }
        return manager;
    }

    public String getProp(String key){
        return System.getProperty(key, prop.getProperty(key));
    }
}
