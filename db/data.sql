SET SQL_SAFE_UPDATES=0;

delete from `brs`.`reserve`;

delete from `brs`.`bus`;
ALTER TABLE `brs`.`bus` AUTO_INCREMENT = 200;

delete from `brs`.`route`;
ALTER TABLE `brs`.`route` AUTO_INCREMENT = 10;

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Ayala MRT',' Balibago Complex');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Ayala MRT','Binan');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Ayala MRT','Pacita Complex');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Binan','Buendia');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Buendia','Balibago Complex');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Buendia','Binan');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Buendia','Pacita Complex');

INSERT INTO `brs`.`route` (origin,destination) VALUES ('Pacita Complex','Ayala');


INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (10,1,86,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (10,0,76,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (10,1,86,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (11,1,68,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (11,0,58,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (11,1,68,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (12,1,64,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (12,0,54,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (12,1,64,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (13,1,68,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (13,0,58,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (13,1,68,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (14,1,86,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (14,0,76,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (14,1,86,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (15,1,68,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (15,0,58,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (15,1,68,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (16,1,64,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (16,0,54,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (16,1,64,'15:00','18:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (17,1,64,'06:00','09:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (17,0,54,'10:00','13:00');

INSERT INTO `brs`.`bus` (routeid,ac,fare,departuretime,arrivaltime) VALUES (17,1,64,'15:00','18:00');

