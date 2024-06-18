#include <iostream>
#include "SortTestHelper.h"

using namespace std;

template <typename T>
void bubbleSort(T ary[], int n)
{
    for (int i = 0; i < n; i ++) {
        for (int j = 0; j < n; j ++) {
            if (ary[j] > ary[j + 1]) {
                swap(ary[j], ary[j + 1]);
            }
        }
    }
}

int main()
{
    int n = 10000;
    int *ary = SortTestHelper::generateRandomArray(n, 0, n);

    SortTestHelper::testSort("Bubble Sort", bubbleSort, ary, n);

    delete [] ary;
    return 0;
}