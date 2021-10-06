package ch.ak.chatroom.service;

import ch.ak.chatroom.model.Reply;
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
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
