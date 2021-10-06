package ch.ak.chatroom.service;

import ch.ak.chatroom.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.service
 * @date 02.10.2021
 */

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Chat findByRoomname(String roomname);
    boolean existsByRoomname(String roomname);
}
