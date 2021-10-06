package ch.ak.chatroom.service;

import ch.ak.chatroom.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.service
 * @date 02.10.2021
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT DISTINCT message.id, " +
                        "message.chat_id, " +
                        "message.chatroom_user_id, " +
                        "message.message, " +
                        "message.created_date, " +
                        "message.image_path " +
                    "FROM message " +
                    "INNER JOIN chat ON message.chat_id=?1",
            nativeQuery = true)
    List<Message> findAllByChat_id(Long id);

}
