package gitflow;

/**
 * @ClassName: GitFlow
 * @Package: git.gitflow
 * @Description:
 * @Author: 刘家旭
 * @Create: 2026/6/24 - 16:54
 * @Version: v1.0
 */

public class GitFlow {

    /*
    gitflow工作流程
    相当于git命令与流程补充

    1.五大分支
    主分支main/master 生产环境分支，总分支，项目交付分支
    热修复分支hotfix  main分支出现bug，拉取hotfix分支用于修改bug，之后合并回最新main和develop分支
    发布分支release  准备分布分支（可能用于测试，体验版功能等），以develop为基础创建，这个分支只添加说明文档，bug修复，不添加额外代码
                    release分支测试完成后会合并回最新main和develop分支
    开发分支develop  各个项目组，前后端大数据部署等代码的集成分支，存放最新开发成果，以main分支为基础创建，负责向main分支合并
    功能分支feature  开发新功能使用分支，以develop分支为基础创建

    2.tag标签有什么用？
    Tag 是基于某个具体的提交（commit）创建的，不是基于分支。
    （1）标记版本：告诉别人（和未来的自己）这个提交是某个版本
    （2）方便回溯：快速找到特定版本的代码，例如之前的分支（最新commit打了tag）出问题了需要新建分支修复，那么直接以tag为基础创建新分支即可
        git checkout -b 新分支名 标签名
        无需记录分支名和commit id

    3.git stash暂存工作区（搁置）
    需求：当前开发分支工作未完成，但需要切换到其它分支修改紧急bug，临时暂存当前工作区内容
    常用命令
    命令	                        说明
    git stash	                搁置当前修改（工作区 + 暂存区）
    git stash push -m "说明"	    带说明信息搁置
    git stash list	            查看所有搁置记录
    git stash pop	            恢复最新的搁置，并删除记录
    git stash apply	            恢复最新的搁置，但保留记录
    git stash drop	            删除最新的搁置记录
    git stash clear	            清空所有搁置记录

    4.fetch与pull的区别
    git fetch 从远程拉取最新代码，但是不会修改本地代码，只会预览
    git pull 远程拉取代码

    5.merge & rebase
    merge保留全部历史记录
    rebase只会有主线记录
    git pull --rebase
     */

}
