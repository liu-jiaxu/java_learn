package test;

import org.junit.jupiter.api.Test;

/**
 * ClassName: T3
 * Package: a1_Basics.test
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/9/8 - 8:19
 * @Version: v1.0
 */

public class T3 {

}

abstract class A {

    private int numa = 10;

    public int getNuma() {
        return numa;
    }

    public void setNuma(int numa) {
        this.numa = numa;
    }

    public abstract void showA();

}

abstract class B extends A {

     private int numb = 20;

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public abstract void showB();

}

class C extends B {

    private int numc =30;

    @Override
    public void showA() {
        System.out.println(getNuma());
    }

    @Override
    public void showB() {
        System.out.println(getNumb());
    }

    public void showC() {
        System.out.println(numc);
    }

}

class TestModel {

    @Test
    public void test() {

        C c = new C();
        c.showA();
        c.showB();
        c.showC();

    }

}



abstract class Poultry {

    private String name;
    private String symptom;
    private int age;
    private String illness;

    public Poultry() {
    }

    public Poultry(String name, String symptom, int age, String illness) {
        this.name = name;
        this.symptom = symptom;
        this.age = age;
        this.illness = illness;
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

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public abstract void showSymptom();

    public void showMsg() {
        System.out.println(symptom);
    }

    @Override
    public String toString() {
        return "Poultry{" +
                "name=" + name +
                ", symptom='" + symptom + '\'' +
                ", age=" + age +
                ", illness='" + illness + '\'' +
                '}';
    }

}

class Duck extends Poultry {

    public Duck() {
    }

    public Duck(String name, String symptom, int age, String illness) {
        super(name, symptom, age, illness);
    }

    @Override
    public void showSymptom() {
        System.out.println(getSymptom());
    }

}

class TestModel2 {

    @Test
    public void test() {

        Poultry p = new Duck("jerry", "生病", 4, "感冒");
        p.showSymptom();

    }

}
