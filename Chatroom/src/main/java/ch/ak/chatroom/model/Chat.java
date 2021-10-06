package ch.ak.chatroom.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.model
 * @date 01.10.2021
 */
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roomname")
    private String roomname;

    @Column(name = "created_date")
    private LocalDate created_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }
}
