package com.example.redis_and_jedis;

/**
 * ClassName: RJ4_Test_Province
 * Package: com.example.redis_and_jedis
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/27 - 11:05
 * @Version: v1.0
 */
public class RJ4_Test_Province {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RJ4_Test_Province{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
