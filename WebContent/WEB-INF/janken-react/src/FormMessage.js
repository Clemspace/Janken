import React, { Component } from 'react';
import axios from 'axios';
import './index.css';

class FormMessage extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
					text : ""}
		
	}

	render(){
			return (
			<div className="formMessage">
				<form action="">
					<table id="formMessage">
						<tr> <textarea name="text" rows="5" cols="30" value="{this.state.text} onChange={this.onChangeText}"> Saisissez votre message </textarea> </tr>
						<tr> <input type="button" onClick={(event) => this.send()}/> </tr>
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
			axios.get("http://localhost:8080/AddComment/",{params:{login:this.state.login,
																				text:this.state.text}}).then(
																		response => this.response_message(response));
	}

	response_message(response) {
		console.log(response.data);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
		}
	}

}

export default FormMessage;