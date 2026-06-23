package a3_StructuralPattern.a4_BridgePattern;

/**
 * ClassName: Client
 * Package: a3_StructuralPattern.a4_BridgePattern
 * Description:测试类
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 19:57
 * @Version: v1.0
 */
public class Client {

    public static void main(String[] args) {

        // 创建mac系统对象
        OpratingSystem system = new Mac(new AviFile());
        // 使用操作系统播放视频文件
        system.play("战狼3");

    }

}
