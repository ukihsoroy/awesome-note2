# greenplum 数据库

## 创建表

- t_user: 根据用户ID分区表，创建复制表

```sql
CREATE TABLE t_user (
  id bigint,
  username varchar,
  password varchar,
  email varchar,
  phone varchar,
  gender smallint,
  birthday date,
  country varchar,
  province varchar,
  city varchar,
  area varchar)
  DISTRIBUTED REPLICATED;
```

- t_order: 根据下单事件分区，按月分区
```sql
CREATE TABLE t_order (
  id bigint,
  goods_id bigint,
  user_id bigint,
  count int,
  time date)
  DISTRIBUTED BY (id)  
  PARTITION BY RANGE(time)    
  (start (date '2020-01-01') inclusive
   end (date '2021-01-01') exclusive every (interval '1 month'),
   default partition outlying_dates); 
```

- t_goods: 根据商品主键分区
```sql
CREATE TABLE t_goods (
  id bigint,
  name varchar,
  stock int)
  DISTRIBUTED BY (id); 
```

## 查询

```sql
# 三表关联
select * from t_user u join t_order o on u.id = o.user_id join t_goods g on o.goods_id = g.id;
```