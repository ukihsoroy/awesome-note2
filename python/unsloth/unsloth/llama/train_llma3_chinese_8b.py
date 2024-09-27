from unsloth import FastLanguageModel
from transformers import TrainingArguments
from trl import SFTTrainer

max_seq_length = 2048
load_in_4bit = True

model, tokenizer = FastLanguageModel.from_pretrained(
    model_name="unsloth/llama-3-8b-bnb-4bit",
    max_seq_length=max_seq_length,
    load_in_4bit=load_in_4bit,
)

train_data = [
    {"prompt": "What is the capital of France?", "response": "The capital of France is Paris."}
]


# lora
training_args = TrainingArguments(
    output_dir="./outputs",
    per_device_train_batch_size=2,
    num_train_epochs=3,
    logging_dir='./logs',
)


model = FastLanguageModel.get_peft_model(
    model,
    r=16,
    target_modules=["q_proj", "k_proj", "v_proj", "o_proj"],
    lora_alpha=16,
    lora_dropout=0,
)


trainer = SFTTrainer(
    model=model,
    args=training_args,
    train_dataset=train_data,  # 使用你的训练数据集
)

trainer.train()