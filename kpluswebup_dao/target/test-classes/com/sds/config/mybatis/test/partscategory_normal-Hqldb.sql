-- ----------------------------
-- Table structure for focus
-- ----------------------------
drop table partscategory_normal if exists; 
CREATE TABLE partscategory_normal (
	id BIGINT IDENTITY NOT NULL,
	mainID VARCHAR(36) NOT NULL,
	ancestorID VARCHAR(36),
	status INTEGER,
	code VARCHAR(36),
	name VARCHAR(36),
	flevel INTEGER,
	findex INTEGER,
	isDelete INTEGER
--	PRIMARY KEY (id)
);
INSERT INTO partscategory_normal(id, mainID, ancestorID, status, code, name, flevel, findex, isDelete) VALUES (2, 'D2A79D18-57EE-411A-BAB2-418C1E5ABD85', '1B897961-5193-412C-8F18-137A8A9D13F4', 0, '3505', '踏板装置', 2, 1,0);
INSERT INTO partscategory_normal(id, mainID, ancestorID, status, code, name, flevel, findex, isDelete) VALUES (3, 'D2A79D18-57EE-411A-BAB2-418C1E5ABD90', 'D2A79D18-57EE-411A-BAB2-418C1E5ABD85', 0, '3505', '发动机', 2, 1,0);
INSERT INTO partscategory_normal(id, mainID, ancestorID, status, code, name, flevel, findex, isDelete) VALUES (4, 'D2A79D18-57EE-411A-BAB2-418C1E5ABD91', 'D2A79D18-57EE-411A-BAB2-418C1E5ABD85', 0, '3505', '车身', 2, 1,0);
INSERT INTO partscategory_normal(id, mainID, ancestorID, status, code, name, flevel, findex, isDelete) VALUES (5, 'D2A79D18-57EE-411A-BAB2-418C1E5ABD92', 'D2A79D18-57EE-411A-BAB2-418C1E5ABD85', 0, '3505', '车门', 2, 1,0);
