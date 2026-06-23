package a2_OOP;

public class OOP6_Bank {

    private OOP6_Customer[] customers;
    private int number; // 客户个数

    public OOP6_Bank() {
        customers = new OOP6_Customer[10];
    }

    // 添加客户
    public void add(String f, String l) {
        OOP6_Customer customer = new OOP6_Customer(f, l);
        customers[number] = customer;
        number++;
    }

    //获取客户个数
    public int getNumber() {
        return number;
    }

    //获取指定位置上的客户
    public OOP6_Customer getCustomer(int index) {
        if (index >= 0 && index < number) {
            return customers[index];
        }
        return null;

    }

}
