package ch.ak.chatroom.service;

import ch.ak.chatroom.model.Chat;
import ch.ak.chatroom.model.ChatroomUser;
import ch.ak.chatroom.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.service
 * @date 02.10.2021
 */

@Service
public class DatabaseService {

    private ChatRepository chatRepository;
    private MessageRepository messageRepository;
    private ReplyRepository replyRepository;
    private ChatroomUserRepository chatroomUserRepository;

    @Autowired
    public DatabaseService(ChatRepository chatRepository, MessageRepository messageRepository, ReplyRepository replyRepository, ChatroomUserRepository chatroomUserRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.replyRepository = replyRepository;
        this.chatroomUserRepository = chatroomUserRepository;
    }

    public ChatroomUser findChatroomUserByUsername(String username) {
        return chatroomUserRepository.findChatroomUserByUsername(username);
    }

    public List<ChatroomUser> getAllChatroomUsers() {
        return chatroomUserRepository.findAll();
    }

    public boolean doesChatroomUserAlreadyExist(String chatroomUsername) {
        return chatroomUserRepository.existsByUsername(chatroomUsername);
    }

    public void saveChatroomUser(ChatroomUser chatroomUser) {
        chatroomUserRepository.save(chatroomUser);
    }

    public Chat findChatByRoomname(String roomname) {
        return chatRepository.findByRoomname(roomname);
    }

    public List<Chat> getAllChatNames() {
        return chatRepository.findAll();
    }

    public boolean doesChatAlreadyExist(String roomname) {
        return chatRepository.existsByRoomname(roomname);
    }
    public void saveChat(Chat chat) {
        chat.setCreated_date(LocalDate.now());
        chatRepository.save(chat);
    }

    public List<Message> getAllMessagesFromChat(String chatname){
        return messageRepository.findAllByChat_id(chatRepository.findByRoomname(chatname).getId());
    }

    public void saveMessage(Message message) {
        message.setChat_id(findChatByRoomname(message.getChat_id().getRoomname()));
        message.setChatroom_user_id(findChatroomUserByUsername(message.getChatroom_user_id().getUsername()));
        message.setCreated_date(LocalDateTime.now());
        messageRepository.save(message);
    }

}
