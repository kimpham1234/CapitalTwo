import {Button} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');



class Transaction extends React.Component {

	constructor(props) {
		super(props);
	}


	render() {
		return (
	      <div>
	      	{this.props.transaction.map((trans)=>
	      		<li key={trans.id}> 
	      			{trans.id} : ${trans.date} - {trans.cost} - {trans.city} - {trans.state}
	      		</li>
	      	)};
	      </div>
	    );
	 }
}

export default Transaction;
