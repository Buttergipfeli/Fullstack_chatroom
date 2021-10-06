DROP TABLE IF EXISTS chat, chatroom_user, message, reply;

CREATE TABLE chat (
    id BIGINT NOT NULL AUTO_INCREMENT,
    roomname varchar (255) NOT NULL,
    background_image_path varchar (255) NULL,
    created_date datetime NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE chatroom_user(
    id BIGINT NOT NULL AUTO_INCREMENT,
    username varchar (255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE message(
    id BIGINT NOT NULL AUTO_INCREMENT,
    chat_id BIGINT NOT NULL,
    chatroom_user_id BIGINT NOT NULL,
    message varchar (2048) NOT NULL,
    created_date datetime NOT NULL,
    image_path varchar (255) NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_message_chat FOREIGN KEY (chat_id) REFERENCES chat(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_message_chatroom_user FOREIGN KEY (chatroom_user_id) REFERENCES chatroom_user(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE reply(
    id BIGINT NOT NULL AUTO_INCREMENT,
    message_id BIGINT NOT NULL,
    chatroom_user_id BIGINT NOT NULL,
    message varchar (255) NOT NULL,
    created_date datetime NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_reply_message FOREIGN KEY (message_id) REFERENCES message(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_reply_chatroom_user FOREIGN KEY (chatroom_user_id) REFERENCES chatroom_user(id) ON DELETE CASCADE ON UPDATE CASCADE
);