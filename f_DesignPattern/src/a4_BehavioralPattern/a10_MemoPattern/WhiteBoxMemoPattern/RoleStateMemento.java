package a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern;

/**
 * ClassName: RoleStateMemento
 * Package: a4_BehavioralPattern.a10_MemoPattern.WhiteBoxMemoPattern
 * Description:备忘录角色类
 *
 * @Author: zgh296
 * @Create: 2023/4/26 - 14:33
 * @Version: v1.0
 */
public class RoleStateMemento {

    private int vit; // 生命
    private int atk; // 攻击
    private int def; // 防御

    public RoleStateMemento(int vit, int atk, int def) {
        this.vit = vit;
        this.atk = atk;
        this.def = def;
    }

    public RoleStateMemento() {
    }

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

}
