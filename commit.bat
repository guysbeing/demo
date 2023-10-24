@echo off

REM 打开 GitHub Desktop
start "" "github-desktop://"

REM 等待 GitHub Desktop 启动
timeout /t 5

REM 导航到本地仓库路径
cd /d "D:\桌面\DOWNLOAD\demo"

REM 添加更改
git add .

REM 提交更改
git commit -m "自动提交"

REM 推送到远程仓库
git push origin main