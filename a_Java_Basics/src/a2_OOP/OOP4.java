package a2_OOP;

/*
四种权限(大->小)：public protected 缺省 private
				类内部    同一个包    不同包的子类    同一工程
				class	  package	   extends		 project
	public：	 yes		yes			 yes		   yes
	protected：	 yes		yes			 yes
	缺省：		 yes		yes
	private：	 yes
 */

/*
封装的特点：
	只能通过规定的方法访问数据。
	隐藏类的实例细节，方便修改和实现。

实现封装的具体步骤：
	修改属性的可见性来限制对属性的访问，一般设为 private。
	为每个属性创建一对赋值（set）方法和取值（get）方法，一般设为 public，用于属性的读写。
	在赋值和取值方法中，加入属性控制语句（对属性值的合法性进行判断）。
*/
class Employee {

    private String name; // 姓名
    private int age; // 年龄
    private String phone; // 联系电话
    private String address; // 家庭住址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18 || age > 40) {
            System.out.println("年龄必须在18到40之间！");
            this.age = 20; // 默认年龄
        } else {
            this.age = age;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

public class OOP4 {

	/*
	递归
		1.占用空间，节省时间
		2.一定要有退出条件
	 */

    //例1：用递归计算任意自然数总加和
    public static int getSum(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n + getSum(n - 1);
        }
    }
	/*
	递归程序解释：
		先进行参数传递n=5，方法进入else语句返回5+getSum(4)，之后不断递归，直到n=1时，
	进入if语句返回n=1，此时已经有五次判断返回五个值了，这些值全部压入栈中，在getSum
	这个方法返回1时正式结束了，此时系统要把压入栈的数据全部压出，注意压出顺序为先进先
	出，因为n=1先出栈，所以n初始出栈值为1，之后便是n-1组递归返回值n+getSum(n-1)，
	这样一直加，加到最后一组数据出栈，最后getSum返回的是15。
	 */

    //例2：f(0)=1,f(1)=4,f(n+2)=f(n+1)*2+f(n),n>0,求f(10).
    public static int FeiBo(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 4;
        } else {
            return 2 * FeiBo(n - 1) + FeiBo(n - 2);
        }
    }

    public static void main(String[] args) {

        System.out.println(getSum(5));

        System.out.println(FeiBo(10));

        Employee people = new Employee();
        people.setName("王丽丽");
        people.setAge(35);
        people.setPhone("13653835964");
        people.setAddress("河北省石家庄市");
        System.out.println("姓名：" + people.getName());
        System.out.println("年龄：" + people.getAge());
        System.out.println("电话：" + people.getPhone());
        System.out.println("家庭住址：" + people.getAddress());

    }

}
