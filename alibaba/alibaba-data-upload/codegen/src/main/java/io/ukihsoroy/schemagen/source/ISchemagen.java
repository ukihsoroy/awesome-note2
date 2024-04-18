package io.ukihsoroy.schemagen.source;

import io.ukihsoroy.schemagen.bean.Table;

import java.util.List;

/**
 *
 */
public interface ISchemagen {

    /**
     * 执行生成逻辑
     * @param name 数据表名称
     */
    Table extractRecord(String name);

}
