import React, { Component } from 'react';
import './index.css';

class Message extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					text : props.text,
					date : props.date}

	}

	render() {
		return (
			<div class="Message">
				<p class = "AuteurMessage"> {this.state.login} </p>
				<p class = "DateMessage">{this.state.date}</p>
				<p class = "TextMessage"> {this.state.text} </p>
			</div>
		)
	}
}

export default Message;
