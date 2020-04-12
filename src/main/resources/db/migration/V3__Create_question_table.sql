create table question
(
	id int auto_increment,
	title varchar(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator int,
	view_count int default 0,
	like_count int default 0,
	tag varchar(255),
	comment_count int default 0;
	constraint question_pk
		primary key (id)
);
