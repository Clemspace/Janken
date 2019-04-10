import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

import ListMessage from './ListMessage';

class Timeline extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					  listMessage : "",
					  query : "",
					  friend : props.friend}

		axios.get("http://localhost:8080/SearchServlet/",{params:{login:this.state.login,
																  query:this.state.query,
																  friend:this.state.text}}).then(
																		response => this.response_timeline(response));
		
	}

	response_timeline(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({listMessage:response.data});
		}
	}

	render() {
		console.log(this.state.login);
		return (
			<div className="timeline">
			<h1> Timeline </h1>
			<ListMessage login={this.state.login} listMessage={this.state.listMessage}/>
			</div>
			)
	}
}

export default Timeline;