import {Button, Table, Panel, Form, FormGroup, FormControl, ControlLabel} from 'react-bootstrap'

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class Transaction extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			transactions: [],
			open: false,
			inputDate: "2017-11-20",
			inputCity: "",
			inputState: ""
		}

		this.addTransaction = this.addTransaction.bind(this);
		this.toggle = this.toggle.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.showAll = this.showAll.bind(this);
	}
	
	toggle(){
		var open = !this.state.open;
		this.setState({open: open});
	}

	handleChange(event){
		console.log("event " + event);
		if(event!=null){
			const target = event.target;
			const value = target.value;
			const name = target.name;
			this.setState({
				[name]: value
			});
		}
	}

	addTransaction(e){
		var transactions = this.state.transactions.slice();
		var submitted = {date: this.state.inputDate, city: this.state.inputCity, state: this.state.inputState};
		transactions.push(submitted);
		alert("Submitted " + JSON.stringify(transactions));
		this.setState({transactions: transactions});
	}

	showAll(){
		axios.get('http://localhost:8080/api/transactions')
	      .then(res => {
	        const resTrans = res.data._embedded.transactions;
	        console.log("axios "+ JSON.stringify(resTrans));
	        this.setState({transactions: resTrans});
        });
	}

	componentDidMount(){
		/*
		axios.get('http://localhost:8080/api/transactions')
	      .then(res => {
	        const resTrans = res.data._embedded.transactions;
	        console.log("axios "+ JSON.stringify(resTrans));
	        this.setState({transactions: resTrans});
        });*/
	}

	render() {
		return (
		<div id="container2">
	      <h1>Transaction of {this.props.params.loginId}</h1>

	      <Button className="primary" onClick={this.showAll}>Show All Transactions</Button> 
	      <Button className="primary" onClick={this.toggle}>Add Transaction</Button>
	      	<Panel collapsible expanded={this.state.open}>
		      	<Form inline onSubmit={this.addTransaction}>
				    <FormGroup controlId="formInlineName">
				      <ControlLabel>Date</ControlLabel>
				      {' '}
				      <FormControl type="date" name="inputDate" value={this.state.inputDate} onChange={this.handleChange}/>
				    </FormGroup>
				    {' '}
				    <FormGroup controlId="formInlineEmail">
				      <ControlLabel>City</ControlLabel>
				      {' '}
				      <FormControl type="text" name="inputCity" value={this.state.inputCity} onChange={this.handleChange}/>
				    </FormGroup>
				    {' '}
				    <FormGroup controlId="formInlineEmail">
				      <ControlLabel>State</ControlLabel>
				      {' '}
				      <FormControl type="text" name="inputState" value={this.state.inputState} onChange={this.handleChange}/>
				    </FormGroup>
				    {' '}
				    <Button type="submit">
				      Add
				    </Button>
				</Form>
	      	</Panel>
	      	<Table striped bordered condensed hover>
	      	<thead>
	      		<th>Date</th>
	      		<th>City</th>
	      		<th>State</th>
	      	</thead>
	      	<tbody>
	      	{this.state.transactions !=null && this.state.transactions.map((trans, index)=>
		      		<tr key={index}>
		      			<td>{trans.date}</td>
		      			<td>{trans.city}</td>
		      			<td>{trans.state}</td>
		      		</tr>
		      	)}
	      	</tbody>
	      	</Table>
	    </div>
	    );
	 }
}

export default Transaction;
