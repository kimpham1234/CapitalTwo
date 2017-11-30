import {PieChart} from 'react-easy-chart';
const React = require('react');
const ReactDOM = require('react-dom');

class CategoryPieChart extends React.Component {

	constructor(props) {
		super(props);
		//console.log("line chart constructor " + JSON.stringify(this.props.location.state.data));
	}


	render() {
		return (
			<div>
			  <h1>Pie chart</h1>
		      <LineChart
		      	size = {750}
		      	padding = {50}
		      	labels
			    data = {[
			    	{key: 'A', value: 100},
			    	{key: 'B', value: 40},
			    	{key: 'C', value: 70}
			    ]}
				/>
			</div>
	    );
	 }
}

export default CategoryPieChart;

/*
{[
  [
    { x: 1, y: 20 },
    { x: 2, y: 10 },
    { x: 3, y: 25 }
  ]
]}
data={ this.props.location.state.data }
*/