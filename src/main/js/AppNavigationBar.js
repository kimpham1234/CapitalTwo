import {Button, Nav, NavItem, Navbar, NavDropdown, MenuItem} from 'react-bootstrap'
import {hashHistory, Link} from 'react-router'
import * as firebase from 'firebase'


const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');
class AppNavigationBar extends React.Component {

	constructor(props) {
		super(props);
		this.logOut = this.logOut.bind(this);
	}

	logOut(){
		console.log("logged out");
		firebase.auth().signOut()
		.then(function(){
			hashHistory.push("/login");
			this.setState({
				isBusiness: ""
			})
		})
		.catch(function(error){
			var errorMessage = error.message;
			console.log("error");
		});
	}


	render() {
		return (
	      <div>
	      	<Navbar inverse collapseOnSelect>
			    <Navbar.Header>
			      <Navbar.Brand>
			        Capital Two
			      </Navbar.Brand>
			      <Navbar.Toggle />
			    </Navbar.Header>
			    <Navbar.Collapse>
			      <Nav>
			        <NavItem><Link to="/login">Login</Link></NavItem>
			        <NavItem><Link to="/customerProfile">Customer</Link></NavItem>
			        <NavItem><Link to="/businessProfile">Business</Link></NavItem>
			        <NavItem><Button className="primary" onClick={this.logOut}>Log Out</Button></NavItem> 
			      	 
			      </Nav>
			      
			      
			      }
			    </Navbar.Collapse>
			  </Navbar>

			  {this.props.children}
	      </div>
	    );
	 }
}

export default AppNavigationBar;
