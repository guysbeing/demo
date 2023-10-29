import os

def print_files_in_directory(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            file_path = os.path.join(root, file)
            print(file_path)

directory_path = r"D:\桌面\DOWNLOAD\demo"

print_files_in_directory(directory_path)
