package a2_OOP;

public class OOP6_Account {
    private int id; //账号
    private double balance; //余额
    private double annual; //年利率

    public OOP6_Account(int id, double balance, double annual) {
        this.id = id;
        this.balance = balance;
        this.annual = annual;
    }

    public OOP6_Account(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnual() {
        return annual;
    }

    public void setAnnual(double annual) {
        this.annual = annual;
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            System.out.println("余额不足，取款失败！");
        } else {
            balance -= amount;
            System.out.println("成功取出：" + amount);
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("成功存入：" + amount);
        }
    }

}
