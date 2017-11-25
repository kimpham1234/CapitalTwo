import {Button, Table, Panel} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');



class BusinessTransaction extends React.Component {

	constructor(props) {
		super(props);
	}

//"transaction_id","city","cost","date","state","business_id","card_id","quantity","name","category"
	render() {
		return (
	      <div>
	      	<Table striped bordered condensed hover>
	      	<thead>
	      		<tr>
		      		<th>Transaction_Id</th>
		      		<th>City</th>
		      		<th>Cost</th>
		      		<th>Date</th>
		      		<th>State</th>
		      		<th>Quantity</th>
		      		<th>Category</th>
		      		<th>Card Id</th>
	      		</tr>
	      	</thead>
	      	<tbody>
	      		{this.props.transaction !=null && this.props.transaction.map((trans, index)=>
		      		<tr key={index}>
		      			<td>{trans.transaction_id}</td>
		      			<td>{trans.city}</td>
		      			<td>{trans.cost}</td>
		      			<td>{trans.date}</td>
		      			<td>{trans.state}</td>
		      			<td>{trans.quantity}</td>
		      			<td>{trans.category}</td>
		      			<td>{trans.card_id}</td>
		      		</tr>
		      	)}
	      	</tbody>
	      	</Table>
	      </div>
	    );
	 }
}

export default BusinessTransaction;
