import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

import Message from './Message';

class ListMessage extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					  listMessage : [],
					  setTimeline : props.setTimeline};

		axios.get("http://localhost:8080/Projets_Janken/Timeline",{params:{login:this.state.login}}).then(
																	response => this.response_ListMessage(response));

	}

	response_ListMessage(response) {
		console.log(response.data["post"]);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({listMessage:response.data["post"]});
			this.state.setTimeline(response.data);
		}
	}

	render() {
		return (
			<div className="listMessage">
			{this.state.listMessage.map((friend,index) => (<Message login={friend.auteur} date = {friend.date} text={friend.message}/>))}
			</div>
			)
	}
}

export default ListMessage;
