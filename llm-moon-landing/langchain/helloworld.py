
import os

# 语音集成方式
from langchain_community.chat_models import ChatZhipuAI

api_key = ""

model = ChatZhipuAI(model="glm-4", api_key=api_key, verbose=True)

# system prompt / user prompt
from langchain_core.messages import HumanMessage, SystemMessage

message = [
    SystemMessage(content="Translate the following from English into Chinese"),
    HumanMessage(content="Hello World!"),
]

result = model.invoke(message)

print(result)

# 输出解析器
from langchain_core.output_parsers import StrOutputParser

parser = StrOutputParser()

parser.invoke(result)

