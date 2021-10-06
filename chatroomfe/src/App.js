import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import { useState, useEffect } from 'react';
import { backendURL } from './Environment';
import Home from './pages/Home';
import Chat from './pages/Chat';

function App() {

  const [chatList, setChatList] = useState([]);

  const readList = () => {
    fetch(backendURL + "/api/get_chat_names")
      .then(j => j.json())
      .then(data => setChatList(data))
  }

  useEffect(() => {
    readList()
  }, [])

  return (
    <Router>
      <div>
        <Switch>
          <Route path={"/"} exact component={Home}></Route>
          {chatList.map(l => 
            <Route key={l.id} path={"/"+l.roomname} component={Chat}></Route>  
          )}
        </Switch>
      </div>
    </Router>
  );
}

export default App;
