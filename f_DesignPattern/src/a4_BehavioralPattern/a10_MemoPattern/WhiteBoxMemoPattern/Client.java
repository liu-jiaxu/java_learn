package a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern;

/**
 * ClassName: Client
 * Package: a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 14:38
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        System.out.println("大战BOSS前");
        // 创建游戏角色对象
        GameRole gameRole = new GameRole();
        // 初始状态操作
        gameRole.initState();
        gameRole.stateDisplay();

        // 将该游戏角色内部状态进行备份
        // 创建管理者对象
        RoleStateCaretaker roleStateCaretaker = new RoleStateCaretaker();
        roleStateCaretaker.setRoleStateMemento(gameRole.saveState());

        System.out.println("大战BOSS后");
        // 损耗严重
        gameRole.fight();
        // 展示状态
        gameRole.stateDisplay();

        System.out.println("恢复状态");
        gameRole.recoverState(roleStateCaretaker.getRoleStateMemento());
        gameRole.stateDisplay();

    }

}
