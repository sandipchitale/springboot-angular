package sandipchitale.springbootangular;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringbootAngularApplication {
	@RestController
	public static class IndexController {
		private final ApplicationContext applicationContext;

		public IndexController(ApplicationContext applicationContext) {
			this.applicationContext = applicationContext;
		}

		@GetMapping("/beandefinitionnames")
	    public String[] beanDefinitionNames() {
			AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
			if (autowireCapableBeanFactory instanceof DefaultListableBeanFactory defaultListableBeanFactory) {
				return defaultListableBeanFactory.getBeanDefinitionNames();
			}
			return new String[0];
	    }

		@GetMapping("/configurationproperties")
	    public String[] configurationProperties() {
			return ConfigurationPropertiesBean.getAll(applicationContext).keySet().toArray(new String[0]);
	    }
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAngularApplication.class, args);
	}

}
