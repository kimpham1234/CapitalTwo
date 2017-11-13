import {Button, FormGroup, FormControl, Col, Form, ControlLabel} from 'react-bootstrap'
import {hashHistory} from 'react-router'
const React = require('react');
const ReactDOM = require('react-dom');



class Login extends React.Component {

	constructor(props) {
		super(props);
	}

	handleCustomerSignIn(e){
		e.preventDefault();
		hashHistory.push("/customerProfile");
	}

	handleBusinessSignIn(e){
		e.preventDefault();
		alert("Feature not yet available");
	}


	render() {
		return (
	      <div>
	      	<h3>Please log in to continue</h3>
	      	<Form horizontal>
			    <FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        Login Id
			      </Col>
			      <Col sm={10}>
			        <FormControl type="text" placeholder="Login id" />
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
			        <Button type="submit" onClick="handleCustomerSignIn(e)">
			          Customer Sign-in
			        </Button>
			      </Col>
			      <Col smOffset={2} sm={10}>
			        <Button type="submit" onClick="handleBusinessSignIn(e)">
			          Business Sign-in
			        </Button>
			      </Col>
			    </FormGroup>

			    <Formgroup>
			    	<Link to path="/newCustomer">Create Account</Link>
			    </Formgroup>
			  </Form>
	      </div>
	    );
	 }
}

export default Login;
