import React, { Component } from 'react';
import logo from './logo.svg';
import './index.css';
import Login from './Login';
import Logout from './Logout';

class NavigationPanel extends Component {
	constructor(props) {
		super(props);
		this.state = {isconnected : props.connected,
					  getConnected : props.getConnected,
					  setLogout : props.setLogout};
	}
	
	render() {

		return (<nav> </nav>)
		// console.log(this.state.isconnected);
		// this.setState(isconnected);
		// if ( this.state.isconnected === "false"){
		// 	return( <nav>
		// 	<Login getConnected = {this.state.getConnected}/>		
		// 	</nav>);
		// }else if(this.state.isconnected === "true"){
		// return( <nav>
		// 	<Logout setLogout = {this.state.setLogout}/>
			
		// 	</nav>);
		
		// }
	}
}

export default NavigationPanel;
