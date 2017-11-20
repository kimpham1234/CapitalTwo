import {Button} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');



class CustomerInfo extends React.Component {

	constructor(props) {
		super(props);
	}


	render() {
		return (
	      <div>
	      	<h3>Customer Info</h3>
	     	First Name: {this.props.customer.firstName}
	     	Last Name: {this.props.customer.lastName}
	     	Phone: {this.props.customer.phoneNo}
	     	Ethnicity: {this.props.customer.ethnicity}
	     	Gender: {this.props.customer.gender}
	     	Reward Points: {this.props.customer.rewardPoints}
	     	Income: {this.props.customer.income}
	     	DOB: {this.props.customer.birthYear}
	      </div>
	    );
	 }
}

export default CustomerInfo;


/*
<div>
	      	<p>Welcome back, {this.props.user.loginId} </p>
	      	<p>PhoneNumber: {this.props.user.phoneNo}</p>
	      	<p>Email: {this.props.user.email}</p>
	      	<p>Firstname: {this.props.user.firstName}</p>
	      	<p>Lastname: {this.props.user.lastName}</p>
	      	<p>Ethnicity: {this.props.user.ethnicity}</p>
	      	<p>Gender: {this.props.user.gender}</p>
	      	<p>Reward Points: {this.user.props.rewardPoints}</p>
	      	<p>Income: {this.props.user.income}</p>
	      	<p>DOB: {this.props.user.birthMonth}/{this.props.user.birthDay}/{this.props.user.birthYear}</p>	      	
	      </div>
	      */