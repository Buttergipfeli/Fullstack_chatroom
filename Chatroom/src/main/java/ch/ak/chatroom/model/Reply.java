package ch.ak.chatroom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.model
 * @date 01.10.2021
 */

@Entity
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message_id;

    @ManyToOne
    @JoinColumn(name = "chatroom_user_id", referencedColumnName = "id")
    private ChatroomUser chatroom_user_id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Message message_id) {
        this.message_id = message_id;
    }

    public ChatroomUser getChatroom_user_id() {
        return chatroom_user_id;
    }

    public void setChatroom_user_id(ChatroomUser chatroom_user_id) {
        this.chatroom_user_id = chatroom_user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }
}