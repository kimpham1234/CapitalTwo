import {PieChart} from 'react-easy-chart'
import {Button, DropdownButton, MenuItem} from 'react-bootstrap'
import CategoryPieChart from '../charts/CategoryPieChart.js'


const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');
const AGE_INTERVAL = 5;

class CustomerDemograph extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			customerData: [{key: 'A', value: 50},{key: 'B', value: 50}],
			purchaseData: [{key: 'a', value: 50},{key: 'b', value: 50}],
			title: ""
		}
		this.showAge = this.showAge.bind(this);
		this.showGender = this.showGender.bind(this);
		this.showEthnicity = this.showEthnicity.bind(this);
	}


	showAge(){ //[{"count":18,"sum":6057677,"group_value":1},{"count":2,"sum":837915,"group_value":11}]
		var that = this;
		console.log(this.props.params.id);
		axios.get('http://localhost:8080/demo/getBusinessStatsByGroup', {
			params: {
				business_id: that.props.params.id,
				group: 2,
				age_interval: AGE_INTERVAL
			}
		})
		.then(res => {
			console.log(JSON.stringify(res.data.results));
			var resData = res.data.results;
			var data = [];
			var data2 = [];
			//low = group_value * 20, high = (group_value+1)*20-1
			for(var i = 0; i < resData.length; i++){
				var low = resData[i].group_value*AGE_INTERVAL;
				var high = (resData[i].group_value+1)*AGE_INTERVAL;

				var p = {key: low +" - "+high, value: resData[i].count};
				var p2 = {key: low +" - "+high + " ", value: resData[i].sum};
				data.push(p);
				data2.push(p2);
			}
			this.setState({
				customerData: data,
				purchaseData: data2,
				title: "Customer Demographics and Spending by Age Group"
			});
		});
	}

	showGender(){//[{"count":13,"sum":4994720,"group_value":0},{"count":7,"sum":1900872,"group_value":1}]
		var that = this;
		axios.get('http://localhost:8080/demo/getBusinessStatsByGroup', {
			params: {
				business_id: that.props.params.id,
				group: 1,
				age_interval: -1
			}
		})
		.then(res => {
			console.log(JSON.stringify(res.data.results));

			var resData = res.data.results;
			var data = [];
			var data2 = [];
			
			//0 male, 1 female
			for(var i = 0; i < resData.length; i++){
				var label = resData[i].group_value == 0 ? "Male" : "Female";
				var p = {key: label, value: resData[i].count};
				var p2 = {key: label+ " ", value: resData[i].sum};
				data.push(p);
				data2.push(p2);
			}
			this.setState({
				customerData: data,
				purchaseData: data2,
				title: "Customer Demographics and Spending by Gender Group"
			});
		});
	}

	showEthnicity(){//[{"count":11,"sum":3781766,"group_value":0},{"count":5,"sum":1578363,"group_value":2},{"count":4,"sum":1535463,"group_value":4}]
		var that = this;
		axios.get('http://localhost:8080/demo/getBusinessStatsByGroup', {
			params: {
				business_id: that.props.params.id,
				group: 0,
				age_interval: -1
			}
		})
		.then(res => {
			console.log(JSON.stringify(res.data.results));

			var resData = res.data.results;
			var data = [];
			var data2 = [];
			
			//0 white, 1 latino, 2 black, 3 native, 4 asian, 5 other
			for(var i = 0; i < resData.length; i++){
				var label = "";
				switch(resData[i].group_value){
					case 0:
						label = "White";
						break;
					case 1: 
						label = "Latino";
						break;
					case 2:
						label = "Black";
						break;
					case 3:
						label = "Native";
						break;
					case 4: 
						label = "Asian";
						break;
					case 5: 
						label = "Other";
						break;
					default:
						label = "Other";
						break;
				}

				var p = {key: label, value: resData[i].count};
				var p2 = {key: label+ " ", value: resData[i].sum};
				data.push(p);
				data2.push(p2);
			}
			this.setState({
				customerData: data,
				purchaseData: data2,
				title: "Customer Demographics and Spending by Ethnicity Group"
			});
		});
	}

	render() {
		return(
			<div>
				<h3>Customer Demographics</h3>
				<div>
					<p>View Customer demo by </p>
					<Button onClick={this.showAge}>Age</Button>
					<Button onClick={this.showGender}>Gender</Button>
					<Button onClick={this.showEthnicity}>Ethnicity</Button>
				</div>
				<h3>{this.state.title}</h3>
				<CategoryPieChart data={this.state.customerData}/>
				<CategoryPieChart data={this.state.purchaseData}/>
			</div>
		);
	 }
}

export default CustomerDemograph;
/*


*/
