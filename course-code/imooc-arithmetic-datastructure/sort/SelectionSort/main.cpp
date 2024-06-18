#include <iostream>
#include "SortTestHelper.h"
#include "Student.h"

using namespace std;

/**
 * 选择排序
 * @tparam T
 * @param ary
 * @param n
 */
template <typename T>
void selectionSort (T ary[], int n)
{
    for (int i = 0; i < n; i ++)
    {
        // 1. 寻找[i, n) 区间里的最小值
        int minIndex = i;
        for (int j = i + 1; j < n; j ++)
        {
            // 2. 如果当前的值小于ary[j], 记录j的索引到minIndex
            if (ary[j] < ary[minIndex])
            {
                minIndex = j;
            }
        }
        //3. 交换值
        swap(ary[i], ary[minIndex]);
    }
}


int main()
{
    int n = 10000;
    int *ary = SortTestHelper::generateRandomArray(n, 0, n);
//    selectionSort(ary, n);
//    SortTestHelper::printArray(ary, n);
    SortTestHelper::testSort("selectionSort", selectionSort, ary, n);
    delete[] ary;

//    Student d[4] = {{"D", 90}, {"B", 85}, {"C", 70}, {"A", 91}};
//    selectionSort(d, 4);
//    SortTestHelper::printArray(d, 4);
    return 0;
}