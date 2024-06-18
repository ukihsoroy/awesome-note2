package org.ko.asm;

import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * 自定义ClassLoader
 *
 * @author   单红宇(365384722)
 * @myblog  http://blog.csdn.net/catoop/
 * @create    2016年2月2日
 */
public class MyClassLoader extends ClassLoader implements Opcodes {


    public MyClassLoader() {
        super();
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    public Class<?> defineClass(String name, byte[] b){
        return super.defineClass(name, b, 0, b.length);
    }

}
