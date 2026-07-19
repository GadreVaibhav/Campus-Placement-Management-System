import axios from "axios";

const API = "http://localhost:8082/api/recruiter/interview-management";

const getToken = () => localStorage.getItem("token");

export const getRecruiterInterviews = async () => {

    const response = await axios.get(API, {

        headers: {
            Authorization: `Bearer ${getToken()}`
        }

    });

    return response.data;
};

export const scheduleInterview = async (data) => {

    const response = await axios.post(API, data, {

        headers: {
            Authorization: `Bearer ${getToken()}`
        }

    });

    return response.data;
};

export const updateInterview = async (id, data) => {

    const response = await axios.put(

        `${API}/${id}`,

        data,

        {

            headers: {
                Authorization: `Bearer ${getToken()}`
            }

        });

    return response.data;
};

export const deleteInterview = async (id) => {

    const response = await axios.delete(

        `${API}/${id}`,

        {

            headers: {
                Authorization: `Bearer ${getToken()}`
            }

        });

    return response.data;
};
export const getRecruiterApplications = async () => {

    const response = await axios.get(

        "http://localhost:8082/api/recruiter/applications",

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;
};
export const getUpcomingInterviews = async () => {

    const response = await axios.get(

        "http://localhost:8082/api/recruiter/interviews/upcoming"

    );

    return response.data;

};