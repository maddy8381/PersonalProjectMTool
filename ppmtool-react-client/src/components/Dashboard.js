import React, { Component } from 'react';
import ProjectItem from './Project/ProjectItem';

class Dashboard extends Component {
  render() {
    return (
     <React.Fragment>
        <h1>Welcome to the Dashboard</h1>
        <ProjectItem />
      </React.Fragment>
    );
  }
}

export default Dashboard;
