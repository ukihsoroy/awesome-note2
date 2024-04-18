package org.ko.rxjava;

/**
 * https://www.jianshu.com/p/81fac37430dd
 *
 * 1）通过 Observable.create() 方法，调用 OkHttp 网络请求；
 * 2）通过 map 操作符集合 gson，将 Response 转换为 bean 类；
 * 3）通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作；
 * 4）调度线程，在子线程中进行耗时操作任务，在主线程中更新 UI ；
 * 5）通过 subscribe()，根据请求成功或者失败来更新 UI 。
 */
public class RxJavaDemo1 {
}
