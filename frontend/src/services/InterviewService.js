import api from "../api/api";

export const scheduleInterview = (applicationId, data) => {
    return api.post(`/interviews/${applicationId}`, data);
};

export const getInterview = (applicationId) => {
    return api.get(`/interviews/application/${applicationId}`);
};

export const interviewExists = (applicationId) => {
    return api.get(`/interviews/exists/${applicationId}`);
};

export const getAllInterviews = () => {
    return api.get("/interviews");
};

export const updateInterview = (interviewId, data) => {
    return api.put(`/interviews/${interviewId}`, data);
};

export const deleteInterview = (interviewId) => {
    return api.delete(`/interviews/${interviewId}`);
};