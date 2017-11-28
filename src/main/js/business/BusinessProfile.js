import {Button} from 'react-bootstrap'
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
			transaction: []
		}
		this.compressTransaction = this.compressTransaction.bind(this);
	}

	//[{"name":"google","reward_rate":1,"expiration":"3/26/2025",
	//"position":"Partner","verified":true,"email":"google@gmail.com","phoneNo":"408-415-7292"}]

	componentDidMount(){
		var that = this;
	
		axios.get('http://localhost:8080/demo/findBusiness', {
			params: {
				email: this.props.params.loginId
			}
		})
		.then((res) => {
			console.log("res "+ JSON.stringify(res.data.results[0]));
			return axios.get('http://localhost:8080/demo/getBusinessTrans', {
				params: {
					business_id: res.data.results[0].business_id
				}
			})
			.then((res2) => {
				console.log("res 2" + JSON.stringify(res2.data.results));
				var compressedTran = that.compressTransaction(res2.data.results);
				that.setState({
					business: res.data.results[0],
					transaction: compressedTran
				})
			})
		})
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
	      	{console.log("transaction " + this.state.transaction)}
	      	{<BusinessTransaction transactions={this.state.transaction}/>}
	      </div>
	    );
	 }
}

export default BusinessProfile;
