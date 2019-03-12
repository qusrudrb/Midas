create table stomp_user(
	stomp_id				varchar2(30)				primary key
	,stomp_pw			varchar2(30)				not null
	,stomp_nm			varchar2(50)				not null
);

create sequence stomp_seq;

insert into stomp_user(
	stomp_id
	,stomp_pw
	,stomp_nm
)values(
	'user1'
	,'pw1'
	,'nm1'
);

insert into stomp_user(
	stomp_id
	,stomp_pw
	,stomp_nm
)values(
	'user2'
	,'pw2'
	,'nm2'
);

insert into stomp_user(
	stomp_id
	,stomp_pw
	,stomp_nm
)values(
	'user3'
	,'pw3'
	,'nm3'
);

commit;