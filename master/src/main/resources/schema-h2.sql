DROP TABLE MC_AUDIT if exists;
DROP TABLE MC_CONTACT if exists;
DROP TABLE MC_GROUP_USER if exists;
DROP TABLE MC_USER if exists;
DROP TABLE MC_GROUP if exists;


-- USER
CREATE TABLE MC_USER (
	id    INTEGER     NOT NULL, -- id
	name  VARCHAR(50) NOT NULL, -- name
	alias VARCHAR(50) NULL,     -- alias
	email VARCHAR(50) NOT NULL  -- email
);

-- USER
ALTER TABLE MC_USER
	ADD CONSTRAINT PK_MC_USER -- USER 기본키
		PRIMARY KEY (
			id -- id
		);

ALTER TABLE MC_USER
	MODIFY COLUMN id INTEGER NOT NULL AUTO_INCREMENT;

-- GROUP
CREATE TABLE MC_GROUP (
	id   INTEGER     NOT NULL, -- id
	name VARCHAR(50) NOT NULL  -- name
);

-- GROUP
ALTER TABLE MC_GROUP
	ADD CONSTRAINT PK_MC_GROUP -- GROUP 기본키
		PRIMARY KEY (
			id -- id
		);

ALTER TABLE MC_GROUP
	MODIFY COLUMN id INTEGER NOT NULL AUTO_INCREMENT;

-- GROUP_USER
CREATE TABLE MC_GROUP_USER (
	group_id INTEGER NOT NULL, -- group_id
	user_id  INTEGER NOT NULL,  -- user_id
	position varchar(50) NULL
);

-- GROUP_USER
ALTER TABLE MC_GROUP_USER
	ADD CONSTRAINT PK_MC_GROUP_USER -- GROUP_USER 기본키
		PRIMARY KEY (
			group_id, -- group_id
			user_id   -- user_id
		);

-- AUDIT
CREATE TABLE MC_AUDIT (
	id         INTEGER       NOT NULL, -- id
	audit_dt   DATETIME      NOT NULL, -- audit_dt
	target     VARCHAR(50)   NOT NULL, -- target
	action     VARCHAR(20)   NOT NULL, -- action
	key_id     INTEGER       NULL,     -- key_id
	data_new   VARCHAR(1000) NULL,     -- data_new
	data_old   VARCHAR(1000) NULL,     -- data_old
	updated_by VARCHAR(20)   NULL      -- updated_by
);

-- AUDIT
ALTER TABLE MC_AUDIT
	ADD CONSTRAINT PK_MC_AUDIT -- AUDIT 기본키
		PRIMARY KEY (
			id -- id
		);

ALTER TABLE MC_AUDIT
	MODIFY COLUMN id INTEGER NOT NULL AUTO_INCREMENT;

CREATE TABLE MC_CONTACT (
	user_id      INTEGER     NOT NULL, -- id
	contact_type VARCHAR(10) NOT NULL, -- contact_type
	email        VARCHAR(50) NOT NULL, -- email
	phone        VARCHAR(30) NULL      -- phone
);

-- CONTACT
ALTER TABLE MC_CONTACT
	ADD CONSTRAINT PK_MC_CONTACT -- CONTACT 기본키
		PRIMARY KEY (
			user_id,      -- id
			contact_type  -- contact_type
		);

-- GROUP_USER
ALTER TABLE MC_GROUP_USER
	ADD CONSTRAINT FK_MC_GROUP_TO_MC_GROUP_USER -- GROUP -> GROUP_USER
		FOREIGN KEY (
			group_id -- group_id
		)
		REFERENCES MC_GROUP ( -- GROUP
			id -- id
		);

-- GROUP_USER
ALTER TABLE MC_GROUP_USER
	ADD CONSTRAINT FK_MC_USER_TO_MC_GROUP_USER -- USER -> GROUP_USER
		FOREIGN KEY (
			user_id -- user_id
		)
		REFERENCES MC_USER ( -- USER
			id -- id
		);

-- CONTACT
ALTER TABLE MC_CONTACT
	ADD CONSTRAINT FK_MC_USER_TO_MC_CONTACT -- USER -> CONTACT
		FOREIGN KEY (
			user_id -- id
		)
		REFERENCES MC_USER ( -- USER
			id -- id
		);