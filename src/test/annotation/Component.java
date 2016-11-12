package test.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD}) //���õ��࣬�������ӿ��ϵ�  
@Inherited //�����̳�  
public @interface Component {
	public String value() default "";
}
