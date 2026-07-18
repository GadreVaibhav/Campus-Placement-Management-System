import api from "../api/api";

export const getMyInterviews = () => {
    return api.get("/interviews/my");
};