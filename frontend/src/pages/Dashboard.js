import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getLoggedInStudent } from "../services/StudentService";
import LatestPlacementDrives from "../components/dashboard/LatestPlacementDrives";
import {
    FaUserGraduate,
    FaEnvelope,
    FaPhone,
    FaGraduationCap,
    FaCode,
    FaCheckCircle,
    FaTimesCircle,
    FaFilePdf
} from "react-icons/fa";

function Dashboard() {

    const [student, setStudent] = useState(null);

    useEffect(() => {
        loadStudent();
    }, []);

    const loadStudent = async () => {
        try {
            const response = await getLoggedInStudent();;
            setStudent(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    if (!student) {
        return (
            <div className="container mt-5 text-center">
                <div className="spinner-border text-primary"></div>
                <h5 className="mt-3">Loading Dashboard...</h5>
            </div>
        );
    }

    return (

        <div className="container py-4">

            <h2 className="fw-bold mb-4">
                🎓 Student Dashboard
            </h2>

            <div className="row g-4">

                {/* Profile */}

                <div className="col-lg-6">

                    <div className="card shadow border-0 h-100">

                        <div className="card-header bg-primary text-white">
                            <h5 className="mb-0">
                                <FaUserGraduate /> Profile
                            </h5>
                        </div>

                        <div className="card-body">

                            <p><strong>Name:</strong> {student.name}</p>

                            <p>
                                <FaEnvelope className="me-2 text-primary"/>
                                {student.email}
                            </p>

                            <p>
                                <FaPhone className="me-2 text-success"/>
                                {student.phone || "N/A"}
                            </p>

                            <p>
                                <FaGraduationCap className="me-2 text-warning"/>
                                {student.branch || "N/A"}
                            </p>

                            <p>
                                Graduation Year :
                                {" "}
                                {student.graduationYear || "N/A"}
                            </p>

                            <p>
                                Primary Language :
                                {" "}
                                {student.primaryLanguage || "N/A"}
                            </p>

                        </div>

                    </div>

                </div>

                {/* Academics */}

                <div className="col-lg-6">

                    <div className="card shadow border-0 h-100">

                        <div className="card-header bg-success text-white">

                            <h5 className="mb-0">
                                📊 Academic Details
                            </h5>

                        </div>

                        <div className="card-body">

                            <h3 className="text-success">
                                {student.cgpa}
                            </h3>

                            <p>CGPA</p>

                            <hr/>

                            <p>10th : {student.tenthPercentage || "N/A"}%</p>

                            <p>12th : {student.twelfthPercentage || "N/A"}%</p>

                            <p>Current Backlogs : {student.currentBacklogs || 0}</p>

                            <p>Total Backlogs : {student.totalBacklogs || 0}</p>

                        </div>

                    </div>

                </div>

                {/* Placement */}

                <div className="col-lg-6">

                    <div className="card shadow border-0">

                        <div className="card-header bg-warning">

                            <h5 className="mb-0">
                                💼 Placement
                            </h5>

                        </div>

                        <div className="card-body">

                            <p>

                                <FaCode className="text-primary me-2"/>

                                {student.skill}

                            </p>

                            <hr/>

                            <h5>

                                Status :

                                {" "}

                                {

                                    student.isPlaced ?

                                        <span className="text-success">

                                            <FaCheckCircle/> Placed

                                        </span>

                                        :

                                        <span className="text-danger">

                                            <FaTimesCircle/> Not Placed

                                        </span>

                                }

                            </h5>

                        </div>

                    </div>

                </div>

                {/* Resume */}

                <div className="col-lg-6">

                    <div className="card shadow border-0">

                        <div className="card-header bg-info text-white">

                            <h5 className="mb-0">

                                <FaFilePdf/>

                                {" "}Resume

                            </h5>

                        </div>

                        <div className="card-body">

                            {

                                student.resumeUrl ?

                                    <a
                                        href={student.resumeUrl}
                                        target="_blank"
                                        rel="noreferrer"
                                        className="btn btn-success"
                                    >

                                        View Resume

                                    </a>

                                    :

                                    <button className="btn btn-outline-primary">

                                        Upload Resume

                                    </button>

                            }

                        </div>

                    </div>

                </div>

            </div>
        <LatestPlacementDrives />
            <div className="d-flex justify-content-end gap-2 mt-4">

    <Link
        to="/interview"
        className="btn btn-success"
    >
        Interview Schedule
    </Link>

    <Link
        to="/edit-profile"
        className="btn btn-primary"
    >
        Edit Profile
    </Link>

</div>

        </div>

    );

}

export default Dashboard;