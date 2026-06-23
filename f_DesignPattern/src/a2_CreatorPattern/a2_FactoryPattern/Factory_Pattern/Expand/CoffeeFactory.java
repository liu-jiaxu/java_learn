package a2_CreatorPattern.a2_FactoryPattern.Factory_Pattern.Expand;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * ClassName: CoffeeFactory
 * Package: a2_CreatorModel.a2_FactoryPattern.Factory_Pattern.Expand
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/17 - 10:11
 * @Version: v1.0
 */
public class CoffeeFactory {

    // 加载配置文件，获取配置文件中配置的全类名，并创建该类的对象进行存储
    // 1.定义容器对象存储咖啡对象
    private static Map<String,Coffee> map = new HashMap();
    // 2.加载配置文件，仅需加载一次
    static {
        // 创建properties对象
        Properties p = new Properties();
        InputStream is = CoffeeFactory.class.getClassLoader().getResourceAsStream("a2_CreatorPattern/a2_FactoryPattern/Factory_Pattern/Expand/bean.properties");
        try {
            // load方法进行配置文件的加载
            p.load(is);
            //遍历Properties集合对象
            Set<Object> keys = p.keySet();
            for (Object key : keys) {
                //根据键获取值（全类名）
                String className = p.getProperty((String) key);
                //获取字节码对象
                Class clazz = Class.forName(className);
                Coffee obj = (Coffee) clazz.newInstance();
                map.put((String)key,obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        // 静态成员变量用来存储创建的对象（键存储的是名称，值存储的是对应的对象），而读取配置文件以及创建对象写在静态代码块中，目的就是只需要执行一次。
    public static Coffee createCoffee(String name) {
        return map.get(name);
    }

}
