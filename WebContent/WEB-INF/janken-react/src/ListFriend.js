import React, { Component } from 'react';
import './index.css';

import Message from './Message';
import Friend from './Friend';

class ListFriend extends Component {
	constructor(props) {
		super(props);
		this.state = {listFriend : []}
		
	}

	render() {
		return (
			<div className="listFriend">
			{this.state.listFriend.map((friend,index) => (<Friend friend={"true"} login={friend.login}/>))}
			</div>
			)
	}
}

export default ListFriend;
