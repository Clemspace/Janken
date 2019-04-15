import React, { Component } from 'react';
import './index.css';

class Friend extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
									setFriend : props.setFriend}

	}

	render(){
			console.log("non"+this.state.login)
			return (
			<div className="friend" onClick = {(event) => this.state.setFriend(this.state.login)} >
			{this.state.login}
		  </div>);
		}
}

export default Friend;
