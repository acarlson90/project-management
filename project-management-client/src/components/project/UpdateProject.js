import React, { Component } from 'react'
import { getProject, createProject } from "../../actions/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";

class UpdateProject extends Component {

  constructor() {
    super()

    this.state = {
      id: "",
	    name: "",
	    identifier: "",
	    description: "",
	    startDate: "",
      endDate: "",
      errors: {}
    }

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({errors: nextProps.errors});
    }
    const {
      id,
	    name,
	    identifier,
	    description,
	    startDate,
	    endDate
    } = nextProps.project

    this.setState({
      id,
	    name,
	    identifier,
	    description,
	    startDate,
	    endDate
    })
  }

  componentDidMount() {
    const { identifier } = this.props.match.params;
    this.props.getProject(identifier, this.props.history);
  }

  onChange(event) {
    this.setState({[event.target.name]:event.target.value});
  }

  onSubmit(event) {
    event.preventDefault();
    const updateProject = {
      id: this.state.id,
	    name: this.state.name,
	    identifier: this.state.identifier,
	    description: this.state.description,
	    startDate: this.state.startDate,
	    endDate: this.state.endDate
    }

    console.log(updateProject);

    this.props.createProject(updateProject, this.props.history);
  }

  render() {
    const { errors } = this.state;
    return (
      <div>
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Update Project Form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.name
                      })}
                      placeholder="Name"
                      name="name"
                      value={this.state.name}
                      onChange={this.onChange}
                    />
                    {
                      errors.name && (
                        <div className="invalid-feedback">{errors.name}</div> 
                      )
                    }
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className="form-control form-control-lg"
                      placeholder="Identifier"
                      name="identifier"
                      value={this.state.identifier}
                      disabled
                    />
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.name
                      })}
                      placeholder="Description"
                      name="description"
                      value={this.state.description}
                      onChange={this.onChange}
                    />
                    {errors.description && (
                      <div className="invalid-feedback">{errors.description}</div>
                    )}
                  </div>
                  <h6>Start Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="startDate"
                      value={this.state.startDate}
                      onChange={this.onChange}
                    />
                  </div>
                  <h6>Estimated End Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="endDate"
                      value={this.state.endDate}
                      onChange={this.onChange}
                    />
                  </div>
                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    )
  }
}

UpdateProject.propTypes = {
  getProject: PropTypes.func.isRequired,
  createProject: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  project: state.project.project,
  errors: state.errors
})

export default connect(
  mapStateToProps, 
  { getProject, createProject }
)(UpdateProject);