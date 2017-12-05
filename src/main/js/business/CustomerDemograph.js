import {PieChart} from 'react-easy-chart'
import {Button} from 'react-bootstrap'
import CategoryPieChart from '../charts/CategoryPieChart.js'


const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class CustomerDemograph extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			data: [{key: 'A', value: 50},{key: 'B', value: 50}]
		}
		this.showAge = this.showAge.bind(this);
		this.showGender = this.showGender.bind(this);
		this.showEthnicity = this.showEthnicity.bind(this);
	}


	showAge(){
		var that = this;
		console.log(this.props.params.id);
		axios.get('http://localhost:8080/demo/getBusinessStatsByGroup', {
			params: {
				business_id: that.props.params.id,
				group: 2,
				age_interval: 20
			}
		})
		.then(res => {
			console.log(JSON.stringify(res.data.results));
		});
	}

	showGender(){
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
		});
	}

	showEthnicity(){
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
		});
	}


	render() {
		return(
			<div>
				<h3>Customer Demo</h3>
				<Button onClick={this.showAge}>Age</Button>
				<Button onClick={this.showGender}>Gender</Button>
				<Button onClick={this.showEthnicity}>Ethnicity</Button>
				<CategoryPieChart data={this.state.data}/>
			</div>
		);
	 }
}

export default CustomerDemograph;
