package git;

/**
 * ClassName: git
 * Package: git
 * Description: Git介绍
 *
 * @Author: zgh296
 * @Create: 2023/7/4 - 16:14
 * @Version: v1.0
 */
public class Git {

    /*
    1.查看文件内容：
        (1)依据GitHub客户端找到40位版本号，在.git/object文件夹中依照前两位找到对应文件夹，后38位为文件名
        (2)在当前仓库文件目录下右键 -> git Bash Here
        (3)输入命令 git cat-file -p 40位版本号，该命令会显示文件提交信息，该信息中包含另外一个版本号
        (4)继续输入命令 git cat-file -p 上一步得到的40位版本号，该命令会显示文件状态信息(权限等)，仍会得到一个版本号
        (5)继续输入命令 git cat-file -p 上一步得到的40位版本号，最终该命令会查看到文件内容

    2.GitHub底层实现逻辑
        示例：有一个git仓库，包含main主分支，和user、order关联分支，user中有u.txt，c.txt，order中有
    o.txt，c.txt，main中合并了两个关联分支，有u.txt，o.txt，c.txt(c.txt的内容合并两个关联分支的文件)
        (1).git目录下有一个HEAD文件，该文件指定此时的分支仓库，若在GitHub客户端上选择main仓库，则HEAD
    文件中的内容便修改为main，若选择其他分支则同样更改；
        (2)GitHub底层分为三大区域：工作区域，对比区域，存储区域，这三个区域中的每个文件都有不同的40位版本
    号，其操作对应上述“查看文件内容”的三个版本号，第一个版本号表示文件在工作区域的版本号，第二个版本号表示在
    存储区域的版本号，最后一个版本号表示存储区域文件的版本号。工作区域被HEAD文件指向，对比区域用于表示版本号，
    文件增删改后得到不同版本号的表示，文件内容最终存储在存储区域；
        注：对比区域又称为暂存区域！
        (3)以该仓库为例，该仓库存有三个分支，共5个文件，这五个文件依次有三个版本号，分别存储在三个区域中，当
    在客户端切换不同分支时，HEAD文件改变指向，每个分支都指向工作区域中对应的文件(即分支中包含的文件)，例如user
    分支中有u.txt，c.txt文件，则执行工作区域的u.txt，c.txt文件，此时在客户端查看user分支时，就只能看到文件
    中只有两个文件；同理当分支切换为main时，HEAD文件改变指向指向main，此时main指向工作区域中对应的文件，此时
    在客户端就可以看到main分支下的文件而不是user分支的了；
        (4)当文件内容增删改后，仅是在工作区域和对比区域新增版本号，最后在存储区域改变原有指向，而不是删除原有
    文件，只是让原先对比区域的指针不再指向存储区域的原文件了，而是指向修改后的文件，相当于文件还在，只是指针不
    指向了，没有办法访问了；
        (5)辅助图片：底层实现逻辑

    3.命令指定文件夹创建仓库
        (1)新建仓库
            [1]在指定文件目录下右键 -> git Bash Here
            [2]输入指令 git init -> 初始化完成，得到.git文件
            [3]指令创建的仓库不会提交任何文件，因此object和head文件为空，而客户端创建仓库会自动执行一次提交，
        提交文件.gitattributes，而且客户端可以指定创建README文件
        (2)克隆远程仓库
            [1]在指定文件目录下右键 -> git Bash Here
            [2]输入指令 git clone 远程仓库网址 -> 得到远程仓库
                       git clone 远程仓库网址 新的仓库名 -> 得到新命名的相同远程仓库

    4.配置仓库用户名和邮箱
        (1)配置本地(单一)仓库 -> 相当于客户端Repository -> Repository Settings -> git config中设置
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]输入指令 git config user.name 用户名 -> 配置用户名
                       git config user.email 邮箱 -> 配置邮箱
            [3]该配置会保存在对应仓库目录下的config文件中，直接修改文件也可
        (2)配置全局(所有)仓库 -> 相当于在客户端option中设置
            [1]在任意仓库文件目录下右键 -> git Bash Here
            [2]输入指令 git config --global user.name 用户名 -> 配置全局用户名
                       git config --global user.email 邮箱 -> 配置全局邮箱
            [3]该配置会保存在C盘用户目录下的.gitconfig文件中，直接修改文件也可

    5.文件操作
        (1)查看仓库状态 -> 查看仓库暂存区 -> 这些状态就是客户端提示的信息(文件增删改后没有提交的信息)
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]输入指令 git status -> 得到分支、未处理文件(增删改后未提交的文件)
        (2)工作区域删除文件a.txt并提交到存储区域 -> 相当于在仓库删除文件a.txt，用命令commit提交
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]输入指令 git rm --cached a.txt -> 将a.txt文件从工作区域删除，并将操作提交到暂存区域
                       git rm --cached *.txt -> 将所有txt文件从工作区域删除，并将操作提交到暂存区域
            [3]输入指令 git commit -m 提示信息 -> 将上述操作提交，真正将文件删除，并将该操作更改至存储区域
        (存储区域不会删除原文件，只是改变暂存区对原文件的指向，不再让暂存区指针指向原文件，访问不到了！)
        (3)工作区域文件a.txt新增到存储区域 -> 相当于在仓库创建新文件a.txt，用指令commit提交
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]输入指令 git add a.txt -> 将a.txt文件存储到存储区域
                       git add *.txt -> 将所有txt文件存储到存储区域
            [3]输入指令 git commit -m 提示信息(提交文件) -> 将上述操作提交，真正将文件存储到存储区域，并设置
        提交信息为“提交文件”
        (4)查看文件历史操作信息 -> 相当于在客户端History中查看
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]输入指令  git log --oneline -> 即可查看历史文件操作信息
        (5)误删除操作的恢复 -> 相当于删除工作区域文件，从存储区域恢复或还原到之前的提交记录
            [1]存储区域保存了删除的文件
                {1}在当前仓库文件目录下右键 -> git Bash Here
                {2}输入指令 git restore a.txt -> 将a.txt从存储区域恢复至工作区域
            [2]存储区域没有保存删除的文件
                {1}直接恢复至最初提交删除之前，在此期间进行的其它commit也一并回滚
                    <1>在当前仓库文件目录下右键 -> git Bash Here
                    <2>输入指令 git log --oneline -> 查看删除操作的版本号(7位)
                    <3>输入指令 git reset --hard 删除操作的版本号 -> 回滚所有操作至删除文件之前
                {2}仅还原删除文件，不影响其它提交的操作
                    <1>在当前仓库文件目录下右键 -> git Bash Here
                    <2>输入指令 git log --oneline -> 查看删除操作下一步的版本号(7位)
                        例：删除操作下一步进行了commit提交，则获取提交的版本号
                    <3>输入指令 git revert 版本号 -> 还原到该操作之前

    6.分支操作
        (1)创建分支、查看分支、切换当前分支、删除分支
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]创建分支：必须在有提交操作的仓库中才能创建，因为分支都是基于提交操作的！
                输入指令 git branch 分支名
            [3]查看分支
                输入指令 git branch -v -> 显示当前仓库所有分支，前面带*的表示当前分支
            [4]切换分支
                输入指令 git checkout 分支名 -> 切换到指定分支
            [5]创建分支并直接切换到新建分支
                输入指令 git checkout -b 分支名 -> 创建新分支并切换到该分支
            [6]删除分支
                输入指令 git branch -d 分支名 -> 删除指定分支
        (2)合并分支
            [1]在当前仓库文件目录下右键 -> git Bash Here
            [2]合并分支
                输入指令 git merge 分支名(user) -> 以当前分支为主分支，将指定分支user合并到当前分支
            [3]合并分支后存在重复文件内容：命令窗口提示
                手动打开对应文件进行修改，之后进行add和commit操作即可解决合并提示文件冲突问题

    7.标签操作(注：所有标签不能重复！)
        [1]在当前仓库文件目录下右键 -> git Bash Here
        [2]若仓库无提交操作，则任意进行一次提交操作
        [3]为commit创建标签
            {1}输入指令 git log -> 查看当前仓库所有commit操作的版本号(40位)
            {2}输入指令 git tag 标签名 commit指令40为版本号 -> 将指定commit设置标签
        [4]查看一个commit操作及其之前的所有操作
            {1}通过版本号查看
                输入指令 git log commit的40位版本号
            {2}通过标签查看
                输入指令 git log commit设置的标签名
        [5]指令git log --oneline可以查看历史操作和commit对应的标签名
        [6]删除标签
            输入指令 git tag -d 标签名

    8.远程仓库操作
        在远程仓库中的config文件中存在下述标签，表示关联的远程仓库，而origin可以代替远程仓库网址
            [remote "origin"]
                url = https://github.com/zgh296/remote.git
                fetch = +refs/heads/*:refs/remotes/origin/*
        (1)远程仓库操作
            [1]在当前文件目录下右键 -> git Bash Here
            [2]关联远程仓库
                输入指令 git remote add origin 远程仓库网址
            [3]删除远程仓库
                输入指令 git remote remove origin(或修改后的名称)
            [4]重命名远程仓库
                输入指令 git remote rename 新名称 -> 默认名称origin
        (2)gitee远程仓库文件操作
            [1]在当前远程仓库文件目录下右键 -> git Bash Here
            [2]本地远程仓库新增文件
                {1}在工作区域新增文件
                {2}git add 文件 -> 将文件提交到暂存区域
                {3}git commit -m 提示信息 -> 将文件提交到存储区域
                {4}ssh-keygen -t rsa -C远程仓库地址，之后全部回车选择默认保存位置即可 -> 得到证书文件
                    得到的证书文件保存在C盘用户目录下的.ssh文件夹中，id_rsa.pub文件保存了证书SSH公钥，
                将公钥复制，打开gitee网站用户图标，选择设置，在SSH公钥中粘贴，并输入密码验证身份
                {5}git push origin(或修改后的名称)，之后输入yes确认
                {6}刷新远程仓库，即可得到新增的文件
        (3)远程仓库修改文件提交到本地仓库
            [1]远程仓库修改文件内容
            [2]在当前远程仓库文件目录下右键 -> git Bash Here
            [3]git pull origin(或修改后的名称) -> 完成文件拉取操作(需要和上一步一样配置密钥)
     */

    /*
    git 常用命令参考手册（直接看md文档）
    ====================

    1. 初始化和配置
    -------------------
    # 初始化新仓库
    git init

    # 克隆远程仓库
    git clone <远程仓库地址>
    cd <项目目录>

    # 配置用户信息（首次使用）
    git config --global user.name "你的用户名"
    git config --global user.email "你的邮箱@example.com"

    # 解决 Windows 文件名过长问题
    git config --global core.longpaths true


    2. 分支管理
    -------------------
    # 查看所有分支（本地 + 远程）
    git branch -a

    # 查看远程分支
    git branch -r

    # 创建新分支
    git branch <分支名>

    # 切换分支
    git checkout <分支名>
    # 新版写法
    git switch <分支名>

    # 创建并切换到新分支
    git checkout -b <分支名>
    # 新版写法
    git switch -c <分支名>

    # 基于远程分支创建本地分支
    git checkout -b <本地分支名> origin/<远程分支名>

    # 将当前本地分支与远程分支建立追踪关系
    git branch --set-upstream-to=origin/<远程分支名>

    # 查看本地分支与远程分支的追踪关系
    git branch -vv

    # 重命名本地分支（直接方式）
    git branch -m <旧分支名> <新分支名>

    # 重命名分支（保留旧分支为备份）
    git checkout -b <新分支名> <旧分支名>   # 基于旧分支创建新分支
    git branch <旧分支名>.bak              # 给旧分支打备份标签
    git push origin <新分支名>             # 推送新分支
    git push origin :<旧分支名>            # 删除远程旧分支

    # 删除本地分支（安全删除，需已合并）
    git branch -d <分支名>

    # 强制删除本地分支（未合并也可删除）
    git branch -D <分支名>

    # 删除远程分支
    git push origin --delete <分支名>


    3. 工作区操作
    -------------------
    # 查看文件状态
    git status

    # 查看文件变更详情（工作区 vs 暂存区）
    git diff

    # 查看特定文件变更
    git diff <文件名>

    # 查看已暂存的变更（暂存区 vs 最新提交）
    git diff --staged

    # 添加文件到暂存区
    git add <文件名>       # 添加单个文件
    git add .              # 添加所有变更（包括新增和修改，不包括删除）
    git add -A             # 添加所有变更（包括新增、修改和删除）

    # 撤销工作区文件的修改（恢复到最后一次 git add/commit 的状态）
    git restore <文件名>
    # 旧版写法
    git checkout -- <文件名>

    # 取消暂存（从暂存区移出，保留工作区修改）
    git restore --staged <文件名>
    # 旧版写法
    git reset HEAD <文件名>

    # 提交到本地仓库
    git commit -m "提交说明"
    git commit -am "提交说明"    # 跳过 git add，直接提交所有已跟踪文件的变更

    # 修改最后一次提交（追加变更或修改提交信息）
    git commit --amend -m "新的提交说明"
    # 仅修改提交信息，不追加变更
    git commit --amend --no-edit


    4. 远程仓库同步
    -------------------
    # 查看远程仓库地址
    git remote -v

    # 添加远程仓库
    git remote add origin <url>

    # 修改远程仓库地址
    git remote set-url origin <url>

    # 拉取远程最新代码（不合并）
    git fetch

    # 拉取并修剪已删除的远程分支引用
    git fetch --prune

    # 清除本地的无效远程分支引用
    git remote prune origin

    # 拉取并合并远程代码
    git pull

    # 拉取指定分支
    git pull origin <分支名>

    # 推送到远程仓库
    git push origin <分支名>

    # 首次推送并设置上游分支
    git push -u origin <分支名>

    # 强制推送（谨慎使用）
    git push -f origin <分支名>
    # 更安全的强制推送（仅当远程与本地记录一致时才推送）
    git push --force-with-lease


    5. 查看提交记录
    -------------------
    # 查看当前分支提交历史
    git log

    # 查看指定分支提交历史
    git log <分支名>

    # 以精简模式查看最近 N 条记录
    git log --oneline -n 20

    # 查看指定分支最近 N 条记录
    git log <分支名> -n 5 --oneline

    # 仅显示 commit hash 值
    git log <分支名> -n 5 --pretty=format:"%H"

    # 图形化查看分支合并历史
    git log --graph --oneline --all

    # 查看某个文件的提交历史
    git log --oneline <文件名>

    # 查看每次提交的变更内容
    git log -p

    # 查看某个提交的详细信息
    git show <commit-id>

    # 查看文件中每一行的最后修改者和时间
    git blame <文件名>


    6. 代码合并
    -------------------
    方式 A：Merge（合并）
      git merge <目标分支>               # 例如 git merge develop
      # 解决冲突后：
      git add <已解决的文件>
      git commit                         # 若自动生成合并提交则不需要手动 commit
      git push

      # 压缩合并（将所有提交压缩为一个提交再合并）
      git merge --squash <目标分支>
      git commit -m "合并说明"

    方式 B：Rebase（变基）
      git rebase <目标分支>              # 例如 git rebase develop
      # 解决冲突后：
      git add <已解决的文件>
      git rebase --continue
      # 放弃本次 rebase
      git rebase --abort
      git push --force-with-lease        # 变基后需强制推送

      # 交互式变基（合并/拆分/修改多个提交）
      git rebase -i HEAD~3               # 对最近3次提交进行操作


    7. Cherry-pick（拣选提交）
    -------------------
    # 将指定提交应用到当前分支
    git cherry-pick <commit-id>

    # 拣选一段提交范围（左开右闭）
    git cherry-pick <commit-A>..<commit-B>

    # 拣选后推送到远程
    git push origin <分支名>


    8. Stash（暂存工作区）
    -------------------
    # 暂存当前工作区修改
    git stash

    # 暂存时添加说明
    git stash save "暂存说明"

    # 查看暂存列表
    git stash list

    # 恢复最近一次暂存（不删除 stash 记录）
    git stash apply

    # 恢复最近一次暂存（恢复后删除 stash 记录）
    git stash pop

    # 恢复指定暂存
    git stash pop stash@{1}

    # 删除最近一次暂存
    git stash drop

    # 清空所有暂存
    git stash clear


    9. Tag（标签管理）
    -------------------
    # 查看所有标签
    git tag

    # 创建轻量标签
    git tag <标签名>

    # 创建附注标签（含说明信息）
    git tag -a <标签名> -m "标签说明"

    # 为历史提交打标签
    git tag -a <标签名> <commit-id> -m "标签说明"

    # 推送标签到远程
    git push origin <标签名>
    git push origin --tags    # 推送所有标签

    # 删除本地标签
    git tag -d <标签名>

    # 删除远程标签
    git push origin :refs/tags/<标签名>


    10. 撤销与回滚
    -------------------
    # 本地撤销 — reset（修改提交历史）
    git reset --soft HEAD~1               # 撤销最后一次提交，保留修改在暂存区
    git reset --soft HEAD~2               # 撤销最近两次提交
    git reset --soft <commit-id>          # 撤销到指定提交，保留修改在暂存区
    git reset --hard HEAD~1               # 撤销最后一次提交并丢弃所有修改（慎用）

    # 远程撤销 — revert（生成新提交来抵消旧提交，不改动历史）
    git revert HEAD                       # 抵消上一次提交
    git revert <commit-id>                # 回退指定提交
    git revert <commit-A>..<commit-B>     # 回退一个提交范围（左开右闭）

    # 找回丢失的提交
    git reflog                            # 查看所有操作记录（包括被删除的分支或 reset）
    git checkout -b <新分支名> <commit-id> # 根据 reflog 中的 commit-id 恢复


    11. 工作树（Worktree）
    -------------------
    # 创建新的工作树（在同一仓库下同时开发多个分支）
    git worktree add <路径> <分支名>

    # 查看所有工作树
    git worktree list

    # 删除工作树
    git worktree remove <路径>

    # 修剪已删除的工作树引用
    git worktree prune


    12. 其他常用操作
    -------------------
    # 清理未跟踪的文件和目录（预览）
    git clean -nfd

    # 清理未跟踪的文件和目录（执行）
    git clean -fd

    # 二分法定位引入 bug 的提交
    git bisect start
    git bisect bad                    # 标记当前版本有问题
    git bisect good <commit-id>       # 标记已知正常的版本
    # ... git 会自动切换提交，测试后标记 good/bad ...
    git bisect reset                  # 结束二分查找

    # 生成 .gitignore 文件模板
    # 常见内容：
    # *.class        — 编译文件
    # *.log          — 日志文件
    # target/        — maven 构建目录
    # .idea/         — IDEA 配置目录
    # *.iml          — IDEA 模块文件


    13. 常见报错处理
    -------------------
    报错：unable to update local ref 或 expected ... but got ...
    原因：本地分支与远程不一致，通常因为远程强制推送或删除了分支，而本地还保留着旧的引用。
    解决方法：
      git remote prune origin             # 清理残留引用
      git fetch --prune                   # 重新拉取并修剪
      git pull                            # 再次尝试拉取


    14. 通用帮助命令
    -------------------
    # 查看 git 所有命令
    git help
    git --help

    # 查看特定命令的帮助
    git help <命令名>
    git <命令名> -h                       # 简洁版帮助
    git <命令名> --help                   # 详细版帮助

     */

}
