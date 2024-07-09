CREATE TABLE student (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(32) NOT NULL,
                         gender TINYINT NOT NULL, -- 0: Male, 1: Female
                         pic LONGBLOB NOT NULL -- Base64 encoded image
);
