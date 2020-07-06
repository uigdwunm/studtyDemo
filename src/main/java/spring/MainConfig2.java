package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.testBean.BBean;
import spring.testBean.CBean;
import spring.testBean2.DBean;

@Configuration
//@Import({BBean.class})
public class MainConfig2 {

    @Bean
    public CBean cBean() {
        return new CBean();
    }
}
