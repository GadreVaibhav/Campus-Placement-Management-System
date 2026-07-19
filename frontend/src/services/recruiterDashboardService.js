import axios from "axios";

const API = "http://localhost:8082/api/recruiter/dashboard";

export const getRecruiterDashboard = async () => {
    const response = await axios.get(API);
    return response.data;
};