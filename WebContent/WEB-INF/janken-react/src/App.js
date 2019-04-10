import React, { Component } from 'react';
import logo from './logo.svg';
import './index.css';

import NavigationPanel from './NavigationPanel';
import SignUp from './SignUp';
import FormMessage from './FormMessage';
import ListMessage from './ListMessage';
import Timeline from './Timeline';
import Login from './Login';
import Logout from './Logout';

class MainPage extends Component {
	constructor(props) {
		super(props);
		this.state = {connected : "false",
					  currentPage : "connection",
					  getConnected : this.getConnected.bind(this),
					  login : ""};
		this.getConnected = this.getConnected.bind(this)
	}
	
	getConnected(log) {
		this.setState({connected : "true", currentPage : "wall", login : log});
	}
	
	setLogout() {
		this.setState({connected : "false",
						 currentPage : "connection"});
	}
	
	render() {
		if(this.state.currentPage === 'signUp') {
			return (
				<div>
					<SignUp/>		
				</div>);

		} else if ((this.state.connected === "false") && (this.state.currentPage === "connection")) {
			return (<div>
					<Login getConnected = {this.state.getConnected}/>
					<button class="createUser" onClick = {(event) => this.setSignUp }> <h3> &rarr; Cr&eacute;er un compte &larr;</h3> </button>
				</div>);

		} else if ((this.state.connected === "true") && (this.state.currentPage === "wall")) {
			return (<div>
					<Logout setLogout = {this.state.setLogout}/>
					<FormMessage login = {this.state.login} />
					<Timeline login = {this.state.login} />
				</div>);
		}
	}

	setSignUp () {
		this.setState({currentPage : "signUp"});
	}
}

export default MainPage;
