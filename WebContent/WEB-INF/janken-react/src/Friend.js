import React, { Component } from 'react';
import './index.css';

class Friend extends Component {
	constructor(props) {
		super(props);
		this.state = {login : ""}
		
	}

	render(){
			return (
			<div className="friend">
			{this.state.login}
		    </div>);
		}
}

export default Friend;