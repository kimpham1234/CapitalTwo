import {Button, Table, Panel, Form, FormGroup, FormControl, ControlLabel} from 'react-bootstrap'
import {hashHistory} from 'react-router'
import {LineChart} from 'react-easy-chart';
import BusinessTransaction from '../business/BusinessTransaction.js'


const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class Transaction extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			transactions: [],
			open: false,
			lineChartOpen: false,
			inputDate: "2017-11-20",
			inputCity: "",
			inputState: "",
			account_id: 0,
			totalBalance: 0
		}

		this.addTransaction = this.addTransaction.bind(this);
		this.toggle = this.toggle.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.showAll = this.showAll.bind(this);
		this.showLineChart = this.showLineChart.bind(this);
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

	showLineChart(){
		var data = [];
		var transactions = this.state.transactions.slice();
		console.log("show line chart " + this.state.transactions);
		
		for(var i = 0; i < transactions.length; i++){
			var p = {x: transactions.date, y: transactions.cost};
			data.push(p);
		}
		data.reverse();
		const chartData = [data];

		
		/*
		hashHistory.push({
			pathname: "/lineChart",
			state: {
				data: chartData
			}
		});*/
	}

	showAll(){
		axios.get('http://localhost:8080/demo/getCustomerTrans', {
			params: {
				account_id: this.props.location.state.account_id
			}
		})
		.then(res => {
			var transactions = res.data.results;
			var compressedTran = [];
			var preTrans = -1;
			var totalBalance = 0;

			for(var i = 0; i < transactions.length; i++){
				if(transactions[i].transaction_id!=preTrans){
					var t = {
						transaction_id: transactions[i].transaction_id,
						city: transactions[i].city,
						state: transactions[i].state,
						date: transactions[i].date,
						cost: transactions[i].cost,
						card_id: transactions[i].card_id,
						business_id: transactions[i].business_id,
						name: transactions[i].name,
						items: [
							{ category: transactions[i].category, quantity: transactions[i].quantity}
						]
					}
					totalBalance += transactions[i].cost;
					//console.log(JSON.stringify(t));
					preTrans = transactions[i].transaction_id;
					compressedTran.push(t);
				}else{
					compressedTran[compressedTran.length-1].items.push({ category: transactions[i].category, quantity: transactions[i].quantity});
				}
			}
			console.log(JSON.stringify(compressedTran));
			this.setState({
				transactions: compressedTran,
				totalBalance: totalBalance
			});
		}).catch(error => {
			console.log(error);
		});
	}

	render() {
		return (
		<div id="container2">
	      <h1>Transaction of {this.props.params.loginId}</h1>
	      <p>Total spent: {this.state.totalBalance}</p>
	      <Button className="primary" onClick={this.showAll}>Show All Transactions</Button> 
	      <Button className="primary" onClick={this.toggle}>Add Transaction</Button>
	      <Button className="primary" onClick={this.showLineChart}>Line Chart</Button>
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
	      	<BusinessTransaction transactions={this.state.transactions}/>
	    </div>
	    );
	 }
}

export default Transaction;
