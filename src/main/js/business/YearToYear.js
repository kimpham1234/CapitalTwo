import {Button, Table, Panel} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');

class YearToYear extends React.Component {

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
		      		<th>Year</th>
		      		<th>Total</th>
		      	</tr>
	      	</thead>
	      	<tbody>
	      		{this.props.transactions !=null && this.props.transactions.map((trans, index)=>
		      		<tr key={index}>
		      			<td>{trans.year}</td>
		      			<td>{trans.total}</td>
		      		</tr>
		      	)}
	      	</tbody>
	      	</Table>
	      </div>
	    );
	 }
}

export default YearToYear;
