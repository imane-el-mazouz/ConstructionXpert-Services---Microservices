CREATE TABLE task (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      description VARCHAR(255) NOT NULL,
                      start_date DATE,
                      end_date DATE,
                      status VARCHAR(255) NOT NULL ,
                      project_id BIGINT NOT NULL

);
