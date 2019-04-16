import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

class Friend extends Component {
	constructor(props) {
		super(props);
		this.state = {me : props.me,
									login : props.login,
									setFriend : props.setFriend}

	}

	send() {
		axios.get("http://localhost:8080/Projets_Janken/RemoveFriend",{params:{user_login:this.state.me,
																			friend_login:this.state.login}}).then(
																	response => this.response_RemoveFriend(response));
	}

	response_RemoveFriend(response) {
			alert(response.data["1"])
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
		}
	}

	render(){
			console.log("non"+this.state.login)
			return (
			<div className="friend" >
			{this.state.login}
			<button type="button" class="btn" onClick = {(event) => this.send()} > Retirer des amis </button>
		  </div>);
		}
}

export default Friend;
