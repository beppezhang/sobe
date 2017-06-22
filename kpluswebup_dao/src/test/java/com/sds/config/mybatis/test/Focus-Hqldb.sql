-- ----------------------------
-- Table structure for focus
-- ----------------------------
drop table focus if exists; 
CREATE TABLE focus (
	id BIGINT IDENTITY NOT NULL,
	mainID VARCHAR(36) NOT NULL,
	customerID VARCHAR(36),
	myFocusInfo VARCHAR(100),
	referenceID VARCHAR(36),
	focusType INTEGER,
	createTime TIMESTAMP,
	isDelete INTEGER
--	PRIMARY KEY (id)
);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (2, '399af5a9-62ee-47ca-985d-62d896167c5b', '0010590487899', 'i8 I12 双门轿跑车 i8 ECE', 'A673E9A1-B9AB-4C80-9030-9C226FCC48FC', 1, '2016-01-08 15:21:43', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (3, '32c61812-75d8-45f7-9ae0-a779e40882d6', '0010590487899', 'i8 I12 双门轿跑车 i8 ECE', 'A673E9A1-B9AB-4C80-9030-9C226FCC48FC', 1, '2016-01-08 15:23:34', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (4, '894a96ca-b782-420a-ba84-088f6100ac46', '0010590487899', 'R1150GS 保养说明', '01417690761', 3, '2016-01-08 15:28:08', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (5, 'c25cf3c3-ac3c-496c-a254-63ed3de7ae08', '0010590487899', 'VL35500', 'VL35500', 2, '2016-01-08 15:36:04', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (6, 'be380a19-cef2-44c6-a09a-08250f4fc6c7', '0010590487899', '1'' E81 3 门车 116d ECE', 'A4351C04-C329-46DC-AB3C-17D9FAB5C5BC', 1, '2016-01-08 15:36:37', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (7, '372eccf1-47e0-4a35-8ce1-53ed12c6b69d', '0010590487899', 'R1150GS 保养说明', '01417690761', 3, '2016-01-08 16:32:25', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (8, '2b43ede8-c89d-448a-a1bf-38d8b9b265c8', '0010590487899', 'VL35500', 'VL35500', 2, '2016-01-08 16:34:08', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (9, 'ac48b010-1309-4c38-9b35-5757b8966763', '0010590487899', '1'' E81 3 门车 116d ECE', 'A4351C04-C329-46DC-AB3C-17D9FAB5C5BC', 1, '2016-01-08 16:34:34', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (10, 'befe1843-2a88-46ec-9c33-ad8308222564', '0010590487899', 'i8 I12 双门轿跑车 i8 ECE', 'A673E9A1-B9AB-4C80-9030-9C226FCC48FC', 1, '2016-01-08 16:36:49', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (11, '695aa066-2396-4c9a-86bc-4b06068c0419', '0010590487899', 'i8 I12 双门轿跑车 i8 ECE', 'A673E9A1-B9AB-4C80-9030-9C226FCC48FC', 1, '2016-01-08 16:40:45', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (12, '37f1d4b2-a448-4250-bac1-ed8b9f009d91', '0010590487899', 'MINI Cabrio R57 敞篷车 Cooper USA', 'DCC66F79-A6EC-473B-AA6A-B2011BF47FB4', 1, '2016-01-08 17:36:52', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (13, '54682076-ad9a-4eba-92ae-4b4dcf394c10', '0010590487899', 'VL35500', 'VL35500', 2, '2016-01-08 17:38:21', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (14, '6df9e944-25a9-43f5-8979-71d0fe74756d', '0010590487899', 'R1150GS 保养说明', '01417690761', 3, '2016-01-08 17:41:05', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (15, '15c1f7fe-ea5f-4457-a131-d72085359de0', '0010590487899', 'VL35500', 'VL35500', 2, '2016-01-08 17:51:20', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (16, '111ca968-191c-4d60-94da-341863e3d249', '0010590487899', '1'' E81 3 门车 116i 1.6 N43 ECE', '14185972-E44F-4BD8-AECB-A4D71F23B0D3', 1, '2016-01-08 17:52:31', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (17, 'b9d5d588-e259-4739-b5c5-4fc5618a78af', '0010590487899', '1'' E81 3 门车 116i 1.6 N43 ECE', '14185972-E44F-4BD8-AECB-A4D71F23B0D3', 1, '2016-01-09 13:14:45', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (18, 'dae3172c-3363-4753-8a8a-1558af020871', '0010590487899', 'VL35500', 'VL35500', 2, '2016-01-09 13:14:49', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (19, '14352267-40ad-4b4b-a2b7-b04fba2e789b', '0010590487899', 'R1150GS 保养说明', '01417690761', 3, '2016-01-09 13:14:53', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (20, '05724a1f-1f66-4fc0-91c9-611e839bf5bb', '0010590487899', 'VL35500', 'VL35500', 2, '2016-01-09 13:14:58', 0);
INSERT INTO focus(id, mainID, customerID, myFocusInfo, referenceID, focusType, createTime, isDelete) VALUES (21, '899694a9-7b04-429b-a6e7-da59f5ae88b1', '0010590487899', '1'' E81 3 门车 116i 1.6 N43 ECE', '14185972-E44F-4BD8-AECB-A4D71F23B0D3', 1, '2016-01-09 13:15:00', 0);
