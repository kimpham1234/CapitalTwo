import {Button, FormGroup, FormControl, Col, Form, ControlLabel} from 'react-bootstrap'
import {hashHistory, Link} from 'react-router'
const React = require('react');
const ReactDOM = require('react-dom');



class Login extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			loginId: "",
			password: "",
			userType: ""
		};

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

	handleSignIn(event){
		event.preventDefault();
		alert("Login Id is "+this.state.loginId + " type "+this.state.userType);

		if(this.state.userType == "customer")
			hashHistory.push("/customerProfile");
		else
			hashHistory.push("/businessProfile");
	}

	render() {

		var checkboxStyle = {
			height: "20px !important"
		}

		return (
	      <div>
	      	<h3>Please log in to continue</h3>
	      	<Form horizontal onSubmit={this.handleSignIn.bind(this)}>
			    <FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        Login Id
			      </Col>
			      <Col sm={6}>
			        <FormControl type="text" name="loginId" placeholder="Login id" value={this.state.loginId}
			        		onChange={this.handleChange} />
			      </Col>
			    </FormGroup>

			    <FormGroup controlId="formHorizontalPassword">
			      <Col componentClass={ControlLabel} sm={2}>
			        Password
			      </Col>
			      <Col sm={6}>
			        <FormControl type="password" name="password" placeholder="Password" value={this.state.password}
			        		onChange={this.handleChange}/>
			      </Col>
			    </FormGroup>


			    <FormGroup>
			      <Col smOffset={2} sm={1} style={checkboxStyle}>
			      	<FormControl type="radio" name="userType" value="customer" onChange={this.handleChange}/>
			      </Col>
			      <Col componentClass={ControlLabel} sm={1}>
			      	Customer
			      </Col>
			    </FormGroup>
			    <FormGroup>
			      <Col smOffset={2} sm={1} style={checkboxStyle}>
			      	<FormControl type="radio" name="userType" value="business" onChange={this.handleChange}/>
			      </Col>
			      <Col componentClass={ControlLabel} sm={1}>
			      	Business
			      </Col>
			    </FormGroup>

			    <FormGroup>
			      <Col smOffset={2} sm={10}>
			        <Button type="submit">
			          Customer Sign-in
			        </Button>
			      </Col>
			    </FormGroup>

			    <FormGroup>
			    	<Col smOffset={2} sm={10}>
			    		<Link to="/newCustomer">Create Account</Link>
			   		</Col>
			    </FormGroup>
			  </Form>
	      </div>
	    );
	 }
}

export default Login;
