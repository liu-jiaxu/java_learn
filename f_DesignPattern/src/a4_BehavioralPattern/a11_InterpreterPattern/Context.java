package a4_BehavioralPattern.a11_InterpreterPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Context
 * Package: a4_BehavioralPattern.a11_InterpreterPattern
 * Description:环境角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 15:06
 * @Version: v1.0
 */
public class Context {

    // 定义map集合用于存储变量及对应的值
    private Map<Variable,Integer> map = new HashMap<Variable,Integer>();

    // 添加变量
    public void assign(Variable variable,Integer value) {
        map.put(variable,value);
    }

    // 根据变量获取对应值
    public int getValue(Variable variable){
        return map.get(variable);
    }

}
