package a4_BehavioralPattern.a9_VisitorPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Home
 * Package: a4_BehavioralPattern.a9_VisitorPattern
 * Description:对象结构类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 10:50
 * @Version: v1.0
 */
public class Home {

    // 声明集合对象用于存储元素对象
    private List<Animal> nodeList = new ArrayList<Animal>();

    // 添加元素的功能
    public void add(Animal animal){
        nodeList.add(animal);
    }

    public void action(Person person){
        // 遍历集合，获取每个元素，让访问者访问每一个元素
        for (Animal animal : nodeList) {
            animal.accept(person);
        }
    }

}
