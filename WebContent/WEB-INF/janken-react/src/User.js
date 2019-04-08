import React, { Component } from 'react';
import './index.css';

class User extends Component {
	constructor(props) {
		super(props);
		this.state = {login : "",
					  nom : "",
					  prenom : "",
					  me : props.me}
		
	}

	send() {
		axios.get("http://localhost:8080/Projets_Janken/AddFriend",{params:{user_login:this.state.me,
																			friend_login:this.state.login}}).then(
																	response => this.response_user(response));
	}

	response_login(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
		}
	}

	render(){
		if (this.state.me != "false") {
			return (
			<div className="user">
			{this.state.login}
			{this.state.nom}
			{this.state.prenom}
		    </div>
		    <ListMessage login = {this.state.login} />);
		}
		else {
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

export default User;