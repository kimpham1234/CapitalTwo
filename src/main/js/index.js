import React from 'react'
import {render} from 'react-dom'
import {Router, Route, IndexRoute, hashHistory} from 'react-router'
import App from './App.js'
import AppNavigationBar from './AppNavigationBar.js'
import Login from './account/Login.js'
import CreateAccount from './account/CreateAccount.js'
import CustomerProfile from './customerProfile/CustomerProfile.js'
import Transaction from './customerProfile/Transaction.js'
import BusinessProfile from './business/BusinessProfile.js'

render((
	<Router history={hashHistory}>
		<Route path="/" component={AppNavigationBar}>
			<IndexRoute component={Login}/>
			<Route path="/login" component={Login}/>
			<Route path="/newCustomer" component={CreateAccount}/>
			<Route path="/customerProfile/:loginId" component={CustomerProfile}/>
			<Route path="/transaction" component={Transaction}/>
			<Route path="/businessProfile/:loginId" component={BusinessProfile}/>
		</Route>
	</Router>
),document.getElementById('react'))
