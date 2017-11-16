import {Button, FormGroup, FormControl, Col, Form, ControlLabel} from 'react-bootstrap'
import {hashHistory} from 'react-router'
const React = require('react');
const ReactDOM = require('react-dom');

class CreateAccount extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			loginId: "",
			password: "",
			userType: ""
		}

		this.handleChange = this.handleChange.bind(this);
	}


	handleChange(event){
		const target = event.target;
		const value = target.value;
		const name = target.name;

		this.setState({
			[name]: value
		});
	}

	handleSubmit(event){
		event.preventDefault();
		alert("Login Id is "+this.state.loginId + " type "+this.state.userType);

		if(this.state.userType == "customer")
			hashHistory.push("/customerProfile/"+ this.state.loginId);
		else
			hashHistory.push("/businessProfile/"+ this.state.loginId);
	}

	render() {
		return (
	      <div>
	      	<h3>Create a new account</h3>
	      	<Form horizontal onSubmit={this.handleSubmit.bind(this)}>
			    <FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        Login id
			      </Col>
			      <Col sm={10}>
			        <FormControl type="text" name="loginId" placeholder="Login Id" />
			      </Col>
			    </FormGroup>

			    <FormGroup controlId="formHorizontalPassword">
			      <Col componentClass={ControlLabel} sm={2}>
			        Password
			      </Col>
			      <Col sm={10}>
			        <FormControl type="password" name="password" placeholder="Password" />
			      </Col>
			    </FormGroup>

			    <FormGroup>
			      <Col smOffset={2} sm={1}>
			      	<FormControl type="radio" name="userType" value="customer" onChange={this.handleChange}/>
			      </Col>
			      <Col componentClass={ControlLabel} sm={1}>
			      	Customer
			      </Col>
			    </FormGroup>
			    <FormGroup>
			      <Col smOffset={2} sm={1}>
			      	<FormControl type="radio" name="userType" value="business" onChange={this.handleChange}/>
			      </Col>
			      <Col componentClass={ControlLabel} sm={1}>
			      	Business
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
