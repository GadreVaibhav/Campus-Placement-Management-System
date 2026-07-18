import api from "../api/api";

export const getAdminDashboard = () =>
    api.get("/dashboard/admin");

export const getStudentsByBranch = () =>
    api.get("/dashboard/admin/students-by-branch");

export const getRecentStudents = () =>
    api.get("/dashboard/recent-students");