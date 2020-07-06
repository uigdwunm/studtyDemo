package spring.testBean2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.testBean.CBean;

@Configuration
//@Import({BBean.class})
public class MainConfig2 {

    @Bean
    public CBean cBean() {
        return new CBean();
    }
}
