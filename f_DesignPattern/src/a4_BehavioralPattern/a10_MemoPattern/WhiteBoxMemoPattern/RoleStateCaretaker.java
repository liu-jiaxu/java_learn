package a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern;

/**
 * ClassName: RoleStateCaretaker
 * Package: a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern
 * Description:备忘录对象管理对象
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 14:37
 * @Version: v1.0
 */
public class RoleStateCaretaker {

    // 声明备忘录类型的变量
    private RoleStateMemento roleStateMemento;

    public RoleStateMemento getRoleStateMemento() {
        return roleStateMemento;
    }

    public void setRoleStateMemento(RoleStateMemento roleStateMemento) {
        this.roleStateMemento = roleStateMemento;
    }

}
