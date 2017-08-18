import React from 'react';
import {connect} from 'react-redux';
import {login} from '../redux/loginReducer'
import './loginForm.css'
import ToDoHome from '../ToDo/toDoHome.jsx'
import Register from '../register/register.jsx'
class loginForm extends React.Component{

  constructor(props){
    super(props);
    this.state={
      registerUser : false
    };
    this.onSubmit=this.onSubmit.bind(this);
    this.register = this.register.bind(this);
  }

    render() {
      let {email,password} = this.state;
      let {isLoginPending,isLoginSuccess,loginError} = this.props;
      if(this.state.registerUser){
        return (
          <div>
            <Register/>
            </div>
        )
      }
      if(isLoginSuccess){
        return ( <div><ToDoHome props={this.props}/></div>)
      }
      return ( 
           <div>    <form  id="contact" name="loginForm" onSubmit={this.onSubmit}>
        <div class="container">
          <div class="head">
      <h2>Welcome!! to TO-DO Tasks</h2>
    </div>
            <input type="text" name="email" placeholder="User Name" onChange={e => this.setState({email: e.target.value})} value={email}/>
            <br/>
            <input type="password" placeholder="Password" name="Password" onChange={e => this.setState({password: e.target.value})} value={password}/>
            <br/>
        <button id="submit" type="submit">
      login
    </button>

        <div className="head">
          { isLoginPending && <div>Please wait...</div> }
          { loginError && <div>{loginError.message}</div> }
        </div>
        </div>
      </form>
      <form onSubmit={this.register}>
      <button id="register" type="submit">
      Register Now!
    </button>
    </form>
    </div>
      );
   }

onSubmit(e){
     e.preventDefault();
     let {email,password} = this.state;
     this.props.login(email,password);

   }  
register(e){
     e.preventDefault();
     this.setState({registerUser : true});
   }  
  
}


const mapStateToProps = (state) =>{
  return {
    isLoginPending : state.isLoginPending,
    isLoginSuccess : state.isLoginSuccess,
    loginError : state.loginError,
    user : state.user
  };
}

const mapDispatchToProps = (dispatch) =>{
  return {
    login : (email,password) => dispatch(login(email,password))
  };
}


export default connect(mapStateToProps,mapDispatchToProps)(loginForm);