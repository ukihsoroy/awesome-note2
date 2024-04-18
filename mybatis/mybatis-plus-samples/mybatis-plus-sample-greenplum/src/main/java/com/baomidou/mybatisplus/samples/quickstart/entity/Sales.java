package com.baomidou.mybatisplus.samples.quickstart.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Sales {
    private Integer transId;
    private Date date;
    private BigDecimal amount;
    private String region;
}
