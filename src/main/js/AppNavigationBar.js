import {Button, Nav, NavItem, Navbar, NavDropdown, MenuItem} from 'react-bootstrap'
import {hashHistory, Link} from 'react-router'
import * as firebase from 'firebase'


const React = require('react');
const ReactDOM = require('react-dom');

class AppNavigationBar extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees: []};
		this.logOut = this.logOut.bind(this);
	}

	componentDidMount() {
		console.log("component did mount");
	}


	logOut(event){
		event.preventDefault();
		firebase.auth().signOut()
		.then(function(){
			hashHistory.push("/");
		})
		.catch(function(error){
			var errorMessage = error.message;
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
			        <NavDropdown title="Menu" id="basic-nav-dropdown">
			          <MenuItem ><Link to="/newCustomer">New Customer</Link></MenuItem>
			          <MenuItem ><Link to="/customerProfile">Customer  Profile</Link></MenuItem>
			          <MenuItem ><Link to="/transaction">Transaction</Link></MenuItem>
			        </NavDropdown>
			      </Nav>
			      <Nav pullRight>
			        <NavItem><Button class="primary" onClick={this.logOut}>Log Out</Button></NavItem>
			        <NavItem eventKey={2} href="#">Link Right</NavItem>
			      </Nav>
			    </Navbar.Collapse>
			  </Navbar>

			  {this.props.children}
	      </div>
	    );
	 }
}

export default AppNavigationBar;
