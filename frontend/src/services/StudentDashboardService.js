import api from "../api/api";

export const getDashboard = () => {
    return api.get("/student-dashboard");
};

export const getRecentApplications = () => {
    return api.get("/applications/my/recent");
};