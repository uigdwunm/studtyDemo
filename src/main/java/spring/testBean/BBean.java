package spring.testBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@Scope()
public class BBean {

    @Autowired
    private ABean aBean;
}
