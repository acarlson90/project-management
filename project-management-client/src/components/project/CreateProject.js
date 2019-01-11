import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "../../actions/projectActions";
import classnames from "classnames";

class CreateProject extends Component {
  constructor() {
    super();

    this.state = {
      name: "",
      identifier: "",
      description: "",
      startDate: "",
      endDate: "",
      errors: {}
    };

    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  // Lifecycle hook
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors : nextProps.errors});
    }
  }

  onChange(event) {
    this.setState({ [event.target.name]: event.target.value });
  }
  onSubmit(event) {
    event.preventDefault();
    const newProject = {
      name: this.state.name,
      identifier: this.state.identifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate
    };

    console.log(newProject);

    this.props.createProject(newProject, this.props.history);
  }
  render() {
    const { errors } = this.state
    return (
      <div>
        {/*
         * Check name attribute input fields
         * Create constructor
         * Set state
         * Set value on input fields
         * Create onChange function
         * Set onChange on each input field
         * Bind on construtor
         * Check state change in the react extension
         */}
        <div className="project">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <h5 className="display-4 text-center">Create Project Form</h5>
                <hr />
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid" : errors.name
                      })}
                      placeholder="Name"
                      name="name"
                      value={this.state.name}
                      onChange={this.onChange}
                    />
                    {errors.name && (
                      <div className="invalid-feedback">{errors.name}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid" : errors.identifier
                      })}
                      placeholder="Identifier"
                      name="identifier"
                      value={this.state.identifier}
                      onChange={this.onChange}
                    />
                    {errors.identifier && (
                      <div className="invalid-feedback">{errors.identifier}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <textarea
                      className={classnames("form-control form-control lg", {
                        "is-invalid" : errors.description
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
    );
  }
}

CreateProject.propTypes = {
  createProject : PropTypes.func.isRequired,
  errors : PropTypes.object.isRequired
}

const mapStateToProps = state => ({
  errors: state.errors
})

export default connect(
  mapStateToProps,
  { createProject }
)(CreateProject);
