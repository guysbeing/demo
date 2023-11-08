import os
import shutil

sourcePath = r"D:\桌面\DOWNLOAD\edgedown"  # 源目录路径
destinationPath = r"D:\桌面\DOWNLOAD\demo\java"  # 目标目录路径

# 遍历源目录中的文件
for filename in os.listdir(sourcePath):
    if filename.endswith(".java"):
        sourceFilePath = os.path.join(sourcePath, filename)  # 源文件路径
        destinationFilePath = os.path.join(destinationPath, filename)  # 目标文件路径
        shutil.move(sourceFilePath, destinationFilePath)  # 移动文件到目标位置
        print(f"Moved file: {filename}")