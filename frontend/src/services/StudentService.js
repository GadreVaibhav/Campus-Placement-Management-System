import api from "../api/api";

// Logged-in Student

export const getLoggedInStudent = () => {
    return api.get("/students/me");
};

export const updateLoggedInStudent = (student) => {
    return api.put("/students/me", student);
};

// Admin

export const getStudentById = (id) => {
    return api.get(`/students/${id}`);
};

export const getStudent = getStudentById;
export const uploadResume = (formData) => {

    return api.post("/students/resume", formData, {

        headers: {

            "Content-Type": "multipart/form-data"

        }

    });

};

export const downloadResume = () => {

    return api.get("/students/resume", {

        responseType: "blob"

    });

};

export const deleteResume = () => {

    return api.delete("/students/resume");

};
export const getAvailableJobs = () => {
    return api.get("/jobs/available");
};
export const getJobById = (id) => {
    return api.get(`/jobs/${id}`);
};
// ==========================================
// Apply Job
// ==========================================

export const applyJob = (jobId) => {

    return api.post(`/applications/apply/${jobId}`);

};

// ==========================================
// My Applications
// ==========================================

export const getMyApplications = () => {

    return api.get("/applications/my");

};