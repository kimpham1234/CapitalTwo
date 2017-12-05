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
			card_list:[],
			business_list:[],
			open: false,
			lineChartOpen: false,
			inputDate: "2017-11-29",
			inputCity: "",
			inputState: "",
			inputBusiness: -1,
			inputCard: -1,
			inputCategory: -1,
			inputQuantity: 0,
			inputPrice: 0,
			inputItemList: [],
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
		this.showPieChart = this.showPieChart.bind(this);
		this.handleAddItem = this.handleAddItem.bind(this);
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
		console.log("items " + JSON.stringify(this.state.inputItemList));
	
		axios.get('http://localhost:8080/demo/createTransaction', {
            params: {
                date: this.state.inputDate.toString(),
                state: this.state.inputState,
                city: this.state.inputCity,
                card_id: this.state.inputCard,
                business_id: this.state.inputBusiness,
                items: this.state.inputItemList
            }
		})
		.then(function (response) {
			console.log(response);
			alert(response);
		})
		.catch(function (error) {
			console.log(error);
		});
	}	

	handleAddItem(){
		var items = this.state.inputItemList.slice();
		var i = this.state.inputCategory +","+this.state.inputQuantity+","+this.state.inputPrice;
		items.push(i);
		this.setState({inputItemList: items});
		console.log("items " + JSON.stringify(this.state.inputItemList));
	}


	showLineChart(){
		var data = [];
		var transactions = this.state.transactions.slice();

		var title = "Transaction timeline ";
		if(!this.state.all){
			title += " from " + this.state.from + " to " + this.state.to;
		}

		
		for(var i = 0; i < transactions.length; i++){
			var p = {x: transactions[i].date.substring(0,11), y: transactions[i].cost};
			data.push(p);
		}

		data.reverse();
		//console.log("data " + JSON.stringify(data));		
		
		hashHistory.push({
			pathname: "/lineChart",
			state: {
				data: [data],
				title: title
			}
		});
	}

	showPieChart(){
		var start = "";
		var end = "";
		var title = "Spending Category "
		if(!this.state.all){
			start = this.state.from;
			end = this.state.to
			title+= " from " + start + " to " + end;
		}



		axios.get('http://localhost:8080/demo/getCustomerCategorizedTrans', {
			params: {
				start: start,
				end: end
			}
		})
		.then(res => {
				var results = res.data.results;
				var data = [];
				for(var i = 0; i < results.length; i++){
					var p = {key: results[i].category, value: results[i].amount};
					data.push(p);
				}

				hashHistory.push({
				pathname: "/pieChart",
				state: {
					data: data,
					title: title
				}
			});
		});
		
		
	}

	componentDidMount(){
		axios.get('http://localhost:8080/demo/getItemList')
			.then(res =>{
				console.log(JSON.stringify(res.data));
				this.setState({item_list: res.data.results});
			});
		axios.get('http://localhost:8080/demo/findAllBusiness')
			.then(res => {
				console.log(JSON.stringify(res.data));
				this.setState({business_list: res.data.results});
			});
		axios.get('http://localhost:8080/demo/getCustomerCards', {
				params: {
					account_id: this.props.location.state.account_id
				}
			})
			.then(res => {
				console.log(JSON.stringify(res.data));
				this.setState({card_list: res.data.results});
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
                var transCost = transactions[i].unit_price*transactions[i].quantity;
				if(transactions[i].transaction_id!=preTrans){
					var t = {
						transaction_id: transactions[i].transaction_id,
						city: transactions[i].city,
						state: transactions[i].state,
						date: transactions[i].date,
						cost: transCost,//transactions[i].cost,
						card_id: transactions[i].card_id,
                        card_number: transactions[i].card_number,
						business_id: transactions[i].business_id,
						name: transactions[i].name,
						items: [
							{ category: transactions[i].category, quantity: transactions[i].quantity}
						]
					}
					//totalBalance += transactions[i].cost;
					//console.log(JSON.stringify(t));
					preTrans = transactions[i].transaction_id;
					compressedTran.push(t);
				}else{
                    var t = compressedTran[compressedTran.length-1];
                    t.items.push({ category: transactions[i].category, quantity: transactions[i].quantity});
                    t.cost += transCost;
				}
                totalBalance += transCost;
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
                    "6,1,1", //id, price, 
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
		<div className="container2">
	      <h1>Transaction of {this.props.params.loginId}</h1>
	      <p>Total spent: {this.state.totalBalance}</p>


	      <div id="filtering-panel">
	      	{/*Get All transaction*/}

	      	<Button className="primary" onClick={this.showAll}>Show Transactions</Button>
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
	      <Button className="primary" onClick={this.showLineChart}>View Timeline</Button>
	      <Button className="primary" onClick={this.showPieChart}>View Spending Category</Button>
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

				<FormGroup>
				      <ControlLabel>Card</ControlLabel>
				      {' '}
				      <FormControl componentClass="select" className="date-input" name="inputCard" placeholder="Card" onChange={this.handleChange}>
					    	<option value={-1}>Card</option>
					    	{ this.state.card_list.map((card) => (
					    		<option value={card.card_id}>{'XXXX' + card.card_number.substring(12)}</option>
					    	  )
					    	)}
					  </FormControl>
				    </FormGroup>
				    {' '}

				    <FormGroup controlId="formInlineEmail">
				      <ControlLabel>Business</ControlLabel>
				      {' '}
				      <FormControl componentClass="select" className="date-input" name="inputBusiness" placeholder="Business" onChange={this.handleChange}>
					    	<option value={-1}>Business</option>
					    	{ this.state.business_list.map((business) => (
					    		<option value={business.business_id}>{business.name}</option>
					    	  )
					    	)}
					  </FormControl>
				    </FormGroup>
				    {' '}

				    <FormGroup controlId="formInlineEmail">
				      <ControlLabel>Category</ControlLabel>
				      {' '}
				      <FormControl componentClass="select" className="date-input" name="inputCategory" placeholder="Category" onChange={this.handleChange}>
					    	<option value={-1}>Category</option>
					    	{ this.state.item_list.map((item) => (
					    		<option value={item.item_id}>{item.name}</option>
					    	  )
					    	)}
					  </FormControl>
				    </FormGroup>
				    {' '}

				    <FormGroup controlId="formInlineName">
				      <ControlLabel>Quantity</ControlLabel>
				      {' '}
				      <FormControl type="number" name="inputQuantity" value={this.state.inputQuantity} onChange={this.handleChange}/>
				    </FormGroup>
				    {' '}

				    <FormGroup controlId="formInlineName">
				      <ControlLabel>Price</ControlLabel>
				      {' '}
				      <FormControl type="number" name="inputPrice" value={this.state.inputPrice} onChange={this.handleChange}/>
				    </FormGroup>
				    {' '}

				    <Button onClick={this.handleAddItem}>Add Category</Button>


	      	</Panel>
	      	<BusinessTransaction transactions={this.state.transactions}/>
	    </div>
	    );
	 }
}

export default Transaction;


        
