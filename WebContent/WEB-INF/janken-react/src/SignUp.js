import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

class SignUp extends Component {

	constructor(props) {
		super(props);
		this.state = {nom : "",
					  prenom : "",
					  login : "",
					  password : "",
					  getConnected : props.getConnected}


		this.onChangeNom = this.onChangeNom.bind(this);
		this.onChangePrenom = this.onChangePrenom.bind(this);
		this.onChangeLogin = this.onChangeLogin.bind(this);
		this.onChangePassword = this.onChangePassword.bind(this);
	}

	send() {
		axios.get("http://localhost:8080/Projets_Janken/CreateUser",{params:{nom:this.state.nom,
																			 prenom:this.state.prenom,
																			login:this.state.login,
																			password:this.state.password}}).then(
																	response => this.response_SignUp(response));
	}

	response_SignUp(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "Error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.state.getConnected(this.state.login,this.state.nom,this.state.prenom);
		}
	}

	onChangeNom (event) {
	    this.setState({
	      nom: event.target.value
	    });
  	}
  	onChangePrenom (event) {
	    this.setState({
	      prenom: event.target.value
	    });
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

	render() {
		return (<div class="login-box">
			<h3>Cr&eacute;er un compte</h3>
			<h4 class="erreur"> {this.state.status} {this.state.desc}</h4>
			<form>
				<div class="textbox">
				<input value={this.state.nom} onChange={this.onChangeNom} placeholder="Nom" type="text" name="nom"/>
				</div>

				<div class="textbox">
				<input value={this.state.prenom} onChange={this.onChangePrenom}type="text" placeholder="Prénom" name="prenom"/>
				</div>

				<div class="textbox">
				<input value={this.state.login} onChange={this.onChangeLogin}type="text" placeholder="Login" name="login"/>
				</div>

				<div class="textbox">
				<input value={this.state.password} onChange={this.onChangePassword} type="password" placeholder="Password" name="password"/>
				</div>

			<input type="button" class="btn" onClick={(event) => this.send()} value="Ikuzo! 行くぞ"/>
			</form>
		</div>)
	}
}

export default SignUp;
