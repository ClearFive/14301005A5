package test.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD}) //作用到类，方法，接口上等  
@Inherited //子类会继承  
public @interface Component {
	public String value() default "";
}
