import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

class Login extends Component{
	constructor(props) {
		super(props);
		this.state = {login :"",
					  password : "",
					  getConnected : props.getConnected,
					  conn_key:"",
					  setSignUp : props.setSignUp,
					  mdp : "false"};

		this.onChangeLogin = this.onChangeLogin.bind(this);
		this.onChangePassword = this.onChangePassword.bind(this);

	}

	render(){
		return (
		<div>
		<div class="login-box">
				<p class="title"> Janken</p>
				<form>
				<h1>Login</h1>
					<div class="textbox">
					<h5 class="erreur"> {this.state.status} {this.state.desc}</h5>
		     			<i class="fas fa-user"></i>
		     			<input type="text" placeholder="Username" value={this.state.login} onChange={this.onChangeLogin}/>
					</div>

					<div class="textbox">
						<i class="fas fa-lock"></i>
						<input type="password" placeholder="Password" value={this.state.password} onChange={this.onChangePassword}/>
					</div>
		     		<a href="" onClick={(event) => this.mdp()} > N&apos;oubliez pas votre mot de passe è-é </a>

		     		<input type="button" onClick={(event) => this.send()} class="btn" value="Ikuzo! 行くぞ"/>
		     		<button type="button" class="btn" onClick = {(event) => this.state.setSignUp()} > <h3> &rarr; Cr&eacute;er un compte &larr;</h3> </button>
		     	</form>
          </div>
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
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({conn_key:response.data["conn_key"]});
			this.state.getConnected(this.state.login,response.data["nomprenom"]["nom"],response.data["nomprenom"]["prenom"]);
		}
	}
}

export default Login;
