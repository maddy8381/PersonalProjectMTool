import React, { Component } from 'react';
import CreateProjectButton from './Project/CreateProjectButton';
import ProjectItem from './Project/ProjectItem';
import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types"

class Dashboard extends Component {

  componentDidMount(){
    this.props.getProjects();
  }

  render() {
    const {projects} = this.props.project
    return (
     <React.Fragment>
        <div className="projects">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Projects</h1>
                        <br />
                        <CreateProjectButton />
                        <br />
                        <hr />
                        {projects.map(project=>( //Passing One by one object from List as a prop to ProjectItem Component

                          <ProjectItem key={project.id} project={project} />
                        ))

                        }
                    </div>
                </div>
            </div>
        </div>
      </React.Fragment>
    );
  }
}

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired
}

const mapStateToProps = state => ({
  project: state.project,

})

//Connecting this component to store by using this
export default connect(mapStateToProps, {getProjects})(Dashboard);
