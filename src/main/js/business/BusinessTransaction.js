import {Button} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');



class BusinessTransaction extends React.Component {

	constructor(props) {
		super(props);
	}


	render() {
		return (
	      <div>
	      	{this.props.transaction.map((trans)=>
	      		<li key={trans.id}> 
	      			{trans.month} : ${trans.amount}
	      		</li>
	      	)};
	      </div>
	    );
	 }
}

export default BusinessTransaction;
