import React from 'react';
import { Button,Input,Icon } from 'semantic-ui-react'
import {connect} from 'react-redux';
import {login} from '../redux/loginReducer'
import LoginForm from '../loginform/loginForm.jsx'
class Register extends React.Component{

  constructor(props){
    super(props);
    this.state={
        username : '',
        password : '',
        firstname : '',
        lastname : '',
        registerError : null,
        registerfailed : false
    };
    this.onSubmit=this.onSubmit.bind(this);
    this.handleChangefirstname=this.handleChangefirstname.bind(this);
    this.handleChangelastname=this.handleChangelastname.bind(this);
    this.handleChangeusername=this.handleChangeusername.bind(this);
    this.handlePasswordChange =  this.handlePasswordChange.bind(this)
  }
handlePasswordChange(e) {
   this.setState({password: e.target.value});
}
handleChangeusername(e) {
   this.setState({username: e.target.value});
}
handleChangefirstname(e) {
   this.setState({firstname: e.target.value});
}
handleChangelastname(e) {
   this.setState({lastname: e.target.value});
}
    render() {
        let {registerfailed,registerError} = this.state
        return (
            
          <div>
            <form  id="contact" name="loginForm" onSubmit={this.onSubmit}>
        <div class="container">
          <div class="head">
      <h2>Register Page</h2>
    </div>
             <Input value={this.state.username} onChange={this.handleChangeusername} placeholder='UserName' />
             <br/>
            <Input type="password" value={this.state.password} onChange={this.handlePasswordChange} placeholder='password' />
             <br/>
            <Input value={this.state.firstname} onChange={this.handleChangefirstname} placeholder='First Name' />
             <br/>
           <Input value={this.state.lastname} onChange={this.handleChangelastname} placeholder='Last Name' />
             <br/>
        <button id="submit" type="submit">
      Register
    </button>
        </div>
        </form>
                  { registerfailed && <div>{registerError.message}</div> }

            </div>
        )
      }
    
onSubmit(e){
     e.preventDefault();
    registerUser(this.state).then(data =>{
      window.location.reload();
    }).catch(err => {
      this.setState({registerError : err, registerfailed : true})
    })
   }  

}

function registerUser(state){
  var user = {
    username : state.username,
    password : state.password,
    firstname : state.firstname,
    lastname : state.lastname
  }
return new Promise((resolve,reject) => { fetch(`http://localhost:8080/todoapp/createUser`,{
            method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body : JSON.stringify(user)
        })

            .then(function(response) {
    if (response.status >= 400) {
      return reject("failed to get data");
    }
    return response.json();
  }).then(function(data){
    return resolve(data);
  });

    })
}


export default Register;