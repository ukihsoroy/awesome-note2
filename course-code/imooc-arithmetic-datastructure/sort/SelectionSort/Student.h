//
// Created by K.O on 2018/6/3.
//

#ifndef ARITHMETIC_DATASTRUCTURE_STUDENT_H
#define ARITHMETIC_DATASTRUCTURE_STUDENT_H

#include <iostream>
#include <string>

using namespace std;

struct Student
{
    //学生名字
    string name;

    //分数
    int score;

    //按照分数比较大小
    bool operator<(const Student &otherStudent)
    {
        return score > otherStudent.score;
    }

    //输出
    friend ostream& operator<<(ostream &os, const Student &student)
    {
        os<<"Student: "<<student.name<<" "<<student.score<<endl;
        return os;
    }
};

#endif //ARITHMETIC_DATASTRUCTURE_STUDENT_H
