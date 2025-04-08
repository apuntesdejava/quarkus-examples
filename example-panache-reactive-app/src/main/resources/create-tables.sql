create table if not exists project
(
    id         bigint not null auto_increment,
    name       varchar(100),
    end_date   date,
    start_date date,
    primary key (id)
) engine = InnoDB;
create table if not exists task
(
    id          bigint not null auto_increment,
    name        varchar(255),
    description varchar(255),
    status      enum ('CANCELLED','COMPLETED','IN_PROGRESS','PENDING'),
    project_id  bigint not null,
    primary key (id)
) engine = InnoDB;
alter table task
    add constraint project_in_task_idx foreign key (project_id) references project (id);