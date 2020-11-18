/**
 * Author:  Melissa Isaacson
 * Created: Nov 18, 2020
 */

DROP DATABASE IF EXISTS javaii_final_project;
CREATE DATABASE javaii_final_project;
USE javaii_final_project;

/* ******************************************************************************
	CREATE TABLE Animal
****************************************************************************** */
DROP TABLE IF EXISTS Animal;
CREATE TABLE Animal (
	Id VARCHAR(12) NOT NULL PRIMARY KEY COMMENT 'Id for the Animal.'
    , Animal_Name VARCHAR(256) NOT NULL COMMENT 'Animal''s first name.'
    , Species VARCHAR(100) NOT NULL COMMENT 'Animal''s species.'
    , Gender VARCHAR(6) NOT NULL COMMENT 'Animal''s gender.'
    , Age INT UNSIGNED NOT NULL COMMENT 'Animal''s age.'
    , Fixed BOOLEAN NOT NULL COMMENT 'Whether Animal is fixed or not.'
    , Legs INT UNSIGNED NOT NULL COMMENT 'How many legs an animal has.'
    , Weight DECIMAL(4,2) NOT NULL COMMENT 'How much an animal weighs'
    , Date_Added DATE NOT NULL COMMENT 'When was the animal added'
    , Last_Feeding_Time DATETIME NOT NULL COMMENT 'When was the last feeding time'
) COMMENT 'General information about an animal.'
;

/* ******************************************************************************
	STORED PROCEDURES
****************************************************************************** */

/* ******************************************************************************
	CREATE A NEW PERSON
****************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_create_new_animal$$
CREATE PROCEDURE sp_create_new_animal(
	IN p_id VARCHAR(12)
    , IN p_animal_name VARCHAR(256)
    , IN p_species VARCHAR(100)
    , IN p_gender VARCHAR(6)
    , IN p_age INT UNSIGNED
    , IN p_fixed BOOLEAN
    , IN p_legs INT UNSIGNED
    , IN p_weight DECIMAL(4,2)
    , IN p_date_added DATE
    , IN p_last_feeding_time DATETIME
)
COMMENT 'A procedure to insert a new animal into the database.'
BEGIN
    
    INSERT INTO Animal(
		Id
        , Animal_Name
        , Species
        , Gender
        , Age
        , Fixed
        , Legs
        , Weight
        , Date_Added
        , Last_Feeding_Time
    ) VALUES (
		p_id
        , p_animal_name
        , p_species
        , p_gender
        , p_age
        , p_fixed
        , p_legs
        , p_weight
        , p_date_added
        , p_last_feeding_time
    )
    ;

END$$
DELIMITER ;


/* ******************************************************************************
	SHOW ALL ANIMALS
****************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_show_animals$$
CREATE PROCEDURE sp_show_animals()
COMMENT 'View all animals.'
BEGIN

	SELECT
		Id
        , Animal_Name
        , Species
        , Gender
        , Age
        , Fixed
        , Legs
        , Weight
        , Date_Added
        , Last_Feeding_Time
	FROM animal
	;

END$$
DELIMITER ;


/* ******************************************************************************
	SHOW AN ANIMAL BY ID
****************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_show_animal_by_id$$
CREATE PROCEDURE sp_show_animal_by_id(
	IN p_id VARCHAR(12)
)
COMMENT 'View an animal with matching id.'
BEGIN

	SELECT
		Id
        , Animal_Name
        , Species
        , Gender
        , Age
        , Fixed
        , Legs
        , Weight
        , Date_Added
        , Last_Feeding_Time
	FROM animal
	WHERE Id = p_id
	;

END$$
DELIMITER ;


/* ******************************************************************************
	SHOW AN ANIMAL BY NAME
****************************************************************************** */
DELIMITER $$
DROP PROCEDURE IF EXISTS sp_show_animal_by_name$$
CREATE PROCEDURE sp_show_animal_by_name(
	IN p_name VARCHAR(256)
)
COMMENT 'View an animal with matching id.'
BEGIN

	SELECT
		Id
        , Animal_Name
        , Species
        , Gender
        , Age
        , Fixed
        , Legs
        , Weight
        , Date_Added
        , Last_Feeding_Time
	FROM animal
	WHERE Animal_Name = p_name
	;

END$$
DELIMITER ;


/* ******************************************************************************
	POPULATING TABLE
****************************************************************************** */
CALL sp_create_new_animal(1234, 'Maizie', 'cat', 'female', 1, true, 4, 11.6, '2020-11-18', '2020-11-18 23:59:59');
CALL sp_create_new_animal(5687, 'Cocoa', 'dog', 'female', 1, false, 4, 50.6, '2020-11-17', '2020-11-18 23:59:59');
CALL sp_create_new_animal(8252, 'Lucky', 'cat', 'male', 5, true, 3, 13.2, '2020-11-15', '2020-11-18 23:59:59');
