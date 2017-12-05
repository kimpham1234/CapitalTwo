import {LineChart} from 'react-easy-chart';
const React = require('react');
const ReactDOM = require('react-dom');

class TransactionLineChart extends React.Component {

	constructor(props) {
		super(props);
		console.log("line chart constructor " + JSON.stringify(this.props.location.state.data));
	}


	render() {
		return (
			<div>
			  <h1>Line chart</h1>
		      <LineChart
		      	xType={'text'}
			    axes
			    grid
			    dataPoints
			    lineColors={['tomato']}
			    margin={{top: 50, right: 50, bottom: 50, left: 100}}
			    axisLabels={{x: 'Date', y: '$'}}
			    width={1000}
			    height={500}
			    data={ this.props.location.state.data }
				/>
			</div>
	    );
	 }
}

export default TransactionLineChart;
