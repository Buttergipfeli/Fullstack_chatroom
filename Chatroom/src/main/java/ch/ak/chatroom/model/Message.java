package ch.ak.chatroom.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.model
 * @date 01.10.2021
 */

@Entity
@Table(name = "message")
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat_id;

    @ManyToOne
    @JoinColumn(name = "chatroom_user_id", referencedColumnName = "id")
    private ChatroomUser chatroom_user_id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_date")
    private LocalDateTime created_date;

    @Column(name = "image_path")
    private String image_path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat_id() {
        return chat_id;
    }

    public void setChat_id(Chat chat_id) {
        this.chat_id = chat_id;
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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", chatroom_user_id=" + chatroom_user_id +
                ", message='" + message + '\'' +
                ", created_date=" + created_date +
                ", image_path='" + image_path + '\'' +
                '}';
    }

    @Override
    public int compareTo(Message o) {
        Long compareInt = o.getId();
        return compareInt.intValue() - this.id.intValue();
    }
}
