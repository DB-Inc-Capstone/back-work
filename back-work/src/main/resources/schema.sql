CREATE TABLE work (
  workID SERIAL PRIMARY KEY,
  parentID INTEGER,
  workTitle VARCHAR(30),
  workContent VARCHAR(100),
  workState INTEGER NOT NULL DEFAULT '0',
  startDate DATE NOT NULL,
  finishDate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS issue (
  issueID SERIAL PRIMARY KEY,
  workID SERIAL PRIMARY KEY,
  issueTitle VARCHAR(30),
  issueContent VARCHAR(100),
  issueState INTEGER NOT NULL DEFAULT '0',
  CONSTRAINT FK_workissue FOREIGN KEY (workID) REFERENCES work (workID),
  );