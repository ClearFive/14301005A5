package ioc;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
public class ClassPathXmlApplicationContext implements ApplicationContext
{
	private Map<String, Object> beans = new HashMap<String, Object>();
	@SuppressWarnings("unchecked")
	public ClassPathXmlApplicationContext(String[] locations) throws JDOMException, IOException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException
	{
		SAXBuilder sb = new SAXBuilder();
		for(int n=0;n<locations.length;n++){
		// 构造文档对象
		Document doc = sb.build(ClassPathXmlApplicationContext.class
				.getClassLoader().getResourceAsStream(locations[n]));
		// 获取根元素
		Element root = doc.getRootElement();
		// 取到根元素所有元素
		List list = root.getChildren();
		
		for (int i = list.size()-1; i > -1; i--)
		{
			Element element = (Element) list.get(i);
			// 取id子元素
			String beanid = element.getAttributeValue("id");
			// 取class子元素
			String clzss = element.getAttributeValue("class");
			// 实例化
			Object o = Class.forName(clzss).newInstance();
			// 将所有bean放入map中
			beans.put(beanid, o);
			// 获取property 进行依赖注入
			for (Element propertyElement : (List<Element>) element
					.getChildren("property"))
			{
				
				String name = propertyElement.getAttributeValue("name");
				String ref = propertyElement.getAttributeValue("ref");
				String value = propertyElement.getAttributeValue("value");
				
				if(value!=null){
					String methodName = "set" + name.substring(0, 1).toUpperCase()
							+ name.substring(1);
					Method m =o.getClass().getMethod(methodName, value.getClass());
					m.invoke(o, value);
				}else{
					
					if(!beans.containsKey(name)){
						Object ob0 = Class.forName("test."+ref).newInstance();
						beans.put(name, ob0);	
					}
				// 从bean.xml中根据ref取到类的对象
				Object beanObj = this.getBean(ref);
				String methodName = "set" + name.substring(0, 1).toUpperCase()
							+ name.substring(1);
				Method m = o.getClass().getMethod(methodName,
							beanObj.getClass());
				m.invoke(o, beanObj);
				
				}
				
			}
			
		}
	}
	
}
	public Object getBean(String name)
	{
		return beans.get(name);
	}
}
