import React from 'react'
import {render} from 'react-dom'
import {Router, Route, IndexRoute, hashHistory} from 'react-router'
import App from './App.js'
import AppNavigationBar from './AppNavigationBar.js'
import Login from './account/Login.js'
import CreateCustomer from './account/CreateCustomer.js'
import CustomerProfile from './customerProfile/CustomerProfile.js'
import Transaction from './customerProfile/Transaction.js'

render((
	<Router history={hashHistory}>
		<Route path="/" component={AppNavigationBar}>
			<IndexRoute component={Login}/>
			<Route path="/login" component={Login}/>
			<Route path="/newCustomer" component={CreateCustomer}/>
			<Route path="/customerProfile" component={CustomerProfile}/>
			<Route path="/transaction" component={Transaction}/>
		</Route>
	</Router>
),document.getElementById('react'))
