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
					    axes
					    margin={{top: 10, right: 10, bottom: 50, left: 50}}
					    axisLabels={{x: 'Date', y: '$'}}
					    width={250}
					    height={250}
					    data={ this.props.location.state.data }
				/>
			</div>
	    );
	 }
}

export default TransactionLineChart;

/*
{[
  [
    { x: 1, y: 20 },
    { x: 2, y: 10 },
    { x: 3, y: 25 }
  ]
]}
*/