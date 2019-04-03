import React, { Component } from 'react';
import './index.css';

class SignUp extends Component {
	render() {
		return (<div>
			<h3 id="rouge">Cr&eacute;er un compte</h3>
			<form>
				<table>
					<tr>  
						<td><label for='nom'>Nom</label> </td>
						<td><input type="text" name="nom"/> </td>
					</tr>
					<tr>
					<td><label for='prenom'>Prénom</label></td>
					<td><input type="text" name="prenom"/></td>
					</tr>
					<tr>
						<td><label for='login'> Login </label></td>
						<td><input type="text" name="login"/></td>
					</tr>
					<tr>
						<td><label for='password'>Password</label> </td>
						<td><input type="text" name="password"/></td>
					</tr>
					<tr>
		     			<td colspan='2' align="right"/> <input type="button" class="btn btn-danger" value="Ikuzo! 行くぞ"/>
		    			</tr>
				</table>
			</form>	
		</div>)
	}
}

export default SignUp;
