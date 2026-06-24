# git 常用命令参考手册

---

## GitFlow 工作流程（分支策略）

> 一种经典的分支管理模型，定义了五大分支角色及其协作关系。

```
main/master  ───●────────────●────────────●────→ 生产环境分支
                ↑            ↑            ↑
release       ──●────────────●────────────────→ 发布分支（测试/Bug修复）
                ↑            ↑
develop       ──●────●────●──●────●────●────●─→ 开发集成分支
                ↑    ↑        ↑    ↑
feature       ──●    ●────────●    ●──────────→ 功能分支
                     ↑
hotfix              ─●────────────────────────→ 热修复分支
```

| 分支 | 基础分支 | 合并目标 | 说明 |
|------|----------|----------|------|
| **main/master** | — | — | 生产环境分支，项目交付分支，只接受合并 |
| **hotfix** | main | main + develop | main 出 bug 时拉取，修复后合并回 main 和 develop |
| **release** | develop | main + develop | 发布准备分支，只加说明文档和 bug 修复，不加新功能 |
| **develop** | main | main | 各项目组代码集成分支，存放最新开发成果 |
| **feature** | develop | develop | 开发新功能的分支 |

---

## 1. 初始化和配置

```bash
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
```

---

## 2. 分支管理

```bash
# 查看所有分支（本地 + 远程）
git branch -a

# 查看远程分支
git branch -r

# 创建新分支
git branch <分支名>

# 切换分支
git checkout <分支名>
git switch <分支名>                        # 新版写法

# 创建并切换到新分支
git checkout -b <分支名>
git switch -c <分支名>                     # 新版写法

# 基于远程分支创建本地分支
git checkout -b <本地分支名> origin/<远程分支名>

# 将当前本地分支与远程分支建立追踪关系
git branch --set-upstream-to=origin/<远程分支名>

# 查看本地分支与远程分支的追踪关系
git branch -vv

# 重命名本地分支（直接方式）
git branch -m <旧分支名> <新分支名>

# 重命名分支（保留旧分支为备份）
git checkout -b <新分支名> <旧分支名>        # 基于旧分支创建新分支
git branch <旧分支名>.bak                   # 给旧分支打备份标签
git push origin <新分支名>                  # 推送新分支
git push origin :<旧分支名>                 # 删除远程旧分支

# 删除本地分支（安全删除，需已合并）
git branch -d <分支名>

# 强制删除本地分支（未合并也可删除）
git branch -D <分支名>

# 删除远程分支
git push origin --delete <分支名>
```

## 3. 工作区操作

```bash
# 查看文件状态
git status

# 查看文件变更详情（工作区 vs 暂存区）
git diff
git diff <文件名>                           # 查看特定文件变更

# 查看已暂存的变更（暂存区 vs 最新提交）
git diff --staged

# 添加文件到暂存区
git add <文件名>                            # 添加单个文件
git add .                                   # 添加所有变更（新增+修改，不含删除）
git add -A                                  # 添加所有变更（新增+修改+删除）

# 撤销工作区文件的修改
git restore <文件名>                         # 新版写法
git checkout -- <文件名>                     # 旧版写法

# 取消暂存（从暂存区移出，保留工作区修改）
git restore --staged <文件名>               # 新版写法
git reset HEAD <文件名>                     # 旧版写法

# 提交到本地仓库
git commit -m "提交说明"
git commit -am "提交说明"                   # 跳过 add，直接提交所有已跟踪文件

# 修改最后一次提交
git commit --amend -m "新的提交说明"         # 追加变更并修改提交信息
git commit --amend --no-edit                # 仅追加变更，不修改提交信息
```

---

## 4. 远程仓库同步

```bash
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
git pull origin <分支名>                    # 拉取指定分支

# 拉取并以变基方式合并（保持线性历史）
git pull --rebase

# fetch 与 pull 的区别
# git fetch  — 从远程拉取最新代码，不修改本地代码，仅更新远程跟踪分支（预览）
# git pull   — 相当于 git fetch + git merge，直接合并到本地分支

# 推送到远程仓库
git push origin <分支名>
git push -u origin <分支名>                 # 首次推送并设置上游分支

# 强制推送（谨慎使用）
git push -f origin <分支名>
git push --force-with-lease                 # 更安全：远程与本地记录一致时才推送
```

---

## 5. 查看提交记录

```bash
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
```

---

## 6. 代码合并

### Merge（合并）

```bash
git merge <目标分支>                        # 例如 git merge develop

# 解决冲突后：
git add <已解决的文件>
git commit
git push
```

**压缩合并**（将所有提交压缩为一个）：

```bash
git merge --squash <目标分支>
git commit -m "合并说明"
```

### Rebase（变基）

```bash
git rebase <目标分支>                       # 例如 git rebase develop

# 解决冲突后：
git add <已解决的文件>
git rebase --continue

# 放弃本次 rebase
git rebase --abort

# 变基后需强制推送
git push --force-with-lease
```

**交互式变基**（合并/拆分/修改多个提交）：

```bash
git rebase -i HEAD~3                        # 对最近3次提交进行操作
```

| 方式         | 优点          | 缺点           |
|------------|-------------|--------------|
| **Merge**  | 保留完整历史，操作安全 | 分支图杂乱        |
| **Rebase** | 提交历史线性整洁    | 需强制推送，协作时风险大 |

---

## 7. Cherry-pick（拣选提交）

```bash
# 将指定提交应用到当前分支
git cherry-pick <commit-id>

# 拣选一段提交范围（左开右闭）
git cherry-pick <commit-A>..<commit-B>

# 拣选后推送到远程
git push origin <分支名>
```

### Cherry-pick 后如何取消

| 当前状态 | 撤销命令 | 效果 |
|----------|----------|------|
| 冲突中，未完成 cherry-pick | `git cherry-pick --abort` | 放弃本次 cherry-pick，回到执行前状态 |
| 已完成 cherry-pick（已提交，未 push） | `git reset --soft HEAD~1` | 撤销提交，保留修改在工作区 |
| | `git reset --hard HEAD~1` | 彻底撤销，丢弃所有修改 |
| 已完成 cherry-pick 且已 push | `git revert <commit-hash>` + `git push` | 安全撤销（推荐） |

---

## 8. Tag（标签管理）

> Tag 基于某个具体提交（commit）创建，而非基于分支。
>
> - **标记版本**：告诉他人（和未来的自己）某个提交属于哪个版本
> - **方便回溯**：当旧分支已删除时，可以直接以 tag 为基础创建新分支，无需记住分支名或 commit id

```bash
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
git push origin --tags                      # 推送所有标签

# 删除本地标签
git tag -d <标签名>

# 删除远程标签
git push origin :refs/tags/<标签名>

# 基于标签创建新分支
git checkout -b <新分支名> <标签名>
```

---

## 9. 撤销与回滚

### 撤销场景速查

> 根据改动所处的阶段，选择对应的撤销方式。

| 当前状态 | 撤销命令 | 效果 |
|----------|----------|------|
| 有改动，有 add，无 commit | `git reset HEAD <文件名>` | 从暂存区回退到工作区 |
| 有改动，有 add，有 commit | `git reset --soft <commit-id>` | 从本地库回退到工作区 |
| 有改动，有 add，有 commit，有 push | `git revert <commit-hash>` + `git push` | 生成反向提交并推送到远程 |

### 本地撤销 — `git reset`

> 修改提交历史，适用于尚未推送的提交。

```bash
git reset --soft HEAD~1                     # 撤销最后一次提交，保留修改在暂存区
git reset --soft HEAD~2                     # 撤销最近两次提交
git reset --soft <commit-id>                # 撤销到指定提交，保留修改在暂存区
git reset --hard HEAD~1                     # 撤销最后一次提交并丢弃所有修改 ⚠️ 慎用
```

### 远程撤销 — `git revert`

> 生成新提交来抵消旧提交，**不修改历史**，适合已推送的提交。

```bash
git revert HEAD                             # 抵消上一次提交
git revert <commit-id>                      # 回退指定提交
git revert <commit-A>..<commit-B>           # 回退一个提交范围（左开右闭）
```

### 找回丢失的提交

```bash
git reflog                                  # 查看所有操作记录
git checkout -b <新分支名> <commit-id>       # 根据 reflog 中的 commit-id 恢复
```

---

## 10. Stash（暂存工作区）

```bash
# 暂存当前工作区修改
git stash
git stash push -m "暂存说明"                 # 暂存时添加说明

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
```

---

## 11. 工作树（Worktree）

> 在同一仓库下同时开发多个分支，无需来回切换，相当于升级版的Stash。

### 一、创建相关

```bash
# 基于现有分支创建
git worktree add <路径> <分支名>
# 示例：基于 develop 分支创建
git worktree add ../Java_Backend-develop develop

# 创建新分支并检出
git worktree add -b <新分支名> <路径> <起始分支>
# 示例：基于 master 创建 feature 分支
git worktree add -b feature/payment ../Java_Backend-payment master

# 基于远程分支创建本地分支
git worktree add -b <本地分支名> <路径> origin/<远程分支名>
# 示例：基于 origin/develop 创建本地 develop
git worktree add -b develop ../Java_Backend-develop origin/develop

# 基于特定 commit 创建（detached HEAD）
git worktree add <路径> <commit-hash>
# 示例：基于某个历史提交
git worktree add ../Java_Backend-old abc1234
```

### 二、查看相关

```bash
# 列出所有工作树
git worktree list

# 详细查看（包含状态）
git worktree list --verbose

# 查看每个工作树的详细状态
git worktree list --porcelain

# 查看当前工作树信息
git worktree list | grep $(pwd)
```

### 三、删除相关

```bash
# 安全删除指定工作树
git worktree remove <路径>
# 示例
git worktree remove ../Java_Backend-develop

# 强制移除（即使有修改）
git worktree remove --force ../Java_Backend-develop
```

### 四、锁定/解锁

```bash
# 锁定（被锁定的 worktree 不会被 prune 删除）
git worktree lock <路径>
git worktree lock --reason "正在开发重要功能" <路径>

# 解锁
git worktree unlock <路径>
```

### 五、移动/重命名

```bash
# 移动工作树到新位置
git worktree move <旧路径> <新路径>
# 示例：重命名目录
git worktree move ../Java_Backend-develop ../Java_Backend-dev
# 示例：移动位置
git worktree move D:/old/path D:/new/path
```

### 六、修复相关

```bash
# 修复损坏的工作树（.git 链接丢失等）
git worktree repair <路径>

# 修复所有工作树
git worktree repair
```

### 七、高级用法

```bash
# 使用相对路径创建
git worktree add ../branch-name branch-name
git worktree add ./worktrees/feature feature  # 在子目录创建

# 子模块中使用 worktree
cd submodule-path
git worktree add ../submodule-worktree branch-name

# 强制操作（即使分支已存在于其他 worktree）
git worktree add --force ../path branch-name
git worktree remove --force ../path
```

### 八、常见问题处理

```bash
# 清理孤立的 worktree 记录
git worktree prune
git worktree prune --verbose                  # 详细显示删除过程
git worktree prune --expire 1.day.ago         # 清理过期记录（默认 3 个月）

# 处理 "already checked out" 错误
# 解决方案1：删除不需要的 worktree
git worktree remove ../other-path
# 解决方案2：强制在其他位置检出
git worktree add --force ../new-path branch-name
# 解决方案3：在另一个 worktree 中先切走分支
cd ../other-worktree
git checkout other-branch
# 然后回到原目录重新创建
```

---

## 12. 其他常用操作

### 清理未跟踪文件

```bash
git clean -nfd                              # 预览将被删除的文件
git clean -fd                               # 执行删除
```

### 二分法定位 Bug

```bash
git bisect start
git bisect bad                              # 标记当前版本有问题
git bisect good <commit-id>                 # 标记已知正常的版本
# git 会自动切换提交，测试后标记 good/bad ...
git bisect reset                            # 结束二分查找
```

### .gitignore 文件模板

```gitignore
*.class                 # 编译文件
*.log                   # 日志文件
target/                 # maven 构建目录
.idea/                  # IDEA 配置目录
*.iml                   # IDEA 模块文件
```

---

## 13. 常见报错处理

> **报错：** `unable to update local ref` 或 `expected ... but got ...`

**原因：** 本地分支与远程不一致，通常因为远程强制推送或删除了分支，而本地还保留着旧的引用。

**解决方法：**

```bash
git remote prune origin                     # 清理残留引用
git fetch --prune                           # 重新拉取并修剪
git pull                                    # 再次尝试拉取
```

---

## 14. 通用帮助命令

```bash
# 查看 git 所有命令
git help
git --help

# 查看特定命令的帮助
git help <命令名>
git <命令名> -h                             # 简洁版帮助
git <命令名> --help                         # 详细版帮助
```
