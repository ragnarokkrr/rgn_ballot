CREATE TABLE USER (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  name varchar(32) not null,
  username nvarchar(64) not null,
  version int not null
);

CREATE TABLE AGENDA (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  short_description varchar(512) not null,
  description nvarchar(1024) not null,
  start_voting_date TIMESTAMP WITH TIME ZONE not null,
  scheduled_end_voting_date TIMESTAMP WITH TIME ZONE not null,
  version int not null
);


CREATE TABLE BALLOT (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  yes boolean not null,
  agenda_id BIGINT not null, -- FK to AGENDA
  version int not null
);

CREATE TABLE VOTE_RECEIPT (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  agenda_id BIGINT not null, -- FK to AGENDA
  user_id BIGINT not null, -- FK to USER
  timestamp TIMESTAMP WITH TIME ZONE not null,
  version int not null
);

ALTER TABLE USER ADD CONSTRAINT user_uq UNIQUE(username);

ALTER TABLE VOTE_RECEIPT ADD CONSTRAINT vote_receipt_uq UNIQUE(agenda_id, user_id);

ALTER TABLE BALLOT ADD CONSTRAINT ballot_agenda_fk
FOREIGN KEY (agenda_id) REFERENCES AGENDA(id);

ALTER TABLE VOTE_RECEIPT ADD CONSTRAINT vote_receipt_agenda_fk
FOREIGN KEY (agenda_id) REFERENCES AGENDA(id);

ALTER TABLE VOTE_RECEIPT ADD CONSTRAINT vote_receipt_user_fk
FOREIGN KEY (user_id) REFERENCES USER(id);
