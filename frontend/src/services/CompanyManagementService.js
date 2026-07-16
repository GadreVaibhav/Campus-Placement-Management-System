import api from "../api/api";

export const getAllCompanies = (
    page = 0,
    size = 5,
    sortBy = "companyName",
    direction = "asc"
) => {

    return api.get("/companies", {
        params: {
            page,
            size,
            sortBy,
            direction
        }
    });

};

export const searchCompanies = (
    companyName,
    industry,
    location,
    isActive,
    page = 0,
    size = 5
) => {

    return api.get("/companies/search", {

        params: {
            companyName,
            industry,
            location,
            isActive,
            page,
            size
        }

    });

};

export const updateCompany = (companyId, companyData) => {

    return api.put(`/companies/${companyId}`, companyData);

};

export const deleteCompany = (companyId) => {

    return api.delete(`/companies/${companyId}`);

};