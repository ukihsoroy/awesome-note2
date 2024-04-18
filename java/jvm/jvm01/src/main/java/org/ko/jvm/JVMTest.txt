Compiled from "JVMTest.java"
public class org.ko.jvm.JVMTest {
  public org.ko.jvm.JVMTest();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: iconst_0
       6: putfield      #2                  // Field i:I
       9: aload_0
      10: new           #3                  // class java/lang/Object
      13: dup
      14: invokespecial #1                  // Method java/lang/Object."<init>":()V
      17: putfield      #4                  // Field object:Ljava/lang/Object;
      20: aload_0
      21: iconst_0
      22: putfield      #5                  // Field js:I
      25: return

  public void method1();
    Code:
       0: iconst_0
       1: istore_1
       2: iconst_0
       3: iload_1
       4: iadd
       5: istore_2
       6: aload_0
       7: getfield      #4                  // Field object:Ljava/lang/Object;
      10: astore_3
      11: invokestatic  #7                  // Method java/lang/System.currentTimeMillis:()J
      14: lstore        4
      16: aload_0
      17: invokevirtual #8                  // Method method2:()V
      20: return

  public void method2();
    Code:
       0: new           #9                  // class java/io/File
       3: dup
       4: ldc           #10                 // String
       6: invokespecial #11                 // Method java/io/File."<init>":(Ljava/lang/String;)V
       9: astore_1
      10: return

  static {};
    Code:
       0: iconst_0
       1: putstatic     #12                 // Field k:I
       4: return
}
