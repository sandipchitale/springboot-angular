package sandipchitale.springbootangular;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringbootAngularApplication  extends SpringBootServletInitializer {
	public static void main(String[] args) {
		launch(new SpringApplicationBuilder()).run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return launch(springApplicationBuilder);
	}

	private static SpringApplicationBuilder launch(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(SpringbootAngularApplication .class);
	}

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
}
