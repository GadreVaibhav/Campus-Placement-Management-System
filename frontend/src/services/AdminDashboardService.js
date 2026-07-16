import api from "../api/api";

export const getDashboardStatistics = () => {
    return api.get("/admin/dashboard");
};