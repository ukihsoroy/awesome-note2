name_list = ["zhangsan", "lisi", "wangwu"]

# 1. 取值和取索引
# IndexError: list index out of range 数组下标越界
print(name_list[2])

# 知道数据的内容，想确定数据在列表中的位置
# ValueError: 'lisi1' is not in list
# 使用index方法需要注意，如果传递的数据不再列表中，程序会出错！
print(name_list.index("lisi"))

# 2. 修改
# 注意指定的索引超出范围会报错
name_list[2] = "K.O"

# 3. 增加
name_list.append("wangwu")
name_list.insert(2, "sultan")
# extend方法可以把其他列表中的完整内容，追加到当前列表的末尾
name_list.extend(["jason", "master"])

# 4. 删除
# ValueError: list.remove(x): x not in list
# 如果列表中没有这个元素，会抛出异常
name_list.remove("wangwu")

# pop默认可以把列表中最后一个元素删除
name_list.pop()
name_list.pop(2)

del name_list[1]
# del 关键字本质上是用来将一个变量从内存中删除的

# len方法是统计列表中元素的总数
print(len(name_list))

# count方法可以统计一个元素出现的次数
print(name_list.count("wangwu"))

name_list.sort()

# clear方法可以清空列表
name_list.clear()

print(name_list)

# 170
