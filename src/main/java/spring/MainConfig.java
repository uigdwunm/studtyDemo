package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.testBean.BBean;
import spring.testBean.CBean;

@Configuration
@ComponentScan(basePackages = {"spring.testBean"})
@Import({BBean.class})
public class MainConfig {

    @Bean
    public CBean cBean() {
        return new CBean();
    }
}
