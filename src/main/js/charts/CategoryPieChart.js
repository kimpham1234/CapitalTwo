import {PieChart, Legend } from 'react-easy-chart';
const React = require('react');
const ReactDOM = require('react-dom');

class CategoryPieChart extends React.Component {

	constructor(props) {
		super(props);
		//console.log("line chart constructor " + JSON.stringify(this.props.location.state.data));
	}


	render() {
		const customStyle = {
		    '.legend': {
		      backgroundColor: '#f9f9f9',
		      border: '1px solid #e5e5e5',
		      borderRadius: '12px',
		      fontSize: '0.8em',
		      maxWidth: '300px',
		      padding: '12px',
		      float: 'left'
		    }
		  };

		return (

			<div>
			    <h1>Pie chart</h1>
			    <div id="pieChart">
		      		<PieChart
		      			size = {600}
		      			padding = {50}
		      			labels
			   			data = {this.props.location == null ? this.props.data : this.props.location.state.data}
						/>
			    </div>

				<Legend 
					data = {this.props.location == null ? this.props.data : this.props.location.state.data}
					dataId = {'key'}
					styles = {customStyle} />
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