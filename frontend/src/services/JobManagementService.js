import api from "../api/api";

export const getAllJobs = (page = 0) => {

    return api.get("/jobs", {
        params: {
            page,
            size: 5,
            sortBy: "id",
            direction: "asc"
        }
    });

};

export const searchJobs = (
    jobTitle,
    location,
    status,
    companyId,
    recruiterId,
    eligibilityCgpa
) => {

    return api.get("/jobs/search", {

        params: {
            jobTitle,
            location,
            status,
            companyId,
            recruiterId,
            eligibilityCgpa,
            page: 0,
            size: 100,
            sortBy: "id",
            direction: "asc"
        }

    });

};

export const updateJob = (id, data) => {

    return api.put(`/jobs/${id}`, data);

};

export const deleteJob = (id) => {

    return api.delete(`/jobs/${id}`);

};
export const createJob = (data) => {

    return api.post("/jobs", data);

};