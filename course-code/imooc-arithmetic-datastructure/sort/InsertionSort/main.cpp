#include <iostream>
#include "SortTestHelper.h"
#include "SelectionSort.h"


using namespace std;

/**
 * 插入排序
 * @tparam T
 * @param ary
 * @param n
 */
template <typename T>
void insertionSort1 (T ary[], int n)
{
    for (int i = 1; i < n; i ++) {

        //1. 寻找元素ary[i]合适的插入位置, 插入排序是可以结束第二层循环
        for (int j = i; j > 0 && ary[j - 1] > ary[j]; j --) {
            swap(ary[j], ary[j-1]);
        }
    }
}

/**
 * 优化后的插入排序
 * @tparam T
 * @param ary
 * @param n
 */
template <typename T>
void insertionSort2 (T ary[], int n)
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


/**
 * 希尔排序, 增强的插入排序
 * 尝试每次和之前第H个排序
 * @tparam T
 * @param ary
 * @param n
 */
template <typename T>
void shellSort (T ary[], int n)
{

}



int main()
{
    int n = 10000;
    //1. 生成数组#无序
//    int *ary1 = SortTestHelper::generateRandomArray(n, 0, n);

    //2. 生成数组#近乎有序
    int *ary1 = SortTestHelper::generateNearlyOrderedArray(n, 10);

    //3. 拷贝两份
    int *ary2 = SortTestHelper::copyIntArray(ary1, n);
    int *ary3 = SortTestHelper::copyIntArray(ary1, n);

    //4. 插入排序, 频繁交换版本
    SortTestHelper::testSort("Insertion Sort 1", insertionSort1, ary1, n);

    //5. 插入排序, 赋值优化版本
    SortTestHelper::testSort("Insertion Sort 2", insertionSort2, ary2, n);

    //6. 选择排序
    SortTestHelper::testSort("Selection Sort", selectionSort, ary3, n);

    delete [] ary1;
    delete [] ary2;
    delete [] ary3;
    return 0;
}