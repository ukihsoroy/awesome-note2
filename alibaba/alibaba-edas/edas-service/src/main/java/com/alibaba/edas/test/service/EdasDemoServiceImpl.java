package com.alibaba.edas.test.service;

import com.alibaba.edas.test.EdasDemoService;
import com.alibaba.edas.test.domain.Echo;
import com.alibaba.edas.test.domain.Now;

import java.util.Date;

/**
 * Created by xiaofei.wxf on 2015/5/9.
 */
public class EdasDemoServiceImpl implements EdasDemoService {
    public Echo echo(Echo echo) {
        return echo;
    }

    public Now now() {
        Now ret = new Now();
        ret.setData(new Date());
        return ret;
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
