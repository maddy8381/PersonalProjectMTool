import React, { Component } from 'react'
import PropTypes from "prop-types"
import {connect} from "react-redux"
import {createProject} from "../../actions/projectActions";
import classnames from "classnames";

export class AddProject extends Component {
    constructor(){
        super();

        this.state = {
            projectName: "",
            projectIdentifier: "",
            description: "",
            start_date: "",
            end_date: "",
            errors: {}
        }

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    //life cycle hook
    componentWillReceiveProps(nextProps){
        if(nextProps.errors){
            this.setState({errors:nextProps.errors});
        }
    }

    onChange(e){
        this.setState({[e.target.name]:e.target.value});
    }

    onSubmit(e){
        //Disabling application Refresh on Submit
        e.preventDefault();

        //Creating new Object
        const newProject = {
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            start_date: this.state.start_date,
            end_date: this.state.end_date
        }

        this.props.createProject(newProject, this.props.history);
    }

    render() {
         const {errors} = this.state; //Destructuring
         
        return (
            <div className="project">

                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create / Edit Project form</h5>
                            <hr />
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">                                    
                                    <input type="text" 
                                       className={classnames("form-control form-control-lg ",{
                                         "is-invalid": errors.projectName
                                        })} 
                                       placeholder="Project Name" name="projectName" value={this.state.projectName} onChange={this.onChange}
                                    />
                                    {errors.projectName && (
                                        <div classNames="invalid-feedback">{errors.projectName} </div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <input type="text" 
                                        className={classnames("form-control form-control-lg ",{ 
                                        "is-invalid": errors.projectIdentifier //main thing - if any error then make it red box
                                       })} 
                                       placeholder="Unique Project ID" name="projectIdentifier" value={this.state.projectIdentifier} onChange={this.onChange} 
                                    />
                                    {errors.projectIdentifier && ( //To show msg if any
                                        <div classNames="invalid-feedback">{errors.projectIdentifier} </div>
                                    )}
                                </div>
                                <div className="form-group">
                                    <textarea 
                                        className={classnames("form-control form-control-lg ",{
                                        "is-invalid": errors.description
                                       })} 
                                        placeholder="Project Description" name="description"  value={this.state.description} onChange={this.onChange}
                                    />
                                    {errors.description && (
                                        <div classNames="invalid-feedback">{errors.description} </div>
                                    )}
                                </div>
                                <h6>Start Date</h6>
                                <div className="form-group">
                                    <input type="date" className="form-control form-control-lg" name="start_date" value={this.state.start_date} onChange={this.onChange} />
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group">
                                    <input type="date" className="form-control form-control-lg" name="end_date" value={this.state.end_date} onChange={this.onChange} />
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

AddProject.propTypes = {
    createProject : PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
})

export default connect(mapStateToProps, {createProject}) (AddProject);
