package a3_StructuralPattern.a6_CompositePattern;

/**
 * ClassName: MenuComponent
 * Package: a3_StructuralPattern.a6_CompositePattern
 * Description:菜单组件，属于抽象根节点
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 20:18
 * @Version: v1.0
 */
public abstract class MenuComponent {

    // 菜单组件的名称
    protected String name;
    // 菜单组件的层级
    protected int level;

    // 添加子菜单
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }

    // 移除子菜单
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }

    // 获取指定子菜单
    public MenuComponent getChild(int index){
        throw new UnsupportedOperationException();
    }

    // 获取菜单或者菜单项的名称
    public String getName(){
        return name;
    }

    // 打印菜单名称方法(菜单和菜单项)
    public abstract void print();

}
