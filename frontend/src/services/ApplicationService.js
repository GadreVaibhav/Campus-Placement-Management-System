import api from "../api/api";

export const applyForDrive = (studentId, placementDriveId) => {

    return api.post("/applications/apply", {

        studentId,

        placementDriveId

    });

};

export const getAllApplications = () => {
    return api.get("/applications");
};

export const updateApplicationStatus = (applicationId, status) => {
    return api.put(
        `/applications/${applicationId}/status?status=${status}`
    );
};

export const getRecentApplications = () => {
    return api.get("/applications/recent");
};

export const getApplicationById = (applicationId) => {

    return api.get(`/applications/${applicationId}`);

};

export const deleteApplication = (applicationId) => {

    return api.delete(`/applications/${applicationId}`);

};