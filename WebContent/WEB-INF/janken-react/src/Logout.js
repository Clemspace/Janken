import React, { Component } from 'react';
import './index.css';

class Logout extends Component {
	constructor(props) {
		super(props);
		this.state = {setLogout : props.setLogout};
	}
	render() {
		return (<button onClick = {(event) => this.state.setLogout} > Logout </button>);
	}
	
}

export default Logout;
