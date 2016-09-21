/**
 * Created by Chikai on 16/9/21.
 */
class DisplayContextInOtherClassDemo {

    Configuration config

    def displaySpringContextUse()
    {
        // get the context through the applicationContextProvider
        def contextProvider = new ApplicationContextProvider()

        //get the Object from the bean by using context
        config = contextProvider.applicationContext.getBean("democonfig")

        //Do somthing else....
        println("get Object from application provider with user name = " + config.getUser())
        println("get Object from application provider with password = " + config.password) // groovy able direct use variable, no need the get method

    }

}
