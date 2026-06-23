package com.example.cookiesession_and_jsp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * ClassName: CJ6_Test_User
 * Package: com.example.servlet_test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/6 - 11:49
 * @Version: v1.0
 */

// 逻辑视图
public class CJ7_Test_User {

    private int id;
    private String username;
    private Date birthday;

    // 编写中文日期获取方式，在jsp中调用成员变量bir即可！
    public String getBir() {
        // 1.格式化日期对象
        // 2.返回字符串
        if(birthday != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(birthday);
        } else {
            return "";
        }
    }

    public CJ7_Test_User(int id, String username, Date birthday) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
    }

    public CJ7_Test_User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CJ7_Test_User)) return false;
        CJ7_Test_User that = (CJ7_Test_User) o;
        return getId() == that.getId() && getUsername().equals(that.getUsername()) && getBirthday().equals(that.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getBirthday());
    }

    @Override
    public String toString() {
        return "CJ7_Test_User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday=" + birthday +
                '}';
    }

}
