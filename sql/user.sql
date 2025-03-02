-- -- 用户表
-- drop table if exists sys_user;
-- create table sys_user (
--                           user_id         bigint(20)    not null auto_increment         comment '用户ID',
--                           dept_id         bigint(20)    default null                    comment '部门ID',
--                           user_name       varchar(30)   not null unique                 comment '用户账号',
--                           nick_name       varchar(30)   not null                        comment '用户昵称',
--                           user_type       varchar(10)   default '00'                    comment '用户类型（00系统用户，01普通用户，02商家用户等）',
--                           email           varchar(50)   default ''                      comment '用户邮箱',
--                           phonenumber     varchar(20)   default ''                      comment '手机号码',
--                           sex             char(1)    default 0                       comment '用户性别（0男 1女 2未知）',
--                           avatar          varchar(255)  default ''                      comment '头像地址',
--                           password        varchar(255)  not null                        comment '密码',
--                           status          tinyint(1)    default 0                       comment '帐号状态（0正常 1停用）',
--                           del_flag        tinyint(1)    default 0                       comment '删除标志（0代表存在 1代表删除）',
--                           remark          varchar(500)  default null                    comment '备注',
--                           create_time     datetime      default current_timestamp       comment '创建时间',
--                           update_time     datetime      default current_timestamp on update current_timestamp comment '更新时间',
--                           primary key (user_id)
-- ) engine=innodb auto_increment=100 comment = '用户信息表';



-- 原表
-- drop table if exists sys_user;
-- create table sys_user (
--   user_id           bigint(20)      not null auto_increment    comment '用户ID',
--   dept_id           bigint(20)      default null               comment '部门ID',
--   login_name        varchar(30)     not null                   comment '登录账号',
--   user_name         varchar(30)     default ''                 comment '用户昵称',
--   user_type         varchar(2)      default '00'               comment '用户类型（00系统用户 01注册用户）',
--   email             varchar(50)     default ''                 comment '用户邮箱',
--   phonenumber       varchar(11)     default ''                 comment '手机号码',
--   sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
--   avatar            varchar(100)    default ''                 comment '头像路径',
--   password          varchar(50)     default ''                 comment '密码',
--   salt              varchar(20)     default ''                 comment '盐加密',
--   status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
--   del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
--   login_ip          varchar(128)    default ''                 comment '最后登录IP',
--   login_date        datetime                                   comment '最后登录时间',
--   pwd_update_date   datetime                                   comment '密码最后更新时间',
--   create_by         varchar(64)     default ''                 comment '创建者',
--   create_time       datetime                                   comment '创建时间',
--   update_by         varchar(64)     default ''                 comment '更新者',
--   update_time       datetime                                   comment '更新时间',
--   remark            varchar(500)    default null               comment '备注',
--   primary key (user_id)
-- ) engine=innodb auto_increment=100 comment = '用户信息表';


-- 商品表
drop table if exists sys_product;
create table sys_product (
                             product_id      bigint(20)    not null auto_increment      comment '商品ID',
                             product_name    varchar(100)  not null                     comment '商品名称',
                             product_desc    text          default null                 comment '商品描述',
                             category_id     bigint(20)    not null                     comment '分类ID',
                             brand_id        bigint(20)    default null                 comment '品牌ID',
                             supplier_id     INT UNSIGNED NOT NULL                      comment '商品的供应商ID',
                             price          decimal(10,2)  not null                     comment '商品价格',
                             stock           int(10)       not null default 0           comment '库存数量',
                             status          tinyint(1)    default 1                    comment '商品状态（1上架 0下架）',
                             del_flag        tinyint(1)    default 0                    comment '删除标志（0正常 1删除）',
                             create_time     datetime      default current_timestamp    comment '创建时间',
                             update_time     datetime      default current_timestamp on update current_timestamp comment '更新时间',
                             primary key (product_id),
                             index idx_category (category_id),
                             index idx_brand (brand_id),
                             index idx_status (status)
) engine=innodb auto_increment=1000 comment = '商品信息表';
-- 订单表
drop table if exists sys_order;
create table sys_order (
                           order_id        bigint(20)     not null auto_increment       comment '订单ID',
                           user_id         bigint(20)     not null                      comment '用户ID',
                           total_price     decimal(10,2)  not null                      comment '订单总金额',
                           pay_status      tinyint    default 0                         comment '支付状态（0未支付 1已支付 2已退款）',
                           order_status    tinyint    default 0                         comment '订单状态（0待支付 1待发货 2已发货 3已完成 4已取消）',
                           pay_method      varchar(20)  not null                        comment '支付方式（支付宝、微信、信用卡等）',
                           pay_time        datetime       default null                  comment '支付时间',
                           cancel_time     datetime       default null                  comment '取消时间',
                           create_time     datetime       default current_timestamp     comment '订单创建时间',
                           update_time     datetime       default current_timestamp on update current_timestamp comment '订单更新时间',
                           primary key (order_id),
                           index idx_user (user_id),
                           index idx_status (order_status),
                           index idx_pay_status (pay_status)
) engine=innodb auto_increment=10000 comment = '订单表';

-- 订单（商品）
drop table if exists sys_order_item;
create table sys_order_item (
                                item_id        bigint(20)     not null auto_increment comment '订单项ID',
                                order_id       bigint(20)     not null comment '订单ID',
                                product_id     bigint(20)     not null comment '商品ID',
                                product_name   varchar(100)   not null comment '商品名称',
                                price          decimal(10,2)  not null comment '商品单价',
                                quantity       int(10)        not null comment '购买数量',
                                total_price    decimal(10,2)  not null comment '该商品总价',
                                create_time    datetime       default current_timestamp comment '创建时间',
                                update_time    datetime       default current_timestamp on update current_timestamp comment '更新时间',
                                primary key (item_id),
                                index idx_order (order_id),
                                index idx_product (product_id)
) engine=innodb auto_increment=10000 comment = '订单商品表';
# item_id	order_id	product_id	商品名称	价格	数量
# 5001	1001	2001	iPhone	6999	1
# 5002	1001	2002	AirPods	999	2

-- 购物车
drop table if exists sys_cart;
create table sys_cart (
                          cart_id       bigint(20)    not null auto_increment comment '购物车ID',
                          user_id       bigint(20)    not null comment '用户ID',
                          product_id    bigint(20)    not null comment '商品ID',
                          quantity      int(10)       not null default 1 comment '商品购买数量',
                          create_time   datetime      default current_timestamp comment '加入购物车时间',
                          update_time   datetime      default current_timestamp on update current_timestamp comment '更新时间',
                          primary key (cart_id),
                          unique key uk_user_product (user_id, product_id),
--                        确保用户购物车里不会有相同商品的多条记录，而是数量累加
                          index idx_user (user_id)
) engine=innodb auto_increment=100000 comment = '购物车表';
-- 再复杂一点，商品有没有选中（但是我不想弄太复杂）
-- -----------------------------------------------------------------------

-- 以上为基础表，下面为大概可能补充
-- 地址
create table sys_user_address (
                                  address_id    bigint(20)   not null auto_increment comment '地址ID',
                                  user_id       bigint(20)   not null comment '用户ID',
                                  receiver_name varchar(50)  not null comment '收货人姓名',
                                  phone         varchar(11)  not null comment '收货人手机号',
                                  province      varchar(50)  not null comment '省份',
                                  city          varchar(50)  not null comment '城市',
                                  district      varchar(50)  not null comment '区县',
                                  detail        varchar(200) not null comment '详细地址',
                                  is_default    tinyint(1)   default 0 comment '是否默认地址（0否 1是）',
                                  create_time   datetime     default current_timestamp comment '创建时间',
                                  update_time   datetime     default current_timestamp on update current_timestamp comment '更新时间',
                                  primary key (address_id),
                                  index idx_user (user_id)
) engine=innodb auto_increment=1000 comment='用户地址表';

-- 分类补充
create table sys_product_category (
                                      category_id   bigint(20)   not null auto_increment comment '分类ID',
                                      category_name varchar(50)  not null comment '分类名称',
                                      parent_id     bigint(20)   default null comment '父分类ID（NULL表示顶级分类）',
                                      create_time   datetime     default current_timestamp comment '创建时间',
                                      update_time   datetime     default current_timestamp on update current_timestamp comment '更新时间',
                                      primary key (category_id)
) engine=innodb auto_increment=1000 comment='商品分类表';


