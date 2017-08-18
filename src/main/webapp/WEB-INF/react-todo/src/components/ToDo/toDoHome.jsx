import React from 'react';
import { Button,Input,Icon } from 'semantic-ui-react'
import {connect} from 'react-redux';
import {logout} from '../redux/loginReducer'

class ToDoHome extends React.Component {
constructor(props){
    super(props);
    this.state={
        user : props.user,
        tasks:[],
        value:'',
        taskOnEdit:null
    };
    this.logout=this.logout.bind(this);
    this.addTask = this.addTask.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.deleteTask = this.deleteTask.bind(this);
    this.editTask = this.editTask.bind(this);
  }

componentWillMount(){
this.getTaskforUser(this.props.props.user.user);
}
editTask(e){
  var array = this.state.tasks;
  var index = -1;
  for(var i=0;i<array.length;i++){
      if(array[i].id == e.target.value){
          index = i;
          break;
      }
  }
  var task = array[index];
  array.splice(index, 1);
 
  this.setState({tasks: array ,value:task.toDoTask,taskOnEdit:task});
}

  addTask(e){
      if(this.state.value===''){
          return
      }
        var task = {
            status : 'NEW',
            toDoTask:this.state.value,
            user : this.props.props.user.user
        }
      if(this.state.taskOnEdit!=null){
          this.state.taskOnEdit.user = this.props.props.user.user;
          this.state.taskOnEdit.toDoTask = this.state.value;
          task = this.state.taskOnEdit;
          
      }
      this.setState({value : ''})
       addUpdateTaskForUser(task).then(
            data => {
                this.state.tasks.push(data);
                var tsk =this.state.tasks;
               this.setState({tasks : tsk, taskOnEdit : null})
            }
        )
  }
    handleChange(event) {
    this.setState({value: event.target.value});
  }
getTaskforUser(user){
    getTasksFromRest(user).then(
            data => {
                this.state.tasks.push(data);
               this.setState({tasks : data})
            }
        )
}
    logout(e){
        e.preventDefault();
        this.props.logout();
    }


    deleteTask(e){
    var array = this.state.tasks;
  var index = -1;
  for(var i=0;i<array.length;i++){
      if(array[i].id == e.target.value){
          index = i;
          break;
      }
  }
  var task = array[index];
  array.splice(index, 1);
deleteTaskForUser(task).then(
            data => {
                    this.setState({tasks: array });
            })
    }
   render() {
       let task = this.state.tasks.map((tsk) => 
    <div>
    <li>{tsk.toDoTask}</li>
    <button onClick={this.deleteTask} value={tsk.id} >delete</button>
    <button onClick={this.editTask} value={tsk.id} >Edit</button>
</div>);
      return (
         <div>
           
            <Input value={this.state.value} onChange={this.handleChange} placeholder='Add your tasks...' />
            <Button icon onClick={this.addTask}>add</Button>
            <ul>{task}</ul>
         </div>
      );
   }

}
function getTasksFromRest(user){
    const hash = new Buffer(('test1:test1').toString('base64'))
return new Promise((resolve,reject) => { fetch(`http://localhost:8080/todoapp/getToDoList`,{
            method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            
        },
        credentials: 'include',
        body : JSON.stringify(user)
        })

            .then(function(response) {
    if (response.status >= 400) {
      return reject("failed to get data");
    }
    return response.json();
  }).then(function(data){
    var arr = [];
    for(var i=0;i<data.length;i++){
        arr[i]=data[i].toDoTask;
    }
    return resolve(data);
  });

    })
}

function addUpdateTaskForUser(task){
return new Promise((resolve,reject) => { fetch(`http://localhost:8080/todoapp/addTask`,{
            method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body : JSON.stringify(task)
        })

            .then(function(response) {
    if (response.status >= 400) {
      return reject("failed to get data");
    }
    return response.json();
  }).then(function(data){
    var arr = [];
    for(var i=0;i<data.length;i++){
        arr[i]=data[i].toDoTask;
    }
    return resolve(data);
  });

    })
}

function deleteTaskForUser(task){
return new Promise((resolve,reject) => { fetch(`http://localhost:8080/todoapp/deleteTask`,{
            method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body : JSON.stringify(task)
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
export default (ToDoHome);

