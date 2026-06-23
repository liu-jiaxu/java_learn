package a1_DesignPattern.DesignPhilosophy.a5_DimittLaw;

import java.util.ArrayList;

/**
 * ClassName: Client_Eager_Singleton
 * Package: a1_DesignPattern.DesignPhilosophy.a5_DimittLaw
 * Description:
 *
 * @Author: zgh296
 * @Create: 2023/4/10 - 21:13
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        Agent agent = new Agent();
        agent.setStar(new Star("刘德华"));
        agent.setFans(new Fans("小明"));
        agent.setCompany(new Company("黑马媒体公司"));
        agent.meeting();
        agent.business();

    }

}
