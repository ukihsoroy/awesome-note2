"""
如果打算让外部使用包的内容
需要在__init__.py中添加允许外面引用的文件
"""
from . import receive_message
from . import send_message
