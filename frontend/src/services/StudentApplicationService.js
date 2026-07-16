import api from "../api/api";

export const getApplicationsByStudent = (studentId) => {
    return api.get(`/applications/student/${studentId}`);
};