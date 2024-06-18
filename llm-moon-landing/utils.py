import pandas as pd


def read_excel(path):
    pd.set_option('display.notebook_repr_html', False)
    # 读取xls（绝对路径）
    return pd.read_excel(io=path)


def read_csv(path):
    pd.set_option('display.notebook_repr_html', False)
    # 读取xls（绝对路径）
    return pd.read_csv(path)


if __name__ == '__main__':
    df = read_csv("术语表.csv")

    for index, row in df.iterrows():
        print(row["name"], row["value"])
