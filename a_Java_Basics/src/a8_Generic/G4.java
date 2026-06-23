package a8_Generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//例：泛型练习

class DAO<T> {

    private Map<String, T> map = new HashMap<>();

    //保存T类型的对象到Map成员变量中
    public void save(String id, T entity) {
        map.put(id, entity);
    }

    //从map中获取id对应的对象
    public T get(String id) {
        return map.get(id);
    }

    //替换map中key为id的内容,改为entity对象
    public void update(String id, T entity) {
        if (!map.containsKey(entity)) {
            map.put(id, entity);
        }
    }

    //返回map中存放的所有T对象
    public ArrayList<T> List() {
        ArrayList<T> arrayList = new ArrayList<>();
        Collection<T> values = map.values();
        for (T t : values) {
            arrayList.add(t);
        }
        return arrayList;
    }

    //删除指定id对象
    public void delete(String id) {
        map.remove(id);
    }

}

class User {

    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        super();
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return age == other.age && id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", age=" + age + ", name=" + name + "]";
    }

}

public class G4 {

    public static void main(String[] args) {

        DAO<User> dao = new DAO<>();
        dao.save("a001", new User(001, 21, "Tom"));
        dao.save("a002", new User(002, 24, "Amy"));
        dao.save("a003", new User(003, 23, "JC"));

        dao.update("a003", new User(003, 22, "JC"));
        dao.delete("a002");

        List<User> list = dao.List();
        list.forEach(System.out::println);
			/*
			相当于：
				for(User u:list){
					System.out.println(u);
				}
			注：数组没有直接的forEach，要用for(Object o:arr)
			 */

    }

}
