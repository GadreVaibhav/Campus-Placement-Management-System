import { useEffect, useState } from "react";
import {
    getLoggedInStudent,
    updateLoggedInStudent
} from "../services/StudentService";

import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

function EditProfile() {

    const navigate = useNavigate();

    const [student, setStudent] = useState({

        name: "",
        email: "",
        phone: "",
        branch: "",
        graduationYear: "",
        primaryLanguage: "",
        cgpa: "",
        skill: "",
        tenthPercentage: "",
        twelfthPercentage: "",
        currentBacklogs: "",
        totalBacklogs: ""

    });

    const [loading, setLoading] = useState(false);

    useEffect(() => {

        loadStudent();

    }, []);

    const loadStudent = async () => {

        try {

            const response = await getLoggedInStudent();

            setStudent(response.data);

        }

        catch (error) {

            console.log(error);

            toast.error("Unable to load profile.");

        }

    };

    const handleChange = (e) => {

        setStudent({

            ...student,

            [e.target.name]: e.target.value

        });

    };

    const saveProfile = async (e) => {

        e.preventDefault();

        setLoading(true);

        const request = {

            name: student.name,
            email: student.email,
            phone: student.phone,
            branch: student.branch,
            graduationYear: student.graduationYear,
            primaryLanguage: student.primaryLanguage,
            cgpa: student.cgpa,
            skill: student.skill,
            tenthPercentage: student.tenthPercentage,
            twelfthPercentage: student.twelfthPercentage,
            currentBacklogs: student.currentBacklogs,
            totalBacklogs: student.totalBacklogs

        };

        try {

            await updateLoggedInStudent(request);

            toast.success("Profile updated successfully.");

            setTimeout(() => {

                navigate("/student");

            }, 1200);

        }

        catch (error) {

            console.log(error);

            if (error.response?.data) {

                if (typeof error.response.data === "object") {

                    Object.values(error.response.data).forEach((message) => {

                        toast.error(message);

                    });

                }

                else {

                    toast.error(error.response.data);

                }

            }

            else {

                toast.error("Something went wrong. Please try again.");

            }

        }

        finally {

            setLoading(false);

        }

    };

    return (

        <div className="container mt-5">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>Edit Profile</h3>

                </div>

                <div className="card-body">

                    <form onSubmit={saveProfile}>

                        <div className="row">

                            <div className="col-md-6 mb-3">

                                <label>Name</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="name"
                                    value={student.name || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Email</label>

                                <input
                                    type="email"
                                    className="form-control"
                                    name="email"
                                    value={student.email || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Phone</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="phone"
                                    value={student.phone || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Branch</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="branch"
                                    value={student.branch || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Graduation Year</label>

                                <input
                                    type="number"
                                    className="form-control"
                                    name="graduationYear"
                                    value={student.graduationYear || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Primary Language</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="primaryLanguage"
                                    value={student.primaryLanguage || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>CGPA</label>

                                <input
                                    type="number"
                                    step="0.01"
                                    className="form-control"
                                    name="cgpa"
                                    value={student.cgpa || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Skills</label>

                                <input
                                    type="text"
                                    className="form-control"
                                    name="skill"
                                    value={student.skill || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>10th Percentage</label>

                                <input
                                    type="number"
                                    className="form-control"
                                    name="tenthPercentage"
                                    value={student.tenthPercentage || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>12th Percentage</label>

                                <input
                                    type="number"
                                    className="form-control"
                                    name="twelfthPercentage"
                                    value={student.twelfthPercentage || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Current Backlogs</label>

                                <input
                                    type="number"
                                    className="form-control"
                                    name="currentBacklogs"
                                    value={student.currentBacklogs || ""}
                                    onChange={handleChange}
                                />

                            </div>

                            <div className="col-md-6 mb-3">

                                <label>Total Backlogs</label>

                                <input
                                    type="number"
                                    className="form-control"
                                    name="totalBacklogs"
                                    value={student.totalBacklogs || ""}
                                    onChange={handleChange}
                                />

                            </div>

                        </div>

                        <button
                            type="submit"
                            className="btn btn-success mt-3"
                            disabled={loading}
                        >

                            {loading ? "Saving..." : "Save Changes"}

                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default EditProfile;