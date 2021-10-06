import { Col, Container, Dropdown, Row } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import { format } from "date-fns";
import '../my_styles.css';
import FooterText from '../components/FooterText';
import { backendURL } from '../Environment';

function Chat() {

    const [messages, setMessages] = useState([]);
    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState("User");
    let roomname = window.location.href.replace('http://localhost:3000/', '');

    const readMessages = () => {
        fetch(backendURL + "/api/get_messages_from_chat/" + roomname)
            .then(j => j.json())
            .then(data => setMessages(data))
    }

    /*
    useEffect(() => {
        readMessages()
    }, [])
    */

    const readUsers = () => {
        fetch(backendURL + "/api/get_all_users")
            .then(j => j.json())
            .then(data => setUsers(data))
    }

    useEffect(() => {
        readMessages();
    }, [])

    useEffect(() => {
        readUsers();
    }, [])

    const chooseUser = (user) => {
        setSelectedUser(user);
    }

    const formatDate = (clean) => {
        let date = new Date(clean);
        let formattedDate = format(date, "dd.MM.yyyy HH:mm");
        return formattedDate;
    }

    useEffect(() => {
        const interval = setInterval(() => {
            readMessages();
        }, 3000);
        return () => clearInterval(interval);
    }, []);

    useEffect(() => {
        const interval = setInterval(() => {
            readUsers();
        }, 10000);
        return () => clearInterval(interval);
    }, []);

    // Sticky footer
    window.onscroll = function () { myFunction() };

    var header = document.getElementById("myHeader");

    var sticky = 0;

    function myFunction() {
        if (window.pageYOffset > sticky) {
            header.classList.add("sticky");
        } else {
            header.classList.remove("sticky");
        }
    }

    return (
        <Container>
            <Row>
                <Col>
                    <Dropdown>
                        <div className={"header"} id="myHeader">
                            <a href="/">
                                <svg className={"backLogo"} id="Ebene_1" data-name="Ebene 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 138.74 218.24"><defs><style>{'.cls-1{fill:#fff;stroke:#1d1d1b;stroke-linecap:round;stroke-miterlimit:10;stroke-width:15px;}'}</style></defs><line class="cls-1" x1="131.24" y1="7.5" x2="7.5" y2="109.12" /><line class="cls-1" x1="131.24" y1="210.74" x2="7.5" y2="109.12" /></svg>
                            </a>
                            <h2 className={"title"}>{roomname}</h2>
                            <Dropdown.Toggle id="dropdown-button-dark-example1" variant="secondary" className={"dropdownHeader"}>
                                {selectedUser}
                            </Dropdown.Toggle>

                            <Dropdown.Menu variant="dark">
                                {users.map(u =>
                                    <Dropdown.Item onClick={() => chooseUser(u.username)} key={u.id}>{u.username}</Dropdown.Item>
                                )}
                            </Dropdown.Menu>
                        </div>
                    </Dropdown>
                </Col>
            </Row>
            {!messages.length
                ? <Row><Col><h2 style={{ textAlign: "center" }}>Empty Chat :(</h2></Col></Row>
                : messages
                    .sort((a, b) => a.id > b.id ? 1 : -1)
                    .map(m =>
                        <Row key={m.id}>
                            <Col>
                                <span>{m.chatroom_user_id.username} ({formatDate(m.created_date)}):</span>
                                <br></br>
                                <span>{m.message}</span>
                                <div className={"lowerSpace"}></div>
                            </Col>
                        </Row>
                    )
            }
            <Row>
                <Col>
                    <FooterText myUser={selectedUser} myRoomname={roomname} readMessages={readMessages}></FooterText>
                </Col>
            </Row>
            {/*
            */}
        </Container>
    );
}

export default Chat;
