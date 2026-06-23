package a4_BehavioralPattern.a11_InterpreterPattern;

/**
 * ClassName: Variable
 * Package: a4_BehavioralPattern.a11_InterpreterPattern
 * Description:封装变量的类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 15:06
 * @Version: v1.0
 */
public class Variable extends AbstractExpression{

    // 声明存储变量名的成员变量
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int interpret(Context context) {
        // 直接返回变量的值
        return context.getValue(this);
    }

    public String toString(){
        return name;
    }

}
