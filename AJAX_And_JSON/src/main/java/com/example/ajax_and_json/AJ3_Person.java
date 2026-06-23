package com.example.ajax_and_json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * ClassName: AJ3_Person
 * Package: com.example.ajax_and_json
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/3/23 - 15:45
 * @Version: v1.0
 */
public class AJ3_Person {

    private String name;
    private int age;
    private String gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
        // 该注解规定此属性输出后的格式
    private Date birthDate;

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
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "AJ3_Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

}
