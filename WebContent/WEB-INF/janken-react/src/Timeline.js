import React, { Component } from 'react';
import './index.css';

import ListMessage from './ListMessage';

class Timeline extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					  listMessage : [],
					  query : "",
					  friend : props.friend}
		this.setTimeline = this.setTimeline.bind(this);	
	}

	setTimeline(messages) {
		this.setState({listMessage : messages})
	}

	render() {
		return (
			<div className="timeline">
			<h1> Timeline </h1>
			<ListMessage login={this.state.login} listMessage={this.state.listMessage} setTimeline = {this.state.setTimeline}/>
			</div>
			)
	}
}

export default Timeline;