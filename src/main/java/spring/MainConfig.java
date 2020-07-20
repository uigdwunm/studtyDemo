package spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.testBean.BBean;
import spring.testBean2.MainConfig2;
import spring.testBean2.DBean;

@Configuration
@ComponentScan(basePackages = {"spring.testBean"})
//@Import({MainConfig2.class})
public class MainConfig {

//    @Bean
//    public DBean dBean() {
//        return new DBean();
//    }

//    @Bean
//    public MainConfig2 mainConfig2() {
//        return new MainConfig2();
//    }
}
