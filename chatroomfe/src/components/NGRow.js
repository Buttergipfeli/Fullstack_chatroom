import { Row } from 'react-bootstrap';
import React, { Component }  from 'react';

// änderung für Row damit immer noGutters={true} ist.
function NGRow(props) {
  return (
    <Row noGutters={true}>
      {props.children}
    </Row>

  );

}

export default NGRow;