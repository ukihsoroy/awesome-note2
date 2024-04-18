import flask
from flask import Flask
import openai

openai.api_key = ''

app = Flask(__name__)


@app.route('/<string:prompt_type>', methods=['post'])
def index(prompt_type):
    # 校验token，鉴权
    token = flask.request.headers.get("token")
    print(prompt_type)
    if token != "":
        return {
            "code": 0,
            "msg": "token error."
        }

    # 获取review
    body = flask.request.get_json()
    review = body['prod_review']
    if len(review) > 3500:
        review = review[0:3500]

    prompt = get_prompt(prompt_type, review)

    # gpt调用
    res = get_completion(prompt)
    print(res)

    response = {
        "code": 0,
        "msg": "success",
        "response": res
    }
    return response


def get_prompt(prompt_type, review):
    prompts = {
        "summarize": f"""
            Your task is to generate a short summary in Chinese to feed back to the consumers of the solution.
            To summarize the following solution,, delimited by triple 
            backticks, in at most 30 words, and focusing on any aspects \
            that mention shipping and delivery of the product. 
            Please return in Chinese.
            Review: ```{review}```
            """,
        "identify": f"""
            Identify the industry attributes of the solution in Chinese. 
            Include no more than five items in the list. Format your answer as a list of \
            lower-case words separated by commas.
            Please return in Chinese.
            Review: ```{review}```
            """
    }

    return prompts.get(prompt_type, None)


def get_completion(prompt, model="gpt-3.5-turbo"): # Andrew mentioned that the prompt/ completion paradigm is preferable for this class
    messages = [{"role": "user", "content": prompt}]
    response = openai.ChatCompletion.create(
        model=model,
        messages=messages,
        temperature=0, # this is the degree of randomness of the model's output
    )
    return response.choices[0].message["content"]


if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=80)