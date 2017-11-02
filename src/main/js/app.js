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
		        //console.log("employees "+ this.state.employees);
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
	        <ul>
	          
	        </ul>
	      </div>
	    );
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
