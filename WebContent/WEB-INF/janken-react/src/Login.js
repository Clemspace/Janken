import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

class Login extends Component{
	constructor(props) {
		super(props);
		this.state = {login :"",
					  password : "",
					  getConnected : props.getConnected,
					  conn_key:""};

		this.onChangeLogin = this.onChangeLogin.bind(this);
		this.onChangePassword = this.onChangePassword.bind(this);
	}

	render(){
		return (
		<div>
			<h3 id="rouge"> Connexion </h3>
				<form>
					<table id="login" style={{height:'200px'}}>
		   				<tr> 
		     				<td> <label for='login_id'>Login</label> </td>
		     				<td> <input class="form" type="text" value={this.state.login} onChange={this.onChangeLogin}/> </td>
		   				</tr>
		   				<tr> 
		     				<td> <label for='password'>Password</label> </td>
		     				<td> <input class="form" type="password" value={this.state.password} onChange={this.onChangePassword}/> </td>       
		   				</tr>
		   				<tr> 
		     				<td colspan='2' align="right"> <a class="password" href=""> N&apos;oubliez pas votre mot de passe è-é</a> </td>
		   				</tr>
		   				<tr>
		     				<td colspan='2' align="right"/>
		     					<input type="button" onClick={(event) => this.send()} class="btn btn-danger" value="Ikuzo! 行くぞ"/>
		    				</tr> 
		     		</table>
		     	</form>
          </div>);
	}

	onChangeLogin (event) {
	    this.setState({
	      login: event.target.value
	    });
  	}

  	onChangePassword (event) {
	    this.setState({
	      password: event.target.value
	    });
  	}

	send() {
		axios.get("http://localhost:8080/Projets_Janken/Login",{params:{login:this.state.login,
																			password:this.state.password}}).then(
																	response => this.response_login(response));
	}

	response_login(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({conn_key:response.data["conn_key"]});
			this.state.getConnected();
		}
	}
}

export default Login;
