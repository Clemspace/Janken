import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

import Friend from './Friend';
import Profile from './Profile';

class ListFriend extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
									listFriend : [],
									friend : ""}

		axios.get("http://localhost:8080/Projets_Janken/ListFriend",{params:{login:this.state.login}}).then(
																								response => this.response_ListFriend(response));

		this.setFriend = this.setFriend.bind(this)
	}

	setFriend(f) {
		this.setState({friend : f});
		console.log("SALUT "+this.state.friend);
	}


	response_ListFriend(response) {
		console.log(response.data.friend);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			if(response.data.friend) {
				this.setState({status :""});
				this.setState({listFriend:response.data.friend});
			} else {
				this.setState({listFriend : []});
			}
		}
	}

	render() {
		if(this.state.listFriend === []) {
			return null;
		} else if (this.state.friend === "") {
			return (
				<div className="listFriend">
				<h1> Vos amis </h1>
				{this.state.listFriend.map((friend,index) => (<Friend me={this.state.login} login={friend} setFriend={this.setFriend}/>))}
				</div> )
		} else {
			return (
				<div className="listFriend">
				<h1> Vos amis </h1>
				{this.state.listFriend.map((friend,index) => (<Friend login={friend} setFriend={this.setFriend}/>))}
				<hr />
				<Profile login = {this.state.friend} nom = {""} prenom = {""} me = {"false"} friend = {"true"}/>
				</div>

				)
			}
	}
}

export default ListFriend;
