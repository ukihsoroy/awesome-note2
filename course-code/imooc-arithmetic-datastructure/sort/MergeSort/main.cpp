#include <iostream>
#include "InsertionSort.h"
#include "SortTestHelper.h"

using namespace std;

/**
 * 将ary[l...mid]和ary[mid + 1...r]两部分进行归并
 * @tparam T
 * @param ary
 * @param l
 * @param mid
 * @param r
 */
template <typename T>
void __merge(T ary[], int l, int mid, int r)
{
    T aux[r - l + 1];
    for (int i = l; i <= r; i ++) {
        aux[i - l] = ary[i];
    }

    int i = l, j = mid + 1;
    for (int k = l; k <= r; k ++) {
        if (i < mid) {
            ary[k] = aux[j - l];
            j ++;
        } else if (j > r) {
            ary[k] = aux[i - l];
            i ++;
        } else if (aux[i -l] < aux[j - l]) {
            ary[k] = aux[i - l];
            i ++;
        } else {
            ary[k] = aux[j - l];
            j ++;
        }
    }
}

/**
 * 递归使用归并排序, 对ary[l...r]的范围进行排序
 * @tparam T
 * @param ary
 * @param l
 * @param r
 */
template <typename T>
void __mergeSort(T ary[], int l, int r)
{
    if (l >= r) return;

    int mid = (l + r) / 2;
    __mergeSort(ary, l, mid);
    __mergeSort(ary, mid + 1, r);
    __merge(ary, l, mid, r);
}


template <typename T>
void mergeSort (T ary[], int n)
{
    __mergeSort(ary, 0, n - 1);
}


int main() {
    int n = 50000;
    cout<<"Test for Random Array, size = "<<n<<", random range [0, "<<n<<"]"<<endl;
    int* ary1 = SortTestHelper::generateRandomArray(n, 0, n);
    int* ary2 = SortTestHelper::copyIntArray(ary1, n);

    SortTestHelper::testSort("Insertion Sort", insertionSort, ary1, n);
    SortTestHelper::testSort("Merge Sort", mergeSort, ary2, n);

    delete [] ary1;
    delete [] ary2;

    cout<<endl;

    return 0;
}