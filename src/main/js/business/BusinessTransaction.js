import {Button, Table, Panel} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');


class BusinessTransaction extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			id: 0
		}
	}

	handleDelete(id){
		
		axios.get('http://localhost:8080/demo/deleteTransaction',{
			params: {
				transaction_id: id
			}
		})
		.then(res => {
			console.log(res);
		})
		.catch(err => {
			console.log(err);
		});
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
		      		<th>Card Number</th>
		      		<th>Merchant</th>
		      		<th>Category</th>
		      		<th>{'     '}</th>
		      	</tr>
	      	</thead>
	      	<tbody>
	      		{this.props.transactions !=null && this.props.transactions.map((trans, index)=>
		      		<tr key={index}>
		      			<td>{trans.transaction_id}</td>
		      			<td>{trans.city + ", " + trans.state}</td>
		      			<td>{trans.date}</td>
		      			<td>{trans.cost}</td>
		      			<td>{trans.card_number}</td>
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
		      			<td>
		      				<Button onClick={this.handleDelete.bind(this, trans.transaction_id)}>Delete</Button>
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
