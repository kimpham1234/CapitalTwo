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
			item_list:[],
			open: false,
			lineChartOpen: false,
			inputDate: "2017-11-20",
			inputCity: "",
			inputState: "",
			account_id: 0,
			totalBalance: 0,
			all: true,
			from: Date,
			to: Date,
			selectedItem: -1
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
		console.log(event.target.value);
		if(event!=null && event.target.type == 'checkbox'){
			var all = this.state.all;
			this.setState({all: !all})
			event.target.checked = !all;
		}else{
			//console.log("event " + event);
			if(event!=null){
				const target = event.target;
				const value = target.value;
				const name = target.name;
				this.setState({
					[name]: value
				});
			}
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

		
		for(var i = 0; i < transactions.length; i++){
			var p = {x: transactions[i].date.substring(0,11), y: transactions[i].cost};
			data.push(p);
		}

		data.reverse();
		//console.log("data " + JSON.stringify(data));		
		
		hashHistory.push({
			pathname: "/lineChart",
			state: {
				data: [data]
			}
		});
	}

	componentDidMount(){
		axios.get('http://localhost:8080/demo/getItemList')
			.then(res =>{
				console.log(JSON.stringify(res.data));
				this.setState({item_list: res.data.results});
			});
	}

	showAll(){
		var start = "";
		var end = "";
		var item_id = -1;

		if(!this.state.all){
			start = this.state.from;
			end = this.state.to
		}

		if(this.state.selectedItem!=-1){
			item_id = this.state.selectedItem;
		}

		console.log("date " + start + " " +end);
		axios.get('http://localhost:8080/demo/getCustomerTrans', {
			params: {
				account_id: this.props.location.state.account_id,
				start: start,
				end: end,
				item_id: item_id
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
		/*
        // WORKING CREATE TRANSACTION EXAMPLE
		axios.get('http://localhost:8080/demo/createTransaction', {
            params: {
                date: "2017-1-1",
                state: "CA",
                city: "SJ",
                card_id: 26,
                business_id: 16,
                items: [
                    "6,1,1",
                    "7,1,1",
                    "8,1,1",
                ]
            }
		})
		.then(function (response) {
			console.log(response);
		})
		.catch(function (error) {
			console.log(error);
		});
        */

        
        /*
        // WORKING EXAMPLE, NOT SURE IF ALL FIELDS WORK
		axios.get('http://localhost:8080/demo/updateCustomerAccount', {
            params: {
                account_id: 1,
                fields: ["birth_day", "birth_month", "birth_year", "first_name"],
                values: ["7", "8", "1999", "\"Dankold\""] // QUOTES NEEDED FOR STRINGS
            }
		})
		.then(function (response) {
			console.log(response);
		})
		.catch(function (error) {
			console.log(error);
		});
        */
	}

	render() {
		return (
		<div id="container2">
	      <h1>Transaction of {this.props.params.loginId}</h1>
	      <p>Total spent: {this.state.totalBalance}</p>


	      <div id="filtering-panel">
	      	{/*Get All transaction*/}

	      	<Button className="primary" onClick={this.showAll}>Show All Transactions</Button>
	      	<ControlLabel className="date-label">All Transactions</ControlLabel>
		  	<FormControl id="all-checkbox" type="checkbox" name="all" onChange={this.handleChange} checked={this.state.all}/>

		  	{/* Filter by Date Range */}

		    <ControlLabel className="date-label">From</ControlLabel>
		  	<FormControl className="date-input" type="date" name="from" value={this.state.from} onChange={this.handleChange}/>
		    <ControlLabel className="date-label">To</ControlLabel>
		    <FormControl className="date-input" type="date" name="to" value={this.state.to} onChange={this.handleChange}/>

		    {/* Filter by Item Selection */}
		    <ControlLabel className="date-label">Category</ControlLabel>
		    <FormControl componentClass="select" className="date-input" name="selectedItem" placeholder="Category" onChange={this.handleChange}>
		    	<option value={-1}>Category</option>
		    	{ this.state.item_list.map((item) => (
		    		<option value={item.item_id}>{item.name}</option>
		    	  )
		    	)}
		    </FormControl>

	      </div>

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


        