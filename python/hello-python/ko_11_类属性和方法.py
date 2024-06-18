class StaticClass:

    classField = 1

    @classmethod
    def class_method(cls):
        print("123")


print(StaticClass.classField)
StaticClass.class_method()