class Cat:

    def __init__(self, name):
        print("这是一个初始化方法")
        self.name = name

    def eat(self):
        print(self.name + "猫吃鱼")

    def drink(self):
        print("猫喝水")
        pass

    def __del__(self):
        print("我去了")

    def __str__(self):
        """对应java中的toString"""
        return self.name


# 创建猫对象
tom = Cat("Tom")
tom.name = "Tom"
tom.drink()
tom.eat()


print(tom)

print(id(tom))

#del tom