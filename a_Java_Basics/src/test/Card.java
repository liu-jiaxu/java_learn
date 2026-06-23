package test;

import org.junit.jupiter.api.Test;

/**
 * ClassName: Card
 * Package: a1_Basics.test
 * Description:扑克牌花色
 *
 * @Author: zgh296
 * @Create: 2023/9/7 - 12:10
 * @Version: v1.0
 */

public class Card {

    private String ds;
    private String hs;

    public Card() {
    }

    public Card(String ds, String hs) {
        this.ds = ds;
        this.hs = hs;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }

    public void showCard() {
        System.out.println(ds + hs);
    }

    @Override
    public String toString() {
        return "Card{" +
                "ds='" + ds + '\'' +
                ", hs='" + hs + '\'' +
                '}';
    }

}

class TestCard {

    @Test
    public void test() {

        new Card("黑桃", "A").showCard();

    }

}
