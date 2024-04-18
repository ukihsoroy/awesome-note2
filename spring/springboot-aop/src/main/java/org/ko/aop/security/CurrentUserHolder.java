package org.ko.aop.security;

public class CurrentUserHolder {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get () {
        return holder.get() != null ? holder.get() : "unknown";
    }

    public static void set (String user) {
        holder.set(user);
    }

}
