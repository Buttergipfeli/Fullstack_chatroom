import { Col, Container, Button, Row, Dropdown, Modal, FormControl } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import '../my_styles.css';
import { backendURL } from '../Environment';

function Home() {

  const [showCreateUser, setShowCreateUser] = useState(false);
  const [showCreateChatroom, setShowCreateChatroom] = useState(false);
  const [createdUsername, setCreatedUsername] = useState("");
  const [createdRoomname, setCreatedRoomname] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [chatList, setChatList] = useState([]);

  const userValidation = () => {
    if (/^[a-zA-Z0-9]*$/.test(createdUsername) === false) {
      setErrorMessage("Benutzername darf keine Sonderzeichen inklusive Abstände enthalten!");
    } else if (createdUsername.length > 10) {
      setErrorMessage("Benutzername darf maximal 10 Zeichen lang sein!")
    } else {
      registerUser();
    }
  }

  const registerUser = () => {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        id: null,
        username: createdUsername
      })
    };
    fetch(backendURL + '/api/register_user', requestOptions)
      .then((response) => {
        console.log(response);
        if (response.status !== 200) {
          setErrorMessage("Benutzername existiert bereits!")
        } else {
          handleClose();
          console.log("Success");
        }
      })// .catch(error => console.log('Error ' + error));
  }

  const createdUsernameHandler = (event) => {
    setCreatedUsername(event.target.value);
  }

  const chatValidation = () => {
    if (/^[a-zA-Z0-9]*$/.test(createdRoomname) === false) {
      setErrorMessage("Raumname darf keine Sonderzeichen inklusive Abstände enthalten!");
    } else if (createdRoomname.length > 10) {
      setErrorMessage("Raumname darf maximal 10 Zeichen lang sein!")
    } else {
      registerChat();
    }
  }

  const registerChat = () => {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        id: null,
        roomname: createdRoomname,
        created_date: null
      })
    };
    fetch(backendURL + '/api/register_chat', requestOptions)
      .then((response) => {
        console.log(response);
        if (response.status !== 200) {
          setErrorMessage("Raumname existiert bereits!")
        } else {
          handleClose();
          readList();
          console.log("Success");
        }
      })// .catch(error => console.log('Error ' + error));
  }

  const createdRoomnameHandler = (event) => {
    setCreatedRoomname(event.target.value);
  }

  const handleClose = () => {
    setShowCreateUser(false);
    setCreatedUsername("");
    setErrorMessage("");

    setShowCreateChatroom(false);
  }

  const handleOpen = (event) => {
    if (event === 0) {
      setShowCreateUser(true);
    } else if (event === 1) {
      setShowCreateChatroom(true);
    }
  }

  const readList = () => {
    fetch(backendURL + "/api/get_chat_names")
      .then(j => j.json())
      .then(data => setChatList(data))
  }

  useEffect(() => {
    readList()
  }, [])

  useEffect(() => {
    const interval = setInterval(() => {
      readList();
    }, 10000);
    return () => clearInterval(interval);
  }, []);

  return (
    <Container>
      <Row>
        <Col>
          <Dropdown>
            <div className={"header"} id="myHeader">
              <h2 className={"homeTitle"}>Home</h2>
              <Dropdown.Toggle id="dropdown-button-dark-example1" variant="secondary" className={"dropdownHeader"}>
                Erstellen
              </Dropdown.Toggle>
              <Dropdown.Menu variant="dark">
                <Dropdown.Item onClick={() => handleOpen(0)}>Benutzer erstellen</Dropdown.Item>
                <Dropdown.Item onClick={() => handleOpen(1)}>Chatraum erstellen</Dropdown.Item>
              </Dropdown.Menu>
            </div>
          </Dropdown>
        </Col>
      </Row>
      <br></br>
      {chatList.map(l =>
        <Row key={l.id}>
          <Col key={l.id}>
            <a href={"/" + l.roomname}>
              <Button key={l.id} className={"noSmoothedButton"} variant="outline-dark" role="button">{l.roomname}</Button>
            </a>
          </Col>
        </Row>
      )}


      <Modal show={showCreateUser} onHide={() => handleClose()}>
        <Modal.Header closeButton>
          <Modal.Title>Benutzer erstellen</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Benutzername
          <FormControl onChange={(event) => createdUsernameHandler(event)}></FormControl>
          <p className={"errorValidation"}>{errorMessage}</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={() => handleClose()}>
            Abbruch
          </Button>
          <Button variant="success" onClick={() => userValidation()}>
            OK
          </Button>
        </Modal.Footer>
      </Modal>

      <Modal show={showCreateChatroom} onHide={() => handleClose()}>
        <Modal.Header closeButton>
          <Modal.Title>Chat erstellen</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          Raumname
          <FormControl onChange={(event) => createdRoomnameHandler(event)}></FormControl>
          <p className={"errorValidation"}>{errorMessage}</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="danger" onClick={() => handleClose()}>
            Abbruch
          </Button>
          <Button variant="success" onClick={() => chatValidation()}>
            OK
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default Home;
