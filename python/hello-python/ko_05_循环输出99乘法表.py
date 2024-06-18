# while 输出99乘法表
def multiple_table():
    i = 1
    while i < 10:
        j = 1
        while j <= i:
            print("%d * %d = %d" % (j, i, i * j), end="\t")
            j = j + 1
        print()
        i = i + 1
