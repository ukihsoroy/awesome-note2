import os

os.environ["OPENAI_API_KEY"] = '你的OpenAI Key'

from langchain.llms import OpenAI

llm = OpenAI(model_name="text-davinci-003", max_tokens=200)

text = llm("请给我写一句情人节红玫瑰的中文宣传语")
print(text)