import {Button} from 'react-bootstrap'
import BusinessTransaction from './BusinessTransaction.js'
const React = require('react');
const ReactDOM = require('react-dom');


var monTomon = [
	{id: 1, month: "Jan", amount: 500000},
	{id: 2, month: "Feb", amount: 50000},
	{id: 3, month: "Mar", amount: 700000},
	{id: 4, month: "Apr", amount: 200000},
	{id: 5, month: "May", amount: 40000},
	{id: 6, month: "June", amount: 340000},
	{id: 7, month: "July", amount: 640000},
	{id: 8, month: "Aug", amount: 55000},
	{id: 9, month: "Sep", amount: 530000},
	{id: 10, month: "Oct", amount: 2400000},
	{id: 11, month: "Nov", amount: 40000},
]

var sampleBusiness = {
	name: "Walmart",
	storeCity: "San Jose",
	expiration: "12/2020"
}


class BusinessProfile extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			store: sampleBusiness
		}
	}

	render() {
		return (
	      <div>
	      	<h2>Business Profile</h2>
	      	<h3>Hello {this.props.params.loginId}</h3>

	      	<h2>Your Store Info</h2>
	      	<p>Name: {this.state.store.name}</p>
	      	<p>Location: {this.state.store.city}</p>
	      	<p>Partnership Expiration: {this.state.store.expiration}</p>

	      	<h3>Your month-to-month transaction will go here</h3>
	      	<BusinessTransaction transaction={monTomon}/>
	      </div>
	    );
	 }
}

export default BusinessProfile;
