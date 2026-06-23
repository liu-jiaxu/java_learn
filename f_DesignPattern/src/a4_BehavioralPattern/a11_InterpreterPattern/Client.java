package a4_BehavioralPattern.a11_InterpreterPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a11_InterpreterPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 15:15
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建环境对象
        Context context = new Context();
        // 创建多个变量对象
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");
        Variable d = new Variable("d");

        // 将变量存储到环境对象中
        context.assign(a,1);
        context.assign(b,2);
        context.assign(c,3);
        context.assign(d,4);

        // 获取抽象语法树 a-((b-c)+d)
        AbstractExpression abstractExpression = new Minus(a,new Plus(new Minus(b,c),d));
        // 获取抽象语法树 a-b+c+d
        AbstractExpression abstractExpression2 = new Plus(new Plus(new Minus(a,b),c),d);

        // 解释，计算
        int interpret = abstractExpression.interpret(context);
        System.out.println(abstractExpression + " = " + interpret);
        int interpret2 = abstractExpression2.interpret(context);
        System.out.println(abstractExpression2 + " = " + interpret2);

    }

}
