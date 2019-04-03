import React, { Component } from 'react';
import './index.css';

class FormMessage extends Component {
	render(){
			return (
			<div className="formMessage">
				<form action="">
					<table id="formMessage">
						<tr> <textarea name="text" rows="5" cols="30"> Saisissez votre message </textarea> </tr>
						<tr> <input type="submit"/> </tr>
					</table>
				</form>
		     </div>);
		}
}

export default FormMessage;
