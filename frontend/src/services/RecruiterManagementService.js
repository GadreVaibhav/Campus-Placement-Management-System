import api from "../api/api";

export const getAllRecruiters = () => {
    return api.get("/recruiters");
};

export const deleteRecruiter = (id) => {
    return api.delete(`/recruiters/${id}`);
};

export const updateRecruiter = (id, recruiter) => {
    return api.put(`/recruiters/${id}`, recruiter);
};

export const createRecruiter = (recruiter) => {
    return api.post("/recruiters", recruiter);
};