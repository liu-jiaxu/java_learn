package a4_BehavioralPattern.a1_TemplateMethodPattern;

/**
 * ClassName: AbstractClass
 * Package: a4_BehavioralPattern.a1_TemplateMethodPattern
 * Description:抽象类，定义模板方法和基本方法
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 11:00
 * @Version: v1.0
 */
public abstract class AbstractClass {

    // 模板方法定义，定义为final表示子类不能更改
    public final void cookProcess() {
        //第一步：倒油
        this.pourOil();
        //第二步：热油
        this.heatOil();
        //第三步：倒蔬菜
        this.pourVegetable();
        //第四步：倒调味料
        this.pourSauce();
        //第五步：翻炒
        this.fry();
    }

    // 基本方法定义
    // 第一步，倒油是一样的，所以直接实现
    public void pourOil() {
        System.out.println("倒油");
    }
    //第二步：热油是一样的，所以直接实现
    public void heatOil() {
        System.out.println("热油");
    }
    //第三步：倒蔬菜是不一样的（一个下包菜，一个是下菜心）
    public abstract void pourVegetable();
    //第四步：倒调味料是不一样
    public abstract void pourSauce();
    //第五步：翻炒是一样的，所以直接实现
    public void fry(){
        System.out.println("炒啊炒啊炒到熟啊");
    }

}
