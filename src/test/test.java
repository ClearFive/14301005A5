package test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.jdom.JDOMException;

import ioc.ApplicationContext;
import ioc.ClassPathXmlApplicationContext;

public class test {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, JDOMException, IOException {
        String[] locations = {"bean.xml"};
        ApplicationContext ctx = 
		    new ClassPathXmlApplicationContext(locations);
        boss boss = (boss) ctx.getBean("boss");
        boss.tostring();
        System.out.println(boss.tostring());
    }
}