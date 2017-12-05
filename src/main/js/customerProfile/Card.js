import {Button, Panel} from 'react-bootstrap'
const React = require('react');
const ReactDOM = require('react-dom');

var sampleCards = [
	{cardNumber: 12344, year: 2012},
	{cardNumber: 12344, year: 2012},
	{cardNumber: 12344, year: 2012},
	{cardNumber: 12344, year: 2012}
];

class Card extends React.Component {

	constructor(props) {
		super(props);
			
		this.state = {
			cards: this.props.cards,
			open: false
		};
		this.toggle = this.toggle.bind(this);
	}

	toggle(){
		var open = this.state.open;
		open = !open;
		this.setState({open: open});
	}

	render() {
		return (
	      <div>
	      	<h3 className="collapse-trigger" onClick={this.toggle}>View Cards</h3>
	      	<Panel id="card-panel" collapsible expanded={this.state.open}>
		      	{this.state.cards.map((card, index)=>
		      		<li key={index}> 
		      			Card: {card.cardNumber}<br></br>
		      			Monthly Limit: {card.monthlyLimit}<br></br>
		      			Monthly Spent: {card.monthlySpent}<br></br>
		      			Expiration Date: {card.expirationMonth}/{card.expirationDay}/{card.expirationYear}
		      		</li>
		      	)};
	      	</Panel>
	      </div>
	    );
	 }
}

export default Card;
       
       