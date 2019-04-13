import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

class Logout extends Component {
	constructor(props) {
		super(props);
		this.state = {setLogout : props.setLogout,
									login : props.login};
	}
	render() {
		return (<div className="logout">
				<button onClick = {(event) => this.send()} > Logout </button>
				</div>);
	}
	send() {
		axios.get("http://localhost:8080/Projets_Janken/Logout",{params:{login:this.state.login,
																			}}).then(
																	response => this.response_logout(response));
	}

	response_logout(response) {
		console.log(response.data);

		this.setState({status :""});
		this.setState({conn_key:""});
		this.state.setLogout();
		}
	}

 export default Logout;
