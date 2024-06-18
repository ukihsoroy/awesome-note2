import numpy as np
from math import sqrt


# 准确度的得分
def accuracy_score(y_true, y_predict):
    """计算y_true和y_predict之间的准确率"""
    assert y_true.shape[0] == y_predict.shape[0], \
        "the size of y_true must be equal to the size of y_predict"

    return sum(y_true == y_predict) / len(y_true)


def mean_squared_error(y_true, y_predict):
    """计算y_true和y_predict之间的MSE"""
    assert len(y_true) == len(y_predict), \
        "the size of y_true must be equal to the size of y_predict"

    return np.sum((y_true - y_predict) ** 2) / len(y_true)


def root_mean_squared_error(y_true, y_predict):
    """计算y_true和y_predict之间的RMSE"""

    return sqrt(mean_squared_error(y_true, y_predict))


def mean_absolute_error(y_true, y_predict):
    """计算y_true和y_predict之间MAE"""
    assert len(y_true) == len(y_predict), \
        "the size of y_true must be equal to the size of y_predict"

    return np.sum(np.absolute(y_true - y_predict)) / len(y_true)


def r2_score(y_true, y_predict):
    """计算y_true和y_predict之间的R Square"""
    return 1 - mean_squared_error(y_true, y_predict) / np.var(y_true)


# True Negative
def TN(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true == 0) * (y_predict == 0))


# False Positive
def FP(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true == 0) * (y_predict == 1))


# False Negative
def FN(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true == 1) * (y_predict == 0))


# True Positive
def TP(y_true, y_predict):
    assert len(y_true) == len(y_predict)
    return np.sum((y_true == 1) * (y_predict == 1))


# 混淆矩阵
def confusion_matrix(y_true, y_predict):
    return np.array([
        [TN(y_true, y_predict), FP(y_true, y_predict)],
        [FN(y_true, y_predict), TP(y_true, y_predict)]
    ])


# 准确率
def precision_score(y_true, y_predict):
    tp = TP(y_true, y_predict)
    fp = FP(y_true, y_predict)
    try:
        return tp / (tp + fp)
    except:
        return 0.0


# 召回率
def recall_score(y_true, y_predict):
    tp = TP(y_true, y_predict)
    fn = FN(y_true, y_predict)
    try:
        return tp / (tp + fn)
    except:
        return 0.0


# f1 score
def f1_score(y_true, y_predict):
    precision = precision_score(y_true, y_predict)
    recall = recall_score(y_true, y_predict)
    try:
        return 2. * precision * recall / (precision + recall)
    except:
        return 0.


# TPR
def TPR(y_true, y_predict):
    tp = TP(y_true, y_predict)
    fn = FN(y_true, y_predict)
    try:
        return tp / (tp + fn)
    except:
        return 0.


# FPR
def FPR(y_true, y_predict):
    fp = FP(y_true, y_predict)
    tn = TN(y_true, y_predict)
    try:
        return fp / (fp + tn)
    except:
        return 0.
