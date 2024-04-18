# 注意：定义好了函数之后，只表示这个函数封装了一段代码而已


def say_hello():
    print("say")
    print("hello")


def fun_return(arg1, arg2):
    """
    函数参数，函数返回值
    :param arg1:
    :param arg2:
    :return:
    """
    return arg1 + arg2


say_hello()
res = fun_return(1, 2)
print(res)
