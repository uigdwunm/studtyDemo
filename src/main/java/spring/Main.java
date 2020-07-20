package spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> objects = new PriorityQueue<>();
        int[] a = new int[]{};
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MainConfig.class);
        Object aBean1 = context.getBean("ABean");
//        System.out.println(aBean1);
//        Object aBean2 = context.getBean("ABean");
//        System.out.println(aBean2);
//        System.out.println();
//        Object cBean1 = context.getBean("CBean");
//        System.out.println(cBean1);
//        Object cBean2 = context.getBean("CBean");
//        System.out.println(cBean2);
    }
}
