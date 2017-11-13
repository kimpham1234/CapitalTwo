import {Button} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');


class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees: []};
		console.log("Constructor");
	}

	componentDidMount() {
		console.log("component did mount");
		axios.get('http://localhost:8080/api/customerAccounts')
		      .then(res => {
		        const employees = res.data._embedded.customerAccounts;//.map(obj => obj.data);
		        this.setState({ employees });
		        console.log("employees "+ this.state.employees);
		        console.log("axios "+ JSON.stringify(res.data._embedded.customerAccounts));
		      }); 
	}

	render() {
		return (
	      <div>
	      	<Button bsStyle="primary">Primary</Button>
	      	<p>Hello</p>
	        <table>
	        	<tr>
	        		<th>Name</th>
	        		<th>Email</th>
	        		<th>PhoneNo</th>
	        		<th>Gender</th>
	        		<th>Income</th>
	        	</tr>
	        	{this.state.employees.map(employee =>
	             <tr>
	             	<td>{employee.name}</td>
	             	<td>{employee.email}</td>
	             	<td>{employee.phoneNo}</td>
	             	<td>{employee.gender}</td>
	             	<td>{employee.income}</td>
	             </tr>
	          	)}
	        </table>

	        <Card></Card>
	      </div>
	    );
	 }
}

class Card extends React.Component{

	constructor(props){
		super(props);
		this.state = {cards: []};
		console.log("card constructor");
	}

	componentDidMount(){
		axios.get('http://localhost:8080/api/creditCards')
			.then(res => {
				const cards = res.data._embedded.creditCards;
				console.log("credit cardssss "+ JSON.stringify(res.data._embedded.creditCards));
				this.setState({ cards });
			});
	}

	render(){
		return (
			<div>
		        <table>
		        	<tr>
		        		<th>Date</th>
		        		<th>Amount</th>
		        	</tr>
		        	{this.state.cards.map(card =>
		             <tr>
		             	<td>{card.expirationDate}</td>
		             	<td>{card.monthlyLimit}</td>
		             </tr>
		          	)}
		        </table>
		     </div>
		)
	}
}

export default App;
