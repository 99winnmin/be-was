CREATE TABLE IF NOT EXISTS bewas_users (
    user_id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bewas_user_infos (
    userinfo_id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS session_storage (
    session_id VARCHAR(255) PRIMARY KEY,
    session_value VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bewas_question (
    question_id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255),
    contents TEXT,
    created_at TIMESTAMP,
    writer_id VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bewas_question_simple_info (
      question_simple_info_id VARCHAR(255) PRIMARY KEY,
      question_id VARCHAR(255),
      title VARCHAR(255),
      created_at TIMESTAMP,
      writer_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS bewas_question_detail_info (
      question_detail_info_id VARCHAR(255) PRIMARY KEY,
      question_id VARCHAR(255),
      title VARCHAR(255),
      contents TEXT,
      created_at TIMESTAMP,
      writer_id VARCHAR(255),
      writer_name VARCHAR(255)
);


