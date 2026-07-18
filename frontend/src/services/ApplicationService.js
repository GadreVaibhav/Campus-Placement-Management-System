import api from "../api/api";

/* ---------- STUDENT ---------- */

export const applyForJob = (jobId) =>
    api.post(`/applications/apply/${jobId}`);

export const getMyApplications = () =>
    api.get("/applications/my");

export const getMyRecentApplications = () =>
    api.get("/applications/my/recent");

/* ---------- ADMIN ---------- */

export const getAllApplications = () =>
    api.get("/applications");

export const getRecentApplications = () =>
    api.get("/applications/recent");

export const getApplicationById = (id) =>
    api.get(`/applications/${id}`);

export const updateApplicationStatus = (id, status) =>
    api.put(`/applications/${id}/status?status=${status}`);

export const deleteApplication = (id) =>
    api.delete(`/applications/${id}`);