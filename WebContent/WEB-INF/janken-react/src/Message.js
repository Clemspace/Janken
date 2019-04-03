import React, { Component } from 'react';
import './index.css';

class Message extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					text : props.text}
		
	}
	
	render() {
		return (
			<div>
				<p> {this.state.login} </p>
				<p> {this.state.text} </p>
			</div>
		)
	}
}

export default Message;
