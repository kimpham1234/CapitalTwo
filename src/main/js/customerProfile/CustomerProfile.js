import {Button, Glyphicon} from 'react-bootstrap'
import Transaction from './Transaction.js'
import CustomerInfo from './CustomerInfo.js'
import Card from './Card.js'
import {hashHistory} from 'react-router'
import * as firebase from 'firebase'

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class CustomerProfile extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			user: []
		}
		console.log("Profile constructor");
		this.handleViewTransaction = this.handleViewTransaction.bind(this);
		this.handleEdit = this.handleEdit.bind(this);
	}

	handleEdit(){
		hashHistory.push({
			pathname:"/editProfile",
			state: {
				customer: this.state.user
			}
		});
	}

	componentDidMount(){
		var currentUser = firebase.auth().currentUser;
		if(currentUser == null){
			hashHistory.push("/login");
		}
		else{
			var toLookup = currentUser.email;
			axios.get('http://localhost:8080/api/customerAccounts/search/findByEmail?email='+toLookup)
		      .then(res => {
		        console.log("axios "+ JSON.stringify(res));
		        const resUser = res.data;
		        this.setState({
		        	user: resUser
		        });
		    }); 
	 	}
	}

	handleViewTransaction(){
		hashHistory.push({
			pathname:"/transactions/"+firebase.auth().currentUser.email,
			state: {
				account_id: this.state.user._links.self.href.substring("http://localhost:8080/api/customerAccounts/".length)
			}
		});
	}

	render() {
		return (
	      <div>
	      	<h1>Customer Profile</h1><Button bsStyle="primary" onClick={this.handleEdit}><Glyphicon glyph="pencil"/></Button>
	      	<CustomerInfo customer={this.state.user}/>
	      	
	      	<div>
		      	{this.state.user.cards!=null &&
			      	<Card cards={this.state.user.cards}/>
		      	}
		    </div>

		    <Button className="primary" onClick={this.handleViewTransaction}>View Transaction</Button>
	      </div>
	    );
	 }
}

export default CustomerProfile;
