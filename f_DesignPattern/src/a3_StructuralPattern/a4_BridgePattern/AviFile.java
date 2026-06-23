package a3_StructuralPattern.a4_BridgePattern;

/**
 * ClassName: AviFile
 * Package: a3_StructuralPattern.a4_BridgePattern
 * Description:avi视频文件，具体实现化角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 19:51
 * @Version: v1.0
 */
public class AviFile implements VideoFile {

    @Override
    public void decode(String fileName) {
        System.out.println("avi视频文件：" + fileName);
    }

}
