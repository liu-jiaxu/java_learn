package a3_StructuralPattern.a4_BridgePattern;

/**
 * ClassName: OpratingSystem
 * Package: a3_StructuralPattern.a4_BridgePattern
 * Description:抽象操作系统类，抽象化角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 19:53
 * @Version: v1.0
 */
public abstract class OpratingSystem {

    // 声明videoFile变量
    protected VideoFile videoFile;

    public OpratingSystem(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public abstract void play(String fileName);

}
