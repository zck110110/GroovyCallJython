import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Created by Chikai on 16/9/21.
 */
/**
 * This class provides the context of Spring, which allows other class use during running time
 * **/
@Component
class ApplicationContextProvider implements ApplicationContextAware
{
    private static ApplicationContext context

    public ApplicationContext getApplicationContext()
    {
        return context
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        this.context = context
    }
}
