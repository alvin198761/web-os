drop table if exists user_info;

/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   id                   bigint not null auto_increment comment '主键',
   login_name           varchar(100) comment '登录名称',
   nick_name            varchar(100) comment '昵称',
   password             varchar(100) comment '密码',
   auth                 tinyint comment '身份',
   create_time          datetime comment '创建时间',
   author               bigint comment '创建人',
   status               tinyint comment '状态',
   primary key (id)
);

alter table user_info comment '用户信息';


drop table if exists app_icon;

/*==============================================================*/
/* Table: app_icon                                              */
/*==============================================================*/
create table app_icon
(
   id                   bigint not null auto_increment comment '主键',
   type                 tinyint comment '图标类型',
   title                char(20) comment '显示文本',
   tip                  varchar(100) comment '悬停提示',
   icon                 varchar(200) comment '图标',
   publish_id           bigint comment '发布人',
   create_time          datetime comment '创建时间',
   parent_id            bigint comment '父节点',
   route_url            varchar(200) comment '对应路由地址',
   status               tinyint comment '状态',
   primary key (id)
);

alter table app_icon comment '应用程序图标';

drop table if exists notes;

/*==============================================================*/
/* Table: notes                                                 */
/*==============================================================*/
create table notes
(
   id                   bigint not null auto_increment comment '主键',
   title                varchar(20) comment '标题',
   content              varchar(5000) comment '内容',
   last_time            datetime comment '最后修改时间',
   author               bigint comment '作者',
   status               tinyint comment '状态',
   primary key (id)
);

alter table notes comment '便签信息';

drop table if exists protocols;

/*==============================================================*/
/* Table: protocols                                             */
/*==============================================================*/
create table protocols
(
   id                   bigint not null auto_increment comment '主键',
   domain               varchar(100) comment 'ad域',
   user                 varchar(50) comment '用户名',
   ip                   varchar(50) comment 'ip地址',
   port                 int comment '端口',
   password             varchar(100) comment '密码',
   author               bigint comment '作者',
   create_time          datetime comment '时间',
   status               tinyint comment '状态',
   type                 TINYINT comment '协议类型',
   primary key (id)
);

alter table protocols comment '远程协议';


