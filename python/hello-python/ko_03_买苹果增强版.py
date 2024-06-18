# 1, 输入苹果的单价
price_str = input("苹果的单价：")

# 2, 输入苹果的重量
weight_str = input("苹果的重量：")

# 3, 计算支付的总金额
# 注意：两个字符串变量之间是不能直接用乘法的
money = int(price_str) * int(weight_str)
print(money)