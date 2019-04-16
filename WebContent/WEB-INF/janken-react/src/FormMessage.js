import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

class FormMessage extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
									text : "",
									newMessage : props.newMessage}
		this.onChangeText = this.onChangeText.bind(this);
	}

	render(){
			return (
			<div className="formMessage">
				<form action="">
					<table id="formMessage">
						<tr> <textarea class="text" name="text" rows="5" cols="30" value={this.state.text} placeholder="Qu'est-ce qui vous passe par la tête?" onChange={this.onChangeText}>  </textarea> </tr>
						<tr> <input class="btn" type="button" onClick={(event) => this.send()} value="Envoyer"/> </tr>
					</table>
				</form>
		     </div>);
	}

	onChangeText (event) {
	    this.setState({
	      text: event.target.value
	    });
	}

	send() {
			axios.get("http://localhost:8080/Projets_Janken/AddComment",{params:{login:this.state.login,
																				text:this.state.text}}).then(
																		response => this.response_message(response));
	}

	response_message(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.state.newMessage(this.state.login);
		}
	}

}

export default FormMessage;
