# ghloc 使用指南

> ghloc 是一款可以统计项目代码行数的 GitHub 插件。
>
> 需要配置环境变量Path  D:\SoftwareInstallation\go\download\ghloc

---

## 补充：PowerShell 统计方式（无需 ghloc）

> 在没有安装 ghloc 时，可以直接用 PowerShell 脚本统计代码行数。

### 方式一：输出为文本文件（行数 + 完整路径）

```powershell
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    $lines = (Get-Content $_.FullName | Measure-Object -Line).Lines
    "{0,8}  {1}" -f $lines, $_.FullName
} | Sort-Object -Descending | Out-File -FilePath java_lines.txt
```

### 方式二：输出为 CSV 文件

```powershell
Get-ChildItem -Recurse -Filter *.java | ForEach-Object {
    $lines = (Get-Content $_.FullName | Measure-Object -Line).Lines
    [PSCustomObject]@{
        File = $_.FullName
        Lines = $lines
    }
} | Sort-Object Lines -Descending | Export-Csv -Path java_lines.csv -NoTypeInformation
```

> 将 `*.java` 替换为其他扩展名即可统计其他文件类型。
>
> 去掉-Filter *.java过滤条件会统计全部文件。
>
> 在当前项目路径下执行，会在当前目录下生成java_lines文件。

---

## 一、命令行方式

### 1. 统计当前目录

```bash
ghloc
```

会自动打开网页显示统计结果，但路径需要F12查看。

### 2. 统计指定仓库

```bash
ghloc https://github.com/zgh296/java_learn
```

### 3. 在终端直接显示结果（不打开网页）

```bash
ghloc -c
```

### 4. 统计指定文件类型

```bash
# 只统计 Markdown 文件
ghloc -m .md

# 只统计 Java 文件
ghloc -m .java

# 只统计 JavaScript 文件
ghloc -m .js
```

### 5. 组合使用

```bash
# 统计当前目录的 Java 文件，并在终端显示
ghloc -c -m .java

# 统计指定仓库的 Markdown 文件
ghloc -m .md https://github.com/zgh296/java_learn
```

---

## 二、针对当前项目

### 统计 Java_Backend 项目

```bash
# 方式1：进入项目目录执行
cd D:\SoftwareInstallation\ideaIU_2026\file_saving\Java_Backend
ghloc

# 方式2：直接指定路径
ghloc D:\SoftwareInstallation\ideaIU_2026\file_saving\Java_Backend

# 方式3：使用 API（在线查看）
# 在浏览器打开：
https://ghloc.ifels.dev/zgh296/java_learn
```

### 只统计 Java 文件

```bash
ghloc -m .java
```

### 在终端显示结果（不打开网页）

```bash
ghloc -c
```

---

## 三、命令速查表

| 命令 | 作用 |
|------|------|
| `ghloc` | 统计当前目录，打开网页 |
| `ghloc -c` | 统计当前目录，终端显示 |
| `ghloc <路径>` | 统计指定目录或仓库 |
| `ghloc -m .java` | 只统计 .java 文件 |
| `ghloc -c -m .java` | 终端显示，只统计 .java 文件 |
| `ghloc -m !test` | 排除包含 test 的文件 |
| `ghloc -m .js$,!test` | 统计 .js 文件，排除 test |

## 五、参数说明

| 参数 | 说明 |
|------|------|
| `-c` | Console 模式，在终端显示结果 |
| `-m` | Matcher，文件匹配模式 |
| `-m .java` | 匹配 .java 结尾的文件 |
| `-m !test` | 排除包含 test 的文件 |
| `-m .js$,!test` | 多个条件用逗号分隔 |
