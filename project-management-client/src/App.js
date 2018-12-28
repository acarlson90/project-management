import React, { Component } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Header from "./components/layout/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import CreateProject from "./components/project/CreateProject";

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/dashboard" component = { Dashboard }/>
          <Route exact path="/createProject" component = { CreateProject }/>
        </div>
      </Router>
    );
  }
}

export default App;