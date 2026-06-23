package a3_StructuralPattern.a4_BridgePattern;

/**
 * ClassName: Mac
 * Package: a3_StructuralPattern.a4_BridgePattern
 * Description:拓展抽象化角色mac
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 19:56
 * @Version: v1.0
 */
public class Mac extends OpratingSystem{

    public Mac(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void play(String fileName) {
        videoFile.decode(fileName);
    }

}
