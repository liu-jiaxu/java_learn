package a4_BehavioralPattern.a11_InterpreterPattern;

/**
 * ClassName: Plus
 * Package: a4_BehavioralPattern.a11_InterpreterPattern
 * Description:加法表达式类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 15:08
 * @Version: v1.0
 */
public class Plus extends AbstractExpression{

    // +号左右的表达式
    private AbstractExpression left;
    private AbstractExpression right;

    public Plus(AbstractExpression left,AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        // 将左右表达式结果相加
        return left.interpret(context) + right.interpret(context);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

}
