import api from "../api/api";

export const getStudentDashboard = (studentId) => {

    return api.get(`/student-dashboard/${studentId}`);

};