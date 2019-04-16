import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

import Friend from './Friend';

class ListFriend extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
									listFriend : [],
									setFriend : props.setFriend}

		axios.get("http://localhost:8080/Projets_Janken/ListFriend",{params:{login:this.state.login}}).then(
																								response => this.response_ListFriend(response));
	}

	response_ListFriend(response) {
		console.log(response.data.friend);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({listFriend:response.data.friend});
		}
	}

	render() {
		return (
			<div className="listFriend">
			<h1> Vos amis </h1>
			{this.state.listFriend.map((friend,index) => (<Friend login={friend} setFriend={this.state.setFriend}/>))}
			</div>
			)
	}
}

export default ListFriend;
