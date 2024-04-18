package com.alibaba.edas.test;

import com.alibaba.edas.test.domain.Echo;
import com.alibaba.edas.test.domain.Now;

/**
 * Created by xiaofei.wxf on 2015/5/9.
 */
public interface EdasDemoService {
    /**
     *
     * @param echo
     * @return
     */
    Echo echo(Echo echo);

    Now now();

    void sleep(int time);

}
