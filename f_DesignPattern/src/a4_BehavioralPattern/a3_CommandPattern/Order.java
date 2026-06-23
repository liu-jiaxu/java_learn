package a4_BehavioralPattern.a3_CommandPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: Order
 * Package: a4_BehavioralPattern.a3_CommandPattern
 * Description:订单类
 *
 * @Author: zgh296
 * @Create: 2023/4/23 - 16:37
 * @Version: v1.0
 */
public class Order {

    // 餐桌号码
    private int diningTable;

    // 所下的餐品及份数
    private Map<String,Integer> foodDir = new HashMap<String,Integer>();

    public int getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(int diningTable) {
        this.diningTable = diningTable;
    }

    public Map<String, Integer> getFoodDir() {
        return foodDir;
    }

    public void setFoodDir(Map<String, Integer> foodDir) {
        this.foodDir = foodDir;
    }

    public void setFood(String food,int num) {
        foodDir.put(food,num);
    }

}
