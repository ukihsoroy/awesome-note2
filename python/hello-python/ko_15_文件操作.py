# 1. 打开文件
file = open("README.md")

# 2. 读取文件内容
# python打开文件的方式
# f-只读 w-只写 a-追加 r+读写 w+读写覆盖 a+读写追加
text = file.read()
print(text)

# 3. 关闭文件
file.close()