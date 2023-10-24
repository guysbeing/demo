import os

def print_files_in_directory(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            print(file_path)

# 指定要打印文件的文件夹路径
directory_path = r"D:\桌面\DOWNLOAD\demo"

# 调用函数打印文件列表
print_files_in_directory(directory_path)