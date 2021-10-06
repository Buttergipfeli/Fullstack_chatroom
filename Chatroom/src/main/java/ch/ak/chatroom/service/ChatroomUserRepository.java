package ch.ak.chatroom.service;

import ch.ak.chatroom.model.ChatroomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.service
 * @date 02.10.2021
 */

@Repository
public interface ChatroomUserRepository extends JpaRepository<ChatroomUser, Long> {

    ChatroomUser findChatroomUserByUsername(String username);
    boolean existsByUsername(String username);

}
