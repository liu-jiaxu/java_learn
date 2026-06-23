package a3_StructuralPattern.a4_BridgePattern;

/**
 * ClassName: Windows
 * Package: a3_StructuralPattern.a4_BridgePattern
 * Description:拓展抽象化角色windows
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 19:55
 * @Version: v1.0
 */
public class Windows extends OpratingSystem {

    public Windows(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void play(String fileName) {
        videoFile.decode(fileName);
    }

}
