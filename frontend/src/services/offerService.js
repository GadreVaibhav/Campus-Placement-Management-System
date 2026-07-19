import axios from "axios";

const API = "http://localhost:8082/api/offers";

const getToken = () => localStorage.getItem("token");

// Recruiter
export const getRecruiterOffers = async () => {

    const response = await axios.get(

        `${API}/recruiter`,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

export const createOffer = async (offer) => {

    const response = await axios.post(

        API,

        offer,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

export const updateOffer = async (id, offer) => {

    const response = await axios.put(

        `${API}/${id}`,

        offer,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

export const deleteOffer = async (id) => {

    const response = await axios.delete(

        `${API}/${id}`,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

// Student

export const getStudentOffers = async () => {

    const response = await axios.get(

        `${API}/student`,

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

export const acceptOffer = async (id) => {

    const response = await axios.put(

        `${API}/${id}/accept`,

        {},

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};

export const rejectOffer = async (id) => {

    const response = await axios.put(

        `${API}/${id}/reject`,

        {},

        {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        }

    );

    return response.data;

};