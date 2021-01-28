import axios from "axios";

export const addProjectTask = (backlog_id, project_task, history) => async dispatch => {

    await axios.post(`/api/backlog/${backlog_id}`);
    history.push(`/projectBoard/${backlog_id}`);
};