package spotifyOAuth2.utils;

import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader()  {
        try {
            properties = PropertyUtils.propertyLoader("src/test/resources/globalConfig.properties");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigLoader getInstance() {
        if(configLoader==null)
        {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getClientID()
    {
        String prop = properties.getProperty("client_id");
        if(prop==null)
        {
            throw new RuntimeException("Client_Id not present in Properties file");
        }
        else return prop;
    }
    public String getClientSecret()
    {
        String prop = properties.getProperty("client_secret");
        if(prop==null)
        {
            throw new RuntimeException("client_secret not present in Properties file");
        }
        else return prop;
    }
    public String getGrantType()
    {
        String prop = properties.getProperty("grant_type");
        if(prop==null)
        {
            throw new RuntimeException("grant_type not present in Properties file");
        }
        else return prop;
    }
    public String getRefreshToken()
    {
        String prop = properties.getProperty("refresh_token");
        if(prop==null)
        {
            throw new RuntimeException("refresh_token not present in Properties file");
        }
        else return prop;
    }
    public String getUserID()
    {
        String prop = properties.getProperty("user_id");
        if(prop==null)
        {
            throw new RuntimeException("user_id not present in Properties file");
        }
        else return prop;
    }
    public String getBaseURI(String env)
    {

        String prop = properties.getProperty("base_uri_"+env);
        if(prop==null)
        {
            throw new RuntimeException("base_uri_"+env+" not present in Properties file");
        }
        else return prop;
    }
}
