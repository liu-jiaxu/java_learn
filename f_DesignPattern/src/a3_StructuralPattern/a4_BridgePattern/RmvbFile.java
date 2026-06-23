package a3_StructuralPattern.a4_BridgePattern;

/**
 * ClassName: RmvbFile
 * Package: a3_StructuralPattern.a4_BridgePattern
 * Description:rmvb视频文件，具体实现化角色
 *
 * @Author: zgh296
 * @Create: 2023/4/21 - 19:52
 * @Version: v1.0
 */
public class RmvbFile implements VideoFile {

    @Override
    public void decode(String fileName) {
        System.out.println("rmvb视频文件：" + fileName);
    }

}
