import numpy as np

# 创建数组
a = np.array([1, 2, 3])
print(a)
# 输出数组纬度
print(a.shape)

# 重新定义数组维度
a = a.reshape((3, -1))
print(a)
print(a.shape)

# 6个数
a = np.array([1, 2, 3, 4, 5, 6])
print(a)
print(a.shape)

# 2行3列
a = a.reshape((2, 3))
print(a)
print(a.shape)

# 3行2列
a = a.reshape((3, 2))
print(a)
print(a.shape)

# 创建都是0的3*3矩阵
a = np.zeros((3, 3))
print(a)

# 创建都是1的3*3矩阵
a = np.ones((3, 3))
print(a)

# 创建都是3的3*3矩阵
a = np.full((3, 3), 3)
print(a)

# 创建单位矩阵
a = np.eye(3)
print(a)

# 创建全随机矩阵
a = np.random.random((3, 2))
print(a)
