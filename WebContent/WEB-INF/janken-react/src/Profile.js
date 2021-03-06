import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

import ListMessage from './ListMessage';
import ListMessageFriend from './ListMessageFriend';

class Profile extends Component {
	constructor(props) {
		super(props);
		this.state = {
						profilepic : props.profilepic,
						login : props.login,
					  nom : props.nom,
					  prenom : props.prenom,
					  friend : props.friend,
						me : props.me}

	}

	send() {
		axios.get("http://localhost:8080/Projets_Janken/AddFriend",{params:{user_login:this.state.me,
																			friend_login:this.state.login}}).then(
																	response => this.response_friend(response));
	}

	response_friend(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
		}
	}

	render(){
		if(this.state.me === "true") {
			return (
			<div className="user">
			<div className="img">
			</div>
			<p>{this.state.login}</p>
			<pre class="nomprenom">{this.state.nom} {this.state.prenom} </pre>
		  </div>
		  );
		}
		else if (this.state.friend === "true") {
			return (
			<div className="user">
			<h1> Profil de {this.state.login} </h1>
			<ListMessageFriend login = {this.state.login} />
		  </div>
		    );
		}
		else if (this.state.friend === "false") {
			return (
			<div className="user">
			{this.state.login}
			{this.state.nom}
			{this.state.prenom}
			<button class="addFriend" type="button" onClick={(event) => this.send()}> Ajouter en ami </button>
			<ListMessage login = {this.state.login} />
		    </div>
		    );
		}
	}


}

export default Profile;
