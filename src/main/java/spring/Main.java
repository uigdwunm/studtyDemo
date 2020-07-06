package spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MainConfig.class);
        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
            System.out.println("bean: " + context.getBean(name));
            BeanDefinition beanDefinition = context.getBeanDefinition(name);
            System.out.println(beanDefinition);
            System.out.println();
        }
    }
}
