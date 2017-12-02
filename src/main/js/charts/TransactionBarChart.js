import {BarChart} from 'react-easy-chart';
const React = require('react');
const ReactDOM = require('react-dom');

class TransactionBarChart extends React.Component {

	constructor(props) {
		super(props);
		//console.log("line chart constructor " + JSON.stringify(this.props.location.state.data));
	}


	render() {
		return (
			<div>
				<h1>Bar chart</h1>
		      	<BarChart
			      	colorBars
			      	axes
			      	grid
			      	axesLabels={{x: "Year", y: "$"}}
			      	xType={'text'}
			      	width={750}
				    height={500}
				    margin={{top: 10, right: 30, bottom: 50, left: 50}}
				    data={[
				      {x: 'A', y: 20},
				      {x: 'B', y: 30},
				      {x: 'C', y: 40},
				      {x: 'D', y: 20},
				      {x: 'E', y: 40},
				      {x: 'F', y: 25},
				      {x: 'G', y: 5}
				    ]}
				/>
			</div>
	    );
	 }
}

export default TransactionBarChart;