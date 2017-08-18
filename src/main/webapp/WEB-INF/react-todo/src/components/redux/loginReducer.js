import promise from 'es6-promise';
const LOGIN_PENDING='LOGIN_PENDING';
const LOGIN_SUCCESS='LOGIN_SUCCESS';
const LOGIN_ERROR='LOGIN_ERROR';
const LOGOUT='LOGOUT';
const user = null;
function setLoginPending(isLoginPending){
    return {
        type: LOGIN_PENDING,
        isLoginPending

    };
}

function setLoginSuccess(isLoginSuccess,user){
    return {
        type: LOGIN_SUCCESS,
        isLoginSuccess,
        user

    };
}

function setLoginError(loginError){
    return {
        type: LOGIN_ERROR,
        loginError

    };
}
    function setLogOut(islogout){
    return {
        type: LOGOUT,
        islogout

    };
}

export function login(email,password){
    return dispatch => {
        dispatch(setLoginPending(true));
        dispatch(setLoginSuccess(false));
        dispatch(setLoginError(null));
        
        
        sendLoginRequest(email,password).then(
            data => {
                dispatch(setLoginPending(false));
                dispatch(setLoginSuccess(true,data));
            }
        )
        .catch( err => {
            dispatch(setLoginPending(false));
            dispatch(setLoginError(err));
        });
    };
}
export function logout(){
    return dispatch => {
        dispatch(setLoginPending(false));
        dispatch(setLoginSuccess(false));
        dispatch(setLoginError(null));
    };
}


export default function loginReducer(state = {
    loginError:null,
    isLoginPending:false,
    isLoginSuccess:false,
    user : null,

},action){
    switch(action.type){
        case LOGIN_SUCCESS :
         return {
             state : action.state,
             isLoginSuccess : action.isLoginSuccess,
             user: action.user
         };
            case LOGIN_PENDING :
         return {
             state : action.state,
             isLoginPending : action.isLoginPending
         };
            case LOGIN_ERROR :
         return {
             state : action.state,
             loginError : action.loginError
         };
             case LOGOUT :
         return {
             state : action.state,
             islogout : action.islogout,
             isLoginPending:false,
             isLoginSuccess:false,
             loginError:null
         };
        default : return state;
    }
}

function sendLoginRequest(email,password) {
    
    return new Promise((resolve,reject) => {
        fetch(`http://localhost:8080/todoapp/login?username=`+email+`&password=`+password,{
            method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        credentials: 'include'
        }) 
            .then(function(response) {
    if (response.status >= 400) {
      return reject(new Error('Invalid Username/Password'));
    }
    return response.json();
  })
  .then(function(data) {
     return resolve(data);
  });
        
            
        
    })
}
