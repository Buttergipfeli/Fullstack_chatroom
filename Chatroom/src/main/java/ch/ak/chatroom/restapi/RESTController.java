package ch.ak.chatroom.restapi;

import ch.ak.chatroom.model.Chat;
import ch.ak.chatroom.model.ChatroomUser;
import ch.ak.chatroom.model.Message;
import ch.ak.chatroom.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Andre Kocher
 * @project Chatroom
 * @package ch.ak.chatroom.restapi
 * @date 02.10.2021
 */

@RestController
@CrossOrigin("http://localhost:3000")
public class RESTController {

    private DatabaseService databaseService;

    @Autowired
    public RESTController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /*
    @GetMapping("/api/getTestUser")
    public ResponseEntity<?> getTestUser() {
        return new ResponseEntity<>(databaseService.findChatroomUserByUsername("TestUser"), HttpStatus.OK);
    }
    */

    @GetMapping("/api/get_all_users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(databaseService.getAllChatroomUsers(), HttpStatus.OK);
    }

    @PostMapping("/api/register_user")
    public ResponseEntity<?> registerUser(@RequestBody ChatroomUser chatroomUser) {
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]*$", Pattern.CASE_INSENSITIVE);
        Matcher usernameMatcher = usernamePattern.matcher(chatroomUser.getUsername());
        if(usernameMatcher.find() &&
                chatroomUser.getUsername().length() <= 10 &&
                databaseService.doesChatroomUserAlreadyExist(chatroomUser.getUsername()) == false
        ) {
            databaseService.saveChatroomUser(chatroomUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/api/get_chat_names")
    public ResponseEntity<?> getChatNames() {
        return new ResponseEntity<>(databaseService.getAllChatNames(), HttpStatus.OK);
    }

    @PostMapping("/api/register_chat")
    public ResponseEntity<?> registerChat(@RequestBody Chat chat) {
        Pattern roomnamePattern = Pattern.compile("^[a-zA-Z0-9]*$", Pattern.CASE_INSENSITIVE);
        Matcher roomnameMatcher = roomnamePattern.matcher(chat.getRoomname());
        if(roomnameMatcher.find() &&
                chat.getRoomname().length() <= 10 &&
                databaseService.doesChatAlreadyExist(chat.getRoomname()) == false
        ) {
            databaseService.saveChat(chat);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/api/get_messages_from_chat/{roomname}")
    public ResponseEntity<?> getMessagesFromChat(@PathVariable("roomname") String roomname) {
        return new ResponseEntity<>(databaseService.getAllMessagesFromChat(roomname), HttpStatus.OK);
    }

    @PostMapping("/api/save_message")
    public ResponseEntity<?> saveMessage(@RequestBody Message message) {
        databaseService.saveMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
