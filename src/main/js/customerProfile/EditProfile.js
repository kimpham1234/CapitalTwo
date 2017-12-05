import {Button, Form, FormGroup, FormControl, Col, ControlLabel, Radio, FieldGroup} from 'react-bootstrap'
import {hashHistory} from 'react-router'

const React = require('react');
const ReactDOM = require('react-dom');
const axios = require('axios');

class EditProfile extends React.Component {

	constructor(props) {
		super(props);

		this.state = {
			day: 0,
			month: 0,
			year:0,
			ethnicity: 0,
			gender: 0,
			first: "",
			middle: "",
			last: ""
		}

		this.handleSubmit = this.handleSubmit.bind(this);
		this.handleChange = this.handleChange.bind(this);		
	}

	componentDidMount(){
		var user = this.props.location.state.customer;
		var eth = 0;
		var gen = 0;
		var id = Number(user._links.self.href.substring("http://localhost:8080/api/customerAccounts/".length));

		console.log(JSON.stringify(user));

		if(user.gender === "MALE")
			gen = 0;
		else gen = 1;

		if(user.ethnicity === "WHITE") eth = 0;
		else if(user.ethnicity === "LATINO") eth = 1;
		else if(user.ethnicity === "BLACK") eth = 2;
		else if(user.ethnicity === "NATIVE") eth = 3;
		else if(user.ethnicity === "ASIAN") eth = 4;
		else eth = 5;

		this.setState({
			day: user.birthDay,
			month: user.birthMonth,
			year: user.birthYear,
			ethnicity: eth,
			gender: gen,
			first: user.firstName,
			middle: user.middleName,
			last: user.lastName,
			account_id: id
		});
	}


	handleSubmit(){
		hashHistory.push('/customerProfile');
	    var values = []
        values.push(this.state.day);
        values.push(this.state.month);
        values.push(this.state.year);
        values.push("\""+this.state.first+"\"");
        values.push("\""+this.state.middle+"\"");
        values.push("\""+this.state.last+"\"");
        values.push(this.state.ethnicity);
        values.push(this.state.gender);
	
		axios.get('http://localhost:8080/demo/updateCustomerAccount', {
            params: {
                account_id: 1,
                fields: ["birth_day", "birth_month", "birth_year",
                		 "first_name", "middle_name", "last_name",
                		 "ethnicity", "gender"],
                values: values // QUOTES NEEDED FOR STRINGS
            }
		})
		.then(function (response) {
			console.log(response);
		})
		.catch(function (error) {
			console.log(error);
		});
		
	}

	handleChange(event){
		console.log("event " + event.target.value);
		if(event!=null){
			const target = event.target;
			const value = target.value;
			const name = target.name;
			this.setState({
				[name]: value
			});
		}
	}

	render() {
		return (
	      <div>
	      	 <Form horizontal onSubmit={this.handleSubmit}>
	      	 	<FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        First Name
			      </Col>
			      <Col sm={10}>
			        <FormControl type="text" placeholder="First" name="first"
			        	onChange={this.handleChange}
			        	value={this.state.first}/>
			      </Col>
			    </FormGroup>

			    <FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        Last Name
			      </Col>
			      <Col sm={10}>
			        <FormControl type="text" placeholder="Last" name="last"
			        	onChange={this.handleChange}
			        	value={this.state.last} />
			      </Col>
			    </FormGroup>

			    <FormGroup controlId="formHorizontalEmail">
			      <Col componentClass={ControlLabel} sm={2}>
			        Middle
			      </Col>
			      <Col sm={10}>
			        <FormControl type="text" placeholder="Middle" name="middle"
			        	onChange={this.handleChange}
			        	value={this.state.middle} />
			      </Col>
			    </FormGroup>

			    <FormGroup controlId="formHorizontalPassword">
			      <Col componentClass={ControlLabel} sm={2}>
			        DOB
			      </Col>
			      <Col sm={3}>
			        <FormControl type="number" name="day" placeholder="Day" 
			        onChange={this.handleChange}
			        value={this.state.day} />
			      </Col>

			      <Col sm={3}>
			        <FormControl type="number" name="month" placeholder="Month" 
			        onChange={this.handleChange}
			        value={this.state.month} />
			      </Col>

			      <Col sm={3}>
			        <FormControl type="number" name="year" placeholder="Year" 
			        onChange={this.handleChange}
			        value={this.state.year} />
			      </Col>
			    </FormGroup>

			    <FormGroup>
			      <Col componentClass={ControlLabel} sm={2}>
			        Gender
			      </Col>
			      <Radio name="gender" value="0" inline 
			      onChange={this.handleChange}>
			        Male
			      </Radio>
			      {' '}
			      <Radio name="gender" value="1" inline 
			      onChange={this.handleChange}>
			        Female
			      </Radio>
			      {' '}
			    </FormGroup>

			    <FormGroup>
			      <Col componentClass={ControlLabel} sm={2}>
			        Ethnicity
			      </Col>
			      <Radio name="ethnicity" value="0" inline 
			      onChange={this.handleChange}>
			        White
			      </Radio>
			      {' '}
			      <Radio name="ethnicity" value="1" inline 
			      onChange={this.handleChange}>
			        Hispanic or Latino
			      </Radio>
			      {' '}
			      <Radio name="ethnicity" value="2" inline 
			      onChange={this.handleChange}>
			        Native American or American Indian
			      </Radio>
			      {' '}
			      <Radio name="ethnicity" value="3" inline 
			      onChange={this.handleChange}>
			      	African American
			      </Radio>
			      {' '}
			      <Radio name="ethnicity" value="4" inline 
			      onChange={this.handleChange}>
			        Asian or Pacific Islander
			      </Radio>
			      {' '}
			      <Radio name="ethnicity" value="5" inline 
			      onChange={this.handleChange}>
			        Other
			      </Radio>
			      {' '}
			    </FormGroup>

			    <FormGroup>
			      <Col smOffset={2} sm={10}>
			        <Button type="submit">
			          Save
			        </Button>
			      </Col>
			    </FormGroup>

			    {this.state.error}
			</Form>
	      </div>
	    );
	 }
}

export default EditProfile;