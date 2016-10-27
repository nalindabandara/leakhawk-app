CREATE DATABASE epsilondb;

USE epsilondb;

CREATE USER 'epsilontest'@'localhost' IDENTIFIED BY 'epsilontest';

GRANT ALL PRIVILEGES ON epsilondb.* TO 'epsilontest'@'localhost';


CREATE TABLE feed_entry(
   entry_id INT NOT NULL AUTO_INCREMENT,
   entry_key VARCHAR(50) NOT NULL,
   entry_url VARCHAR(200) NOT NULL,
   entry_title VARCHAR(500) ,  
   entry_file_name VARCHAR(200) ,
   entry_user VARCHAR(100) ,    
   PRIMARY KEY ( entry_id )
);

CREATE TABLE feed_entry_context(
   entry_id INT NOT NULL AUTO_INCREMENT,
   entry_key VARCHAR(50) NOT NULL,
   entry_url VARCHAR(200) NOT NULL,
   entry_title VARCHAR(500) ,  
   entry_file_name VARCHAR(200) ,
   entry_user VARCHAR(100) ,    
   PRIMARY KEY ( entry_id )
);


CREATE TABLE mal_user(
   user_id INT NOT NULL AUTO_INCREMENT,
   user_name VARCHAR(250) NOT NULL,
   PRIMARY KEY ( user_id )
);

DROP TABLE feed_entry;