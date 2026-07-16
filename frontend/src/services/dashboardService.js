import axios from "axios";
import api from "../api/api";
const API = "http://localhost:8082/api/dashboard";

export const getAdminDashboard = () => {

    return axios.get(
        `${API}/admin`,
        {
            headers: {
                Authorization:
                    "Bearer " + localStorage.getItem("token")
            }
        }
    );

};
export const getRecentStudents = () => {

    return axios.get(

        `${API}/recent-students`,

        {

            headers: {

                Authorization:
                    "Bearer " + localStorage.getItem("token")

            }

        }

    );

};
export const getStudentsByBranch = () => {

    return api.get("/dashboard/admin/students-by-branch");

};
