CREATE TABLE `user` (
  `u_id` int(20) unsigned NOT NULL,
  `username` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) not null,
  `password` varchar(200) not null,
  `date_created` DATE not null,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `to_do_list` (
  `t_id` int(20) unsigned NOT NULL,
  `user_u_id` int(20) unsigned NOT NULL,
  `to_do_task` varchar(100) NOT NULL,
  `status` varchar(100) not null,
  `date_created` DATE not null,
  PRIMARY KEY (`t_id`),
  foreign key (`user_u_id`) references `user`(`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
