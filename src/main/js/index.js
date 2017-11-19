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

import * as firebase from 'firebase'


var config = {
		apiKey: "AIzaSyBZv_cDdlh-mEU9_WlEbYxfo6Ezv1-RxtY",
		authDomain: "capita-b5576.firebaseapp.com",
		databaseURL: "https://capita-b5576.firebaseio.com",
		projectId: "capita-b5576",
		storageBucket: "capita-b5576.appspot.com",
		messagingSenderId: "561270156464"
};
firebase.initializeApp(config);

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
