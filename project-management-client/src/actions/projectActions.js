import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
    try {
        const response = await axios.post("/api/projects", project)
        history.push("/dashboard")
        dispatch({
            type: GET_ERRORS,
            payload: {}
        })
    } catch (error) {
        dispatch({
            type: GET_ERRORS,
            payload: error.response.data
        })
    }
}

export const getProjects = () => async dispatch => {
    const response = await axios.get("/api/projects")
    dispatch ({
        type: GET_PROJECTS,
        payload: response.data
    })
}

export const getProject = (identifier, history) => async dispatch => {
    try {
        const response = await axios.get(`/api/projects/${identifier}`)
        dispatch ({
            type: GET_PROJECT,
            payload: response.data
        })
    } catch (error) {
        history.push("/dashboard");
    }
}

export const deleteProject = (identifier, history) => async dispatch => {
    if (window.confirm(
        "Are you sure? This will delete the Project and all the data related to it"
    )) {
        try {
            const response = await axios.delete(`/api/projects/${identifier}`)
            dispatch ({
                type: DELETE_PROJECT,
                payload: identifier
            })
        } catch (error) {
    
        }
    }
}