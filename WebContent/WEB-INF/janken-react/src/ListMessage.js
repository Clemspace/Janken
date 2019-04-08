import React, { Component } from 'react';
import './index.css';

import Message from './Message';

class ListMessage extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					  listMessage : ""}
		
	}

	render() {
		return (
			<div className="listMessage">
			{this.state.listMessage.map((friend,index) => (<Message login={friend.login}/>))}
			</div>
			)
	}
}

export default ListMessage;