import React from 'react';
import ReactDOM from 'react-dom';
import {provider} from 'react-redux';
import LoginForm from './src/components/loginform/loginForm.jsx';
import {Provider} from 'react-redux'
import store from './src/components/redux/loginStore'
ReactDOM.render(
<Provider store={store}>
<LoginForm />
</Provider>, document.getElementById('app'));