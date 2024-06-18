from openai import OpenAI
import until

client = OpenAI(api_key="", base_url="https://api.stepfun.com/v1")


# 文生文
completion = client.chat.completions.create(
    model="step-1-200k",
    messages=[
        {
            "role": "system",
            "content": "你是由阶跃星辰提供的AI聊天助手，你擅长中文，英文，以及多种其他语言的对话。在保证用户数据安全的前提下，你能对用户的问题和请求，作出快速和精准的回答。同时，你的回答和建议应该拒绝黄赌毒，暴力恐怖主义的内容",
        },
        {"role": "user", "content": "当我六岁时，我的妹妹只有我年龄的一半。那么现在她多少岁，我已经70了"},
    ],
)

print(completion)


# image_str = until.image2base64("photo.png")
# # 识图
# completion = client.chat.completions.create(
#   model="step-1v-32k",
#   messages=[
#       {
#           "role": "system",
#           "content": "你是由阶跃星辰提供的AI聊天助手，你除了擅长中文，英文，以及多种其他语言的对话以外，还能够根据用户提供的图片，对内容进行精准的内容文本描述。在保证用户数据安全的前提下，你能对用户的问题和请求，作出快速和精准的回答。同时，你的回答和建议应该拒绝黄赌毒，暴力恐怖主义的内容",
#       },
#       {
#           "role": "user",
#           "content": [
#               {
#                   "type": "text",
#                   "text": "图片有几个女孩？",
#               },
#               {
#                   "type": "image_url",
#                   "image_url": {
#                       "url": "data:image/webp;base64,%s" % (image_str),
#                   },
#               },
#           ],
#       },
#   ],
# )

print(completion)