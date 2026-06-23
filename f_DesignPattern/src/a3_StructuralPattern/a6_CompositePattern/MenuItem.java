package a3_StructuralPattern.a6_CompositePattern;

/**
 * ClassName: MenuItem
 * Package: a3_StructuralPattern.a6_CompositePattern
 * Description:菜单项类，属于叶子节点
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 20:28
 * @Version: v1.0
 */
public class MenuItem extends MenuComponent{

    public MenuItem(String name, int level){
        this.name = name;
        this.level = level;
    }

    @Override
    public void print() {
        // 打印菜单项的名称
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);
    }

}
