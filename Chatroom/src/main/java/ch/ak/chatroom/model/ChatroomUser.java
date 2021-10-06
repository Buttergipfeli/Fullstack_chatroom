package ch.ak.chatroom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.model
 * @date 01.10.2021
 */

@Entity
@Table(name = "chatroom_user")
public class ChatroomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ChatroomUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
