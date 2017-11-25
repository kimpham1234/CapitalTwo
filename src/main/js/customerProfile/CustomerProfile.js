import {Button} from 'react-bootstrap'
import Transaction from './Transaction.js'
import CustomerInfo from './CustomerInfo.js'
import Card from './Card.js'
import {hashHistory} from 'react-router'
import * as firebase from 'firebase'

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

var sampleUser = {
	firstName: "James",
	middleName: "",
	lastName: "Brolin",
	age: 35,
	income: 100000,
	rewardPoints: 123
}

var sampleTrans = [
	{id: 1 ,item: "egg", cost: 2},
	{id: 2 ,item: "turkey", cost: 5},
	{id: 3 ,item: "ham", cost: 20},
	{id: 4 ,item: "cranberries", cost: 3},
	{id: 5 ,item: "potatoes", cost: 2.39},
	{id: 6 ,item: "sodas", cost: 3.99}
]

class CustomerProfile extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			user: [],
			trans: sampleTrans
		}
		console.log("Profile constructor");
		this.handleViewTransaction = this.handleViewTransaction.bind(this);
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
	      	<h1>Customer Profile</h1>
	      	<CustomerInfo customer={this.state.user}/>
	      	
	      	{this.state.user.cards!=null &&
		      	<Card cards={this.state.user.cards}/>
	      	}

		    <Button className="primary" onClick={this.handleViewTransaction}>View Transaction</Button>
	      </div>
	    );
	 }
}

export default CustomerProfile;
