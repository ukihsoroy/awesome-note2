from openai import OpenAI

# Initialize the client with your API key
client = OpenAI(api_key='your-api-key-here')

# Create a chat completion
response = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "You are a helpful assistant."},
        {"role": "user", "content": "Hello, how are you?"}
    ]
)

# Print the response
print(response.choices[0].message.content)