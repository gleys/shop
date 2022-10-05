create table category (
  category_id varbinary(16) not null,
  crated_at timestamp,
  updated_at timestamp,
  name varchar(255) not null,
  parent_id varbinary(16),
  primary key (category_id)
);

create table delivery (
  delivery_id varbinary(16) not null,
  crated_at timestamp,
  updated_at timestamp,
  city varchar(255),
  street varchar(255),
  zipcode varchar(255),
  status varchar(255) not null,
  primary key (delivery_id)
);

create table image (
   image_id varbinary(16) not null,
   crated_at timestamp,
   updated_at timestamp,
   image blob not null,
   review_id varbinary(16),
   primary key (image_id)
);

create table item (
  item_id varbinary(16) not null,
  crated_at timestamp,
  updated_at timestamp,
  name varchar(255) not null,
  price numeric(19,2) not null,
  primary key (item_id)
);

create table member (
    member_id varbinary(16) not null,
    crated_at timestamp,
    updated_at timestamp,
    city varchar(255),
    street varchar(255),
    zipcode varchar(255),
    name varchar(255),
    type varchar(255),
    primary key (member_id)
);

create table menu (
  menu_id varbinary(16) not null,
  crated_at timestamp,
  updated_at timestamp,
  name varchar(255) not null,
  price numeric(19,2) not null,
  category_id varbinary(16) not null,
  primary key (menu_id)
);

create table menu_item (
   menu_item_id bigint not null,
   quantity integer not null,
   item_id varbinary(16),
   menu_id varbinary(16),
   primary key (menu_item_id)
);

create table order_menu (
    order_menu_id bigint not null,
    quantity integer not null,
    menu_id varbinary(16),
    order_id varbinary(16),
    primary key (order_menu_id)
);

create table orders (
    order_id varbinary(16) not null,
    crated_at timestamp,
    updated_at timestamp,
    status varchar(255) not null,
    delivery_id varbinary(16) not null,
    member_id varbinary(16) not null,
    primary key (order_id)
);

create table review (
    review_id varbinary(16) not null,
    crated_at timestamp,
    updated_at timestamp,
    description varchar(255),
    rate integer check (rate<=5 AND rate>=1),
    title varchar(255) not null,
    member_id varbinary(16),
    order_id varbinary(16),
    primary key (review_id)
);

alter table orders
    add constraint UK_9ct0l8xfeaiqruabcqjh1neui unique (delivery_id);

alter table category
    add constraint fk_child_to_parent
        foreign key (parent_id)
            references category;

alter table image
    add constraint fk_review_to_image
        foreign key (review_id)
            references review;

alter table menu
    add constraint fk_menu_to_category
        foreign key (category_id)
            references category;

alter table menu_item
    add constraint fk_menu_item_to_item
        foreign key (item_id)
            references item;

alter table menu_item
    add constraint fk_menu_item_to_menu
        foreign key (menu_id)
            references menu;

alter table order_menu
    add constraint fk_order_menu_to_menu
        foreign key (menu_id)
            references menu;

alter table order_menu
    add constraint fk_order_menu_to_order
        foreign key (order_id)
            references orders;

alter table orders
    add constraint fk_order_to_delivery
        foreign key (delivery_id)
            references delivery;

alter table orders
    add constraint fk_order_to_member
        foreign key (member_id)
            references member;

alter table review
    add constraint fk_review_to_member
        foreign key (member_id)
            references member;

alter table review
    add constraint fk_review_to_order
        foreign key (order_id)
            references orders;
