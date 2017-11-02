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
		      /*
		axios.get('http://localhost:8080/api/customerAccounts')
			.then(function(response){
			//	console.log("axios "+ JSON.stringify(response));
			//	console.log("axios 1"+ JSON.stringify(response.data));
			//	console.log("axios 2"+ JSON.stringify(response.data._embedded));
				console.log("axios"+ JSON.stringify(response.data._embedded.customerAccounts));
			//	console.log("axios 3"+ JSON.stringify(response.data._embedded.customerAccounts[0]));
			    that.setState(employees: response.data._embedded.customerAccounts);
				console.log("employees:");
			    console.log("axios");
			}.bind(this));*/
	}

	render() {
		return (
	      <div>
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
		        		<th>LoginId</th>
		        		<th>Amount</th>
		        	</tr>
		        	{this.state.cards.map(card =>
		             <tr>
		             	<td>{card.expirationDate}</td>
		             	<td>{card.loginId}</td>
		             	<td>{card.monthlyLimit}</td>
		             </tr>
		          	)}
		        </table>
		     </div>
		)
	}
}



/*
{this.state.employees.map(employee =>
	            <li key={employee.name}>{employee.email}</li>
	          )}
*/


ReactDOM.render(
	<App />,
	document.getElementById('react')
)
