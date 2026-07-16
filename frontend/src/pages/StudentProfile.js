import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Sidebar from "../components/layout/Sidebar";
import Navbar from "../components/layout/Navbar";
import { getStudent } from "../services/StudentService";

function StudentProfile() {

    const [student, setStudent] = useState(null);

    useEffect(() => {
        loadStudent();
    }, []);

    const loadStudent = async () => {
        try {
            const response = await getStudent(1);
            setStudent(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (

        <div className="d-flex">

            <Sidebar />

            <div className="flex-grow-1 bg-light">

                <Navbar />

                <div className="container py-4">

                    <div className="card shadow border-0">

                        <div className="card-header bg-primary text-white">

                            <h3 className="mb-0">
                                Student Profile
                            </h3>

                        </div>

                        <div className="card-body">

                            <div className="text-center mb-4">

                                <img
                                    src="https://ui-avatars.com/api/?name=Student&background=0D6EFD&color=fff&size=150"
                                    alt="Student"
                                    className="rounded-circle shadow"
                                />

                                <h3 className="mt-3">
                                    {student?.name}
                                </h3>

                                <p className="text-muted">
                                    {student?.email}
                                </p>

                            </div>

                            <div className="row">

                                <div className="col-md-6 mb-3">

                                    <strong>CGPA</strong>

                                    <p>{student?.cgpa}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Skills</strong>

                                    <p>{student?.skill}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Phone</strong>

                                    <p>{student?.phone || "Not Available"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Branch</strong>

                                    <p>{student?.branch || "Not Available"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Graduation Year</strong>

                                    <p>{student?.graduationYear || "Not Available"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Primary Language</strong>

                                    <p>{student?.primaryLanguage || "Not Available"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>10th Percentage</strong>

                                    <p>{student?.tenthPercentage || "Not Available"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>12th Percentage</strong>

                                    <p>{student?.twelfthPercentage || "Not Available"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Current Backlogs</strong>

                                    <p>{student?.currentBacklogs || "0"}</p>

                                </div>

                                <div className="col-md-6 mb-3">

                                    <strong>Total Backlogs</strong>

                                    <p>{student?.totalBacklogs || "0"}</p>

                                </div>

                            </div>
                    
                            <div className="text-end mt-4">

                                <Link
                                    to="/edit-profile"
                                    className="btn btn-primary"
                                >
                                    Edit Profile
                                </Link>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentProfile;