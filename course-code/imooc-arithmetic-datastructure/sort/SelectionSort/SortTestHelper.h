//
// Created by Administrator on 2018/6/2.
//

#ifndef ARITHMETIC_DATASTRUCTURE_SORTTESTHELPER_H
#define ARITHMETIC_DATASTRUCTURE_SORTTESTHELPER_H

#include <iostream>
#include <ctime>
#include <cassert>

using namespace std;

namespace SortTestHelper
{
    /**
     * 生成有n个元素的随机数组, 每个元素的随机范围为[rangeL, rangeR]
     * @param n 数组长度
     * @param rangeL 最小
     * @param rangeR 最大
     * @return
     */
    int* generateRandomArray (int n, int rangeL, int rangeR) {

        assert( rangeL <= rangeR);
        int *arr = new int[n];
        srand(time(NULL));
        for (int i = 0; i < n; ++i)
        {
            arr[i] = rand() % ( rangeR - rangeL + 1 ) + rangeL;
        }
        return arr;
    }

    /**
     * 打印排序后的数组结果
     * @tparam T
     * @param ary
     * @param n
     */
    template<typename T>
    void printArray (T ary[], int n)
    {
        for (int i = 0; i < n; i ++)
            cout << ary[i] << " ";
        cout << endl;

        return;
    }

    /**
     * 查看数组是否排序成功
     * @tparam T
     * @param ary
     * @param n
     * @return
     */
    template <typename T>
    bool isSorted (T ary[], int n)
    {
        for (int i = 0; i < n - 1; i ++)
            if (ary[i] > ary[i + 1])
                return false;
        return true;
    }

    /**
     * 查看排序执行时间
     * @tparam T
     * @param sortName
     * @param sort
     * @param ary
     * @param n
     */
    template <typename T>
    void testSort (string sortName, void(*sort) (T[], int), T ary[], int n)
    {
        //1. 记录开始时间
        clock_t startTime = clock();
        //2. 排序
        sort(ary, n);
        //3. 记录结束时间
        clock_t endTime = clock();
        //4. 验证数组是否有序
        assert(isSorted(ary, n));
        //5. 输出时间
        cout << sortName << " : " << double(endTime - startTime) / CLOCKS_PER_SEC << " s" << endl;
        return;
    }

}


#endif //ARITHMETIC_DATASTRUCTURE_SORTTESTHELPER_H
