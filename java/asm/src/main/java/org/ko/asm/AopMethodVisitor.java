package org.ko.asm;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class AopMethodVisitor extends MethodVisitor implements Opcodes {

    public AopMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "org/ko/aop/AopInteceptor", "before", "()V", false);
    }

    @Override
    public void visitInsn(int opcode) {
        if (opcode >= IRETURN && opcode <= RETURN)// 在返回之前安插after 代码。
            this.visitMethodInsn(INVOKESTATIC, "org/ko/aop/AopInteceptor", "after", "()V", false);
        super.visitInsn(opcode);
    }

}
