package a3_StructuralPattern.a6_CompositePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Menu
 * Package: a3_StructuralPattern.a6_CompositePattern
 * Description:菜单类，属于树枝节点
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 20:23
 * @Version: v1.0
 */
public class Menu extends MenuComponent{

    // 菜单可以有多个子菜单或子菜单项
    private List<MenuComponent> menuComponentsList = new ArrayList<MenuComponent>();

    // 构造方法
    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuComponentsList.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuComponentsList.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int index) {
        return menuComponentsList.get(index);
    }

    @Override
    public void print() {
        // 打印菜单名称
        for (int i = 0; i < level; i++) {
            System.out.print("--");
        }
        System.out.println(name);
        // 打印子菜单或子菜单项名称
        for(MenuComponent component:menuComponentsList){
            component.print();
        }

    }

}
