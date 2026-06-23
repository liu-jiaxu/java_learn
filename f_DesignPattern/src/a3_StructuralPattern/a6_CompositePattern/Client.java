package a3_StructuralPattern.a6_CompositePattern;

import a1_DesignPattern.DesignPhilosophy.a3_DependentReversal_Principle.Computer;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a6_CompositePattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/22 - 20:29
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建菜单树

        // 创建菜单
        MenuComponent menuComponent1 = new Menu("菜单管理",2);
        MenuComponent menuComponent2 = new Menu("权限配置",2);
        MenuComponent menuComponent3 = new Menu("角色管理",2);
        MenuComponent menuComponent4 = new Menu("页面访问",3);

        // 创建菜单项
        menuComponent1.add(new MenuItem("页面访问",3));
        menuComponent1.add(new MenuItem("展开菜单",3));
        menuComponent1.add(new MenuItem("编辑菜单",3));
        menuComponent1.add(new MenuItem("删除菜单",3));
        menuComponent1.add(new MenuItem("新增菜单",3));

        menuComponent2.add(new MenuItem("页面访问",3));
        menuComponent2.add(new MenuItem("提交保存",3));

        menuComponent3.add(menuComponent4);
        menuComponent3.add(new MenuItem("新增角色",3));
        menuComponent3.add(new MenuItem("修改角色",3));

        menuComponent4.add(new MenuItem("访问设置",4));

        // 创建一级菜单
        MenuComponent menuComponent = new Menu("系统管理",1);
        // 将二级菜单添加到一级菜单中
        menuComponent.add(menuComponent1);
        menuComponent.add(menuComponent2);
        menuComponent.add(menuComponent3);

        // 打印菜单名称(若有子菜单一并打印)
        menuComponent.print();

    }

}
