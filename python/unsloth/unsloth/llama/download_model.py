from modelscope import snapshot_download

# 下载模型
model_dir = snapshot_download('FlagAlpha/Llama3-Chinese-8B-Instruct', cache_dir="../model")