import base64


# 读取本地图片转换成 base64
def image2base64(image_path):
    # 以二进制读取模式打开文件
    with open(image_path, 'rb') as image_file:
        # 读取文件内容
        image_data = image_file.read()

        # 将读取到的二进制数据转换为Base64编码
        base64_encoded_data = base64.b64encode(image_data)

        # 将Base64编码转换为UTF-8编码的字符串
        base64_message = base64_encoded_data.decode('utf-8')

    # 打印Base64编码的字符串
    print(base64_message)
    return base64_message
