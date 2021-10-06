import '../my_styles.css';
import { Row, Button, InputGroup, FormControl, Modal } from 'react-bootstrap';
import { useState } from 'react';
import { backendURL } from '../Environment';

function FooterText(props) {

    const [showNoMessage, setShowNoMessage] = useState(false);
    const [showNoUser, setShowNoUser] = useState(false);
    const [writingMessage, setWritingMessage] = useState("");

    const readMessages = () => {
        props.readMessages();
    }

    const handleClose = () => {
        setShowNoMessage(false);
        setShowNoUser(false);
    }

    const messageHandler = (event) => {
        setWritingMessage(event.target.value)
    }

    const messageSender = () => {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                id: null,
                chat_id: {
                    id: null,
                    roomname: props.myRoomname,
                    created_date: null
                },
                chatroom_user_id: {
                    id: null,
                    username: props.myUser,
                    date_of_birth: null
                },
                message: writingMessage,
                created_date: null,
                image_path: null
            })
        };
        fetch(backendURL + '/api/save_message', requestOptions)
            .then(() => readMessages())
        setWritingMessage("");
    }

    const messageValidator = () => {
        if (props.myUser === "User") {
            setShowNoUser(true);
        } else if (writingMessage === "") {
            setShowNoMessage(true);
        } else {
            messageSender();
        }
    }

    return (
        <>
            <div className="pushUp"></div>
            <Row className="footerNavBar">
                <InputGroup className="mb-3">
                    <FormControl
                        placeholder="Message"
                        aria-label="Message"
                        aria-describedby="basic-addon2"
                        value={writingMessage}
                        onChange={(event) => messageHandler(event)}
                    />
                    <Button
                        variant="outline-secondary"
                        id="button-addon2"
                        onClick={() => messageValidator()}
                    >
                        Send
                    </Button>
                </InputGroup>
            </Row>


            <Modal show={showNoMessage} onHide={() => handleClose()}>
                <Modal.Header closeButton>
                    <Modal.Title>Keine Nachricht!</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Bitte geben Sie eine Nachricht ein.
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleClose()}>
                        OK
                    </Button>
                </Modal.Footer>
            </Modal>

            <Modal show={showNoUser} onHide={() => handleClose()}>
                <Modal.Header closeButton>
                    <Modal.Title>Kein Benutzer</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Bitte wählen Sie einen Benutzer aus!
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={() => handleClose()}>
                        OK
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default FooterText;
