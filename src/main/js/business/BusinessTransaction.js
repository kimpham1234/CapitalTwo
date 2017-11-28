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
		      		<th>Location</th>
		      		<th>Date</th>
		      		<th>Cost</th>
		      		<th>Card Id</th>
		      		<th>Merchant</th>
		      		<th>Category</th>
		      	</tr>
	      	</thead>
	      	<tbody>
	      		{this.props.transactions !=null && this.props.transactions.map((trans, index)=>
		      		<tr key={index}>
		      			<td>{trans.transaction_id}</td>
		      			<td>{trans.city + ", " + trans.state}</td>
		      			<td>{trans.date}</td>
		      			<td>{trans.cost}</td>
		      			<td>{trans.card_id}</td>
		      			<td>{trans.name}</td>
		      			<td>
		      				<ul>Category - Quantity
		      				{trans.items.map((items, index2) =>
		      					<li key={index2}>
		      						{items.category} - {items.quantity}
		      					</li>
		      				)}
		      				</ul>
		      			</td>
		      		</tr>
		      	)}
	      	</tbody>
	      	</Table>
	      </div>
	    );
	 }
}

export default BusinessTransaction;
