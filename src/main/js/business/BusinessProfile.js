import {Button, ControlLabel, FormControl} from 'react-bootstrap'
import {hashHistory} from 'react-router'
import BusinessTransaction from './BusinessTransaction.js'

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

var monTomon = [
	{id: 1, month: "Jan", amount: 500000},
	{id: 2, month: "Feb", amount: 50000},
	{id: 3, month: "Mar", amount: 700000},
	{id: 4, month: "Apr", amount: 200000},
	{id: 5, month: "May", amount: 40000},
	{id: 6, month: "June", amount: 340000},
	{id: 7, month: "July", amount: 640000},
	{id: 8, month: "Aug", amount: 55000},
	{id: 9, month: "Sep", amount: 530000},
	{id: 10, month: "Oct", amount: 2400000},
	{id: 11, month: "Nov", amount: 40000},
]

class BusinessProfile extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			business: [],
			transactions: [],
			all: true,
			from: Date,
			to: Date,
			initial: true,
			business_id: 0
		}
		this.compressTransaction = this.compressTransaction.bind(this);
		this.showLineChart = this.showLineChart.bind(this);
		this.handleChange = this.handleChange.bind(this);
		this.handleShow = this.handleShow.bind(this);
	}

	//[{"name":"google","reward_rate":1,"expiration":"3/26/2025",
	//"position":"Partner","verified":true,"email":"google@gmail.com","phoneNo":"408-415-7292"}]

	componentDidMount(){
		var that = this;
		if(this.state.initial || this.state.all){
			//console.log("start " + start.toString() + " end " + end.toString());
		
			axios.get('http://localhost:8080/demo/findBusiness', {
				params: {
					email: this.props.params.loginId
				}
			})
			.then((res) => {
				console.log("res "+ JSON.stringify(res.data.results[0]));
				that.setState({business_id: res.data.results[0].business_id});
				return axios.get('http://localhost:8080/demo/getBusinessTrans', {
					params: {
						business_id: res.data.results[0].business_id,
						start: "",
						end: ""
					}
				})
				.then((res2) => {
					console.log("res 2" + JSON.stringify(res2.data.results));
					var compressedTran = that.compressTransaction(res2.data.results);
					that.setState({
						business: res.data.results[0],
						transactions: compressedTran
					})
				})
			})
			this.setState({initial: false});
		}
	}

	handleChange(event){
		console.log("event "+event);
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

	handleShow(){
		var that = this;
		var start = "";
		var end = "";
		if(!this.state.all){
			start = this.state.from;
			end = this.state.to;
		}
		console.log("start " + start.toString() + " end " + end.toString());

		axios.get('http://localhost:8080/demo/getBusinessTrans', {
			params: {
				business_id: this.state.business_id,
				start: start,
				end: end
			}
		})
		.then((res) => {
			var compressedTran = that.compressTransaction(res.data.results);
			that.setState({
				transactions: compressedTran
			});
		});
	}

	showLineChart(){
		var data = [];
		var transactions = this.state.transactions.slice();

		
		for(var i = 0; i < transactions.length; i++){
			var p = {x: transactions[i].date.substring(0,11), y: transactions[i].cost};
			data.push(p);
		}

		data.reverse();
		console.log("data " + JSON.stringify(data));		
		
		hashHistory.push({
			pathname: "/lineChart",
			state: {
				data: [data]
			}
		});

	}

	compressTransaction(data){
		var transactions = data;
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
		return compressedTran;
	}

	render() {
		return (
	      <div>
	      	<h2>Business Profile</h2>
	      	<h3>Info</h3>
	      	{this.state.business && 
	      	<div>
		      	Company: <span className="text">{this.state.business.name}</span><br></br>
		      	Position: <span className="text">{this.state.business.position}</span><br></br>
		      	Contact Number: <span className="text">{this.state.business.phoneNo}</span><br></br>
		      	Verified: <span className="text">{this.state.business.verified ? "Verified" : "Not Verified"}</span><br></br>
		      	Account Expiration Date: <span className="text">{this.state.business.expiration}</span><br></br>
		    </div>
	      	}
	      	<hr></hr>
	      	<h3>Your month-to-month transaction will go here</h3>
	      	<div className="button-panel"> 
	      		<div>
	      			<Button className="primary" onClick={this.handleShow}>Show</Button>
			      	<ControlLabel className="date-label">All Transactions</ControlLabel>
				  	<FormControl id="all-checkbox" type="checkbox" name="all" onChange={this.handleChange} checked={this.state.all}/>
				    <ControlLabel className="date-label">From</ControlLabel>
				  	<FormControl className="date-input" type="date" name="from" value={this.state.from} onChange={this.handleChange}/>
				    <ControlLabel className="date-label">To</ControlLabel>
				    <FormControl className="date-input" type="date" name="to" value={this.state.to} onChange={this.handleChange}/>
			     </div>
	      		<Button className="primary" onClick={this.showLineChart}>Line Chart</Button>
	      	</div>
	      	{<BusinessTransaction transactions={this.state.transactions}/>}
	      </div>
	    );
	 }
}

export default BusinessProfile;
