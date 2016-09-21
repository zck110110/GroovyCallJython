

import org.springframework.beans.factory.InitializingBean
import org.apache.commons.lang3.StringUtils
/**
 * Created by Chikai on 16/9/21.
 * The Advantage use the properties file + One configuration class is that:
 *  1. easier to manage large configuration setting than edit spring xml
 *  2. provide a centralize class to setting things
 */
class Configuration implements InitializingBean {

    /**
     * This is the properties need to set
     * **/
    private String user
    private String password
    // able to add more and more....

    /**
     * This is the properties file name and local, which is injected by Spring
     * **/
    String configFileName
    String configFilePath

    /**
     * This mehtod will be automatically called once Spring injects the bean
     * **/
    public void afterPropertiesSet() throws Exception {
        String configFile = configFilePath + "/" + configFileName

        //should use log here..
        println("read the properties file = " + configFile)

        File file = new File(configFile)
        InputStream is = null

        if (file.canRead())
        {
            try
            {
                is = new FileInputStream(configFile)
            }
            catch (FileNotFoundException exception)
            {
                println("can not find file")
            }
        }

        //in case of inputstream is none, try to find the file in the META
        if (is == null)
        {
            configFile = "META-INF/" + configFileName
            is = getClass().getResourceAsStream(configFile)
        }

        if (is != null)
        {
            Properties properties = new Properties()
            try
            {
                properties.load(is)
            }
            catch (IOException ioe)
            {
                println("Exception whilst loading properties from " + configFile, ioe)
            }

            println("properties = " + properties.toString())

            //start read the variable in the properties file
            String str = properties.getProperty("user")
            if (!StringUtils.isBlank(str))
            {
                println("read user is = " + str)
                user = str
            }

            str = properties.getProperty("password")
            if (!StringUtils.isBlank(str))
            {
                println("read password is = " + str)
                password = str
            }

            try{
               is.close()
            }
            catch (IOException e)
            {
                println('Unable to close the input stream, er.....')
            }
        }
    }

    String getUser() {
        return user
    }

    String getPassword() {
        return password
    }
}
