import {Button, FormGroup, FormControl, Col, Form, ControlLabel} from 'react-bootstrap'
import {hashHistory, Link} from 'react-router'
import * as firebase from 'firebase'



const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');



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

	componentDidMount(){
		axios.get('http://localhost:8080/demo/findBusiness', {
			params: {
				email: 'google@gmail.com'
			}
		})
		.then(res => {
			console.log(JSON.stringify(res));
		}).catch(error => {
			console.log(error);
		});
	}

	handleSignIn(event){
		event.preventDefault();
		//alert("Login Id is "+this.state.loginId + " type "+this.state.userType);
		var loginId = this.state.loginId;
		var password = this.state.password;
		var userType = this.state.userType;
		//hashHistory.push("/customerProfile/kim");
		
		firebase.auth().signInWithEmailAndPassword(loginId, password)
		.then(function(){
			console.log("sign in success");
			if(userType == "customer"){
				var path = "/customerProfile";
				hashHistory.push(path);
			}
			else{
				console.log("from login " + loginId);
				hashHistory.push("/businessProfile/"+loginId);
			}
		})
		.catch(function(error) {
		  // Handle Errors here.
		  var errorCode = error.code;
		  var errorMessage = error.message;
		  console.log(errorMessage);
		});
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
			          Sign-in
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
