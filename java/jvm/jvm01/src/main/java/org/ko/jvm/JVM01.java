package org.ko.jvm;

public class JVM01 {

//    private int a=0xDada_Cafe;
    private float b=0x1.fffffeP+127f;
    private float c=1996;
//    private float d=1996.3;
//    private int f=9999e2;
    private double g=33e2;
    private float h=0x1.fffep-12f;
//    private float i=1.fffep-12f;
//    private long p=0b1_1_1_0_1;
//    private long q=0b1_1_1_0_2;
}

class Value { int val; }

class Test1 {
    public static void main(String[] args) {
        int i1 = 3;
        int i2 = i1;
        i2 = 4;
        System.out.print("i1==" + i1);
        System.out.println(" but i2==" + i2);
        Value v1 = new Value();
        v1.val = 5;
        Value v2 = v1;
        v2.val = 6;
        System.out.print("v1.val==" + v1.val);
        System.out.println(" and v2.val==" + v2.val);
    }
}

class Test2 {
    public static void main(String[] args){
        int a=-6;
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000>>>i)>>>(31-i);
            System.out.print(t);
        }
    }
}
