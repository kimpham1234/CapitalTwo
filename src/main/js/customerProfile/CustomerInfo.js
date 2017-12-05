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
	      	<h3>Your Info</h3>
	      	<hr className="divider"></hr>
	      	<div className="cusInfo-container">
	      		<div className="inner-container">
	      			First Name: {this.props.customer.firstName}<br></br>
			     	Last Name: {this.props.customer.lastName}<br></br>
			     	Phone: {this.props.customer.phoneNo}<br></br>
			     	DOB: {this.props.customer.birthYear}
	      		</div>
	      		<div className="inner-container">
	      			Ethnicity: {this.props.customer.ethnicity}<br></br>
			     	Gender: {this.props.customer.gender}<br></br>
			     	Reward Points: {this.props.customer.rewardPoints}<br></br>
			     	Income: {this.props.customer.income}
	      		</div>
	      	</div>
	      </div>
	    );
	 }
}

export default CustomerInfo;