'''
Usage:

1. python3 -m pip install --user volcengine
2. python main.py
'''
import os
from volcengine.maas import MaasService, MaasException, ChatRole


def test_chat(maas, req):
    try:
        resp = maas.chat(req)
        print(resp)
        print(resp.choice.message.content)
    except MaasException as e:
        print(e)


def test_stream_chat(maas, req):
    try:
        resps = maas.stream_chat(req)
        for resp in resps:
            print(resp)
            print(resp.choice.message.content)
    except MaasException as e:
        print(e)


if __name__ == '__main__':
    maas = MaasService('maas-api.ml-platform-cn-beijing.volces.com', 'cn-beijing')

    # set ak&sk
    maas.set_ak("")
    maas.set_sk("")

    # document: "https://www.volcengine.com/docs/82379/1099475"
    req = {
        "model": {
            "name": "chatglm2-pro",
            "version": "1.0", # use default version if not specified.
            "endpoint_id": "mse-20230808125635-2mwf4",  # use default endpoint if not specified.
        },
        "parameters": {
            "max_new_tokens": 1024,  # 输出文本的最大tokens限制
            "temperature": 0.8,  # 用于控制生成文本的随机性和创造性，Temperature值越大随机性越大，取值范围0~1
            "top_p": 0.8,  # 用于控制输出tokens的多样性，TopP值越大输出的tokens类型越丰富，取值范围0~1
            "top_k": 16,  # 选择预测值最大的k个token进行采样，取值范围0-1024，0表示不生效
        },
        "messages": [
            {
                "role": ChatRole.USER,
                "content": "天为什么这么蓝？"
            }, {
                "role": ChatRole.ASSISTANT,
                "content": "因为有你"
            }, {
                "role": ChatRole.USER,
                "content": "花儿为什么这么香？"
            },
        ]
    }

    test_chat(maas, req)
    test_stream_chat(maas, req)
