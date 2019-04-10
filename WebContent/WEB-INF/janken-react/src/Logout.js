import React, { Component } from 'react';
import './index.css';

class Logout extends Component {
	constructor(props) {
		super(props);
		this.state = {setLogout : props.setLogout};
	}
	render() {
		return (<div className="logout">
				<button onClick = {(event) => this.state.setLogout} > Logout </button>
				</div>);
	}
	
}

export default Logout;
