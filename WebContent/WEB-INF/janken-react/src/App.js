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
import ListFriend from './ListFriend';
import NavigationBar from './NavigationBar';
import Profile from './Profile';

class MainPage extends Component {
	constructor(props) {
		super(props);
		this.state = {connected : "false",
					  currentPage : "connection",
					  getConnected : this.getConnected.bind(this),
					  login : "",
						prenom : "",
						nom : ""};
		this.getConnected = this.getConnected.bind(this)
		this.setSignUp = this.setSignUp.bind(this)
		this.setLogout = this.setLogout.bind(this)
	}

	getConnected(log,n,pre) {
		this.setState({connected : "true", currentPage : "wall", login : log, prenom : pre, nom : n});
	}

	setLogout() {
		this.setState({connected : "false",
						 currentPage : "connection"});
	}

	setSignUp () {
		this.setState({currentPage : "signUp",
					   connected : "false"});
	}

	render() {
		if((this.state.connected === "false") && (this.state.currentPage === 'signUp')) {
			return (
				<div>
					<SignUp getConnected = {this.state.getConnected}/>
				</div>);

		} else if ((this.state.connected === "false") && (this.state.currentPage === "connection")) {
			return (<div>
					<Login getConnected = {this.state.getConnected} setSignUp = {this.setSignUp} />
				</div>);

		} else if ((this.state.connected === "true") && (this.state.currentPage === "wall")) {
			return (<div>
					<NavigationBar />
					<Logout login = {this.state.login} setLogout = {this.setLogout}/>
					<section id="leftpanel">
					<Profile login = {this.state.login} nom = {this.state.nom} prenom= {this.state.prenom} me={"true"}/>
					</section>
					<section id="centerpanel">
					<section id="mainWall">
					<FormMessage login = {this.state.login} />
					<Timeline login = {this.state.login} />
					<ListFriend login = {this.state.login}/>
					</section>
					</section>
				</div>);
		}
	}


}

export default MainPage;
