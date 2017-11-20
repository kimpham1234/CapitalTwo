import {Button} from 'react-bootstrap'
import Transaction from './Transaction.js'
import CustomerInfo from './CustomerInfo.js'
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
	}

	componentDidMount(){
		axios.get('http://localhost:8080/api/customerAccounts/search/findByEmail?email='+this.props.params.loginId)
	      .then(res => {
	        console.log("axios "+ JSON.stringify(res));
	        const resUser = res.data;
	        this.setState({user: resUser});
	      }); 
	}

	render() {
		{console.log("user " + this.state.user)}
		return (
	      <div>
	      	<h2>CustomerProfile</h2>
	      	<CustomerInfo customer={this.state.user}/>
	      	
	      	<p>Your transaction will go here</p>
	   
	      	<Transaction transaction={this.state.trans}/>
	      </div>
	    );
	 }
}

export default CustomerProfile;
