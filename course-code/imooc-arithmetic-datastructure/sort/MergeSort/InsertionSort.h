//
// Created by Administrator on 2018/6/13.
//

#ifndef MERGESORT_INSERTIONSORT_H
#define MERGESORT_INSERTIONSORT_H

template <typename T>
void insertionSort (T ary[], int n)
{
    for (int i = 1; i < n; i ++) {
        T e = ary[i];
        //1. 寻找元素ary[i]合适的插入位置, 插入排序是可以结束第二层循环
        int j;
        for (j = i; j > 0 && ary[j - 1] > e; j --) {
            ary[j] = ary[j - 1];
        }
        ary[j] = e;
    }
}

#endif //MERGESORT_INSERTIONSORT_H
