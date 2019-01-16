import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
    try {
        const response = await axios.post("http://localhost:8080/api/projects", project)
        history.push("/dashboard")
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
    }
}

export const getProjects = () => async dispatch => {
    const response = await axios.get("http://localhost:8080/api/projects")
    dispatch ({
        type: GET_PROJECTS,
        payload: response.data
    })
}

export const getProject = (identifier, history) => async dispatch => {
    const response = await axios.get(`http://localhost:8080/api/projects/${identifier}`)
    dispatch ({
        type: GET_PROJECT,
        payload: response.data
    })
}