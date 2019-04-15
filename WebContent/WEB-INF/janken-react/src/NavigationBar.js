import React, { Component } from 'react';
import axios from 'axios';
import './index.css';
import Logout from './Logout';


class NavigationBar extends Component{
  constructor(props){
    super(props);
    this.state = {login : props.login}
  }
  render(){
    return(
    <nav>
      <a class="active" href="javascript:void(0)">Janken</a>
      <a onClick="javascript:void(0)">Profile</a>
      <a onClick="Logout()">Logout</a>
  </nav>
    )
  }

}

export default NavigationBar
