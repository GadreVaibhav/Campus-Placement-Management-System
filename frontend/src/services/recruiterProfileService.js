import axios from "axios";

const API = "http://localhost:8082/api/recruiter/profile";

const authHeader = () => ({
    headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
    }
});

export const getRecruiterProfile = async () => {
    const response = await axios.get(API, authHeader());
    return response.data;
};

export const updateRecruiterProfile = async (data) => {
    const response = await axios.put(API, data, authHeader());
    return response.data;
};