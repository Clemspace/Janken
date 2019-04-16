import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

import Message from './Message';

class ListPeople extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					  listPeople : []};

		axios.get("http://localhost:8080/Projets_Janken/ListPeople",{params:{login:this.state.login}}).then(
																	response => this.response_ListPeople(response));

	}

	response_ListMessage(response) {
		console.log(response.data["post"]);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({listMessage:response.data});
		}
	}

	render() {
		return (
			<div className="listPeople">
			{this.state.listPeople.map((people,index) => (<Friend me={this.state.login} login={friend} />))}
			</div>
			)
	}
}

export default ListPeople;
