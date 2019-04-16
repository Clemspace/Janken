import React, { Component } from 'react';
import './index.css';
import axios from 'axios';

import Friend from './Friend';

class Search extends Component {
	constructor(props) {
		super(props);
		this.state = {login : props.login,
									friend : "",
									query : ""}

    this.onChangeQuery = this.onChangeQuery.bind(this);
    this.onChangeFriend = this.onChangeFriend.bind(this);
	}

  send() {
    axios.get("http://localhost:8080/Projets_Janken/Search",{params:{login:this.state.login,
                                                                     friend:this.state.friend,
                                                                     query:this.state.query}}).then(
																								response => this.response_Search(response));
  }

	response_Search(response) {
		console.log(response.data.friend);
		if(response.data["status"] === "error") {
			this.setState({status : "error", desc : response.data["description"]});
		} else {
			this.setState({status :""});
			this.setState({listFriend:response.data.friend});
		}
	}

  onChangeLogin (event) {
	    this.setState({
	      login: event.target.value
	    });
  }

  onChangeFriend (event) {
	    this.setState({
	      friend: event.target.value
	    });
  }

	render() {
    <div>
    <div class="login-box">
        <form>
        <h1>Search</h1>
          <div class="textbox">
          <h5 class="erreur"> {this.state.status} {this.state.desc}</h5>
              <i class="fas fa-user"></i>
              <input type="text" placeholder="motscle" value={this.state.query} onChange={this.onChangeQuery}/>
          </div>

          <div class="textbox">
            <i class="fas fa-lock"></i>
            <input type="text" placeholder="Ami" value={this.state.friend} onChange={this.onChangeFriend}/>
          </div>

            <input type="button" onClick={(event) => this.send()} class="btn" value="Ikuzo! 行くぞ"/>
          </form>
          </div>
          </div>);
	}
}

export default Search;
