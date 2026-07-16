import api from "../api/api";

export const getLoggedInStudent = () => {
    return api.get("/students/me");
};

export const getStudentById = (id) => {
    return api.get(`/students/${id}`);
};

// Backward compatibility
export const getStudent = getStudentById;

export const updateStudent = (userId, student) => {
    return api.put(`/students/profile/${userId}`, student);
};