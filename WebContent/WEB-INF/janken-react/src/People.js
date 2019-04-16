import React, { Component } from 'react';
import './index.css';

class People extends Component {
	constructor(props) {
		super(props);
		this.state = {me : props.me
                  login : props.login,
									setFriend : props.setFriend}

	}

  send() {
    axios.get("http://localhost:8080/Projets_Janken/AddFriend",{params:{user_login:this.state.me,
                                                                        friend_login:this.state.login}}).then(
                                  response => this.response_AddFriend(response));
  }

  response_AddFriend(response) {
    console.log(response.data);
    if(response.data["status"] === "error") {
      this.setState({status : "error", desc : response.data["description"]});
    } else {
      this.setState({status :""});
    }
  }

	render(){
			return (
			<div className="friend">
			{this.state.login}
      <button type="button" class="btn" onClick = {(event) => this.addFriend(this.state.login)> Ajouter en ami </button>
		  </div>);
		}
}

export default Friend;
