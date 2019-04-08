import React, { Component } from 'react';
import './index.css';

import Message from './Message';

class ListFriend extends Component {
	constructor(props) {
		super(props);
		this.state = {listFriend : ""}
		
	}

	render() {
		return (
			<div className="listFriend">
			{this.state.listFriend.map((message,index) => (<Friend login={message.login} text={message.text}/>))}
			</div>
			)
	}
}

export default ListFriend;