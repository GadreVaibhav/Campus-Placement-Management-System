import api from "../api/api";

// Dashboard
export const getRecentApplications = async () => {
    const response = await api.get("/recruiter/applications/recent");
    return response.data;
};

// Applications Page
export const getRecruiterApplications = async () => {
    const response = await api.get("/recruiter/applications");
    return response.data;
};