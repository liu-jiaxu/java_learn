package a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern;

/**
 * ClassName: GameRole
 * Package: a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern
 * Description:游戏角色类，发起人角色
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 14:29
 * @Version: v1.0
 */
public class GameRole {

    private int vit; // 生命
    private int atk; // 攻击
    private int def; // 防御

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    // 初始化状态
    public void initState(){
        this.vit = 100;
        this.atk = 100;
        this.def = 100;
    }

    // 战斗方法
    public void fight(){
        this.vit = 0;
        this.atk = 0;
        this.def = 0;
    }

    // 保存角色状态功能
    public RoleStateMemento saveState(){
        return new RoleStateMemento(vit,atk,def);
    }

    // 恢复角色状态
    public void recoverState(RoleStateMemento roleStateMemento){
        // 将备忘录对象中存储的状态赋值给当前对象的成员
        this.vit = roleStateMemento.getVit();
        this.atk = roleStateMemento.getAtk();
        this.def = roleStateMemento.getDef();
    }

    // 展示状态
    public void stateDisplay(){
        System.out.println("生命：" + vit);
        System.out.println("攻击：" + atk);
        System.out.println("防御：" + def);
    }

}
