import {Button, FormGroup, FormControl, Col, Form, ControlLabel} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');

class CreateAccount extends React.Component {

	constructor(props) {
		super(props);
	}


	render() {
		return (
	      <div>
	      	<h3>Create a new account</h3>
	      	<Form horizontal>
			    <FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        Login id
			      </Col>
			      <Col sm={10}>
			        <FormControl type="text" placeholder="Id" />
			      </Col>
			    </FormGroup>

			    <FormGroup controlId="formHorizontalPassword">
			      <Col componentClass={ControlLabel} sm={2}>
			        Password
			      </Col>
			      <Col sm={10}>
			        <FormControl type="password" placeholder="Password" />
			      </Col>
			    </FormGroup>

			    <FormGroup>
			      <Col smOffset={2} sm={10}>
			        <Button type="submit">
			          Create
			        </Button>
			      </Col>
			    </FormGroup>
			  </Form>
	      </div>
	    );
	 }
}

export default CreateAccount;
