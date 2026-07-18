import { useEffect, useState } from "react";
import {
    getDashboard,
    getRecentApplications
} from "../services/StudentDashboardService";
import { useNavigate } from "react-router-dom";

function StudentDashboard() {

    const [dashboard, setDashboard] = useState({});


    const navigate = useNavigate();

    const [recentApplications, setRecentApplications] = useState([]);

    

    useEffect(() => {
    loadDashboard();
}, []);

const loadDashboard = async () => {

    try {

        const [dashboardResponse, recentResponse] = await Promise.all([
            getDashboard(),
            getRecentApplications()
        ]);

        setDashboard(dashboardResponse.data);
        setRecentApplications(recentResponse.data);

    } catch (error) {

        console.error(error);

    }

};

   

    return (

        
         <div
                    className="container-fluid py-4"
                    style={{
                        background: "#f4f7fb",
                        minHeight: "100vh"
                    }}
                className="container-fluid">

           <div className="mb-4">
                <h2 className="fw-bold mb-1"  style={{ color: "#1f2937" }}>
                    Welcome Back 👋
                </h2>

                <p className="text-muted mb-0">
                    Here's what's happening with your placements today.
                </p>
            </div>

            <div className="row g-4 mb-4">

    {/* Applied */}

    <div className="col-lg-3 col-md-6">

        <div
            className="card border-0 shadow-sm"
            style={{
                borderRadius: "18px"
            }}
        >

            <div className="card-body">

                <small className="text-muted">
                    Applied Drives
                </small>

                <h2
                    className="fw-bold mt-2"
                    style={{ color: "#2563eb" }}
                >
                    {dashboard.appliedDrives}
                </h2>

            </div>

        </div>

    </div>

    {/* Selected */}

    <div className="col-lg-3 col-md-6">

        <div
            className="card border-0 shadow-sm"
            style={{
                borderRadius: "18px"
            }}
        >

            <div className="card-body">

                <small className="text-muted">
                    Selected
                </small>

                <h2
                    className="fw-bold mt-2"
                    style={{ color: "#16a34a" }}
                >
                    {dashboard.selectedDrives}
                </h2>

            </div>

        </div>

                </div>

                {/* Upcoming */}

                <div className="col-lg-3 col-md-6">

                    <div
                        className="card border-0 shadow-sm"
                        style={{
                            borderRadius: "18px"
                        }}
                    >

                        <div className="card-body">

                            <small className="text-muted">
                                Upcoming Drives
                            </small>

                            <h2
                                className="fw-bold mt-2"
                                style={{ color: "#f59e0b" }}
                            >
                                {dashboard.upcomingDrives}
                            </h2>

                        </div>

                    </div>

                </div>

                {/* Interviews */}

                <div className="col-lg-3 col-md-6">

                    <div
                        className="card border-0 shadow-sm"
                        style={{
                            borderRadius: "18px"
                        }}
                    >

                        <div className="card-body">

                            <small className="text-muted">
                                Interviews
                            </small>

                            <h2
                                className="fw-bold mt-2"
                                style={{ color: "#7c3aed" }}
                            >
                                {dashboard.interviews}
                            </h2>

                        </div>

                    </div>

                </div>

             </div>
                    <div className="card border-0 shadow-sm">

    <div className="card-body">

        <div className="d-flex justify-content-between align-items-center mb-3">

            <h5 className="fw-bold mb-0">
                Recent Applications
            </h5>

            <button
                className="btn btn-outline-primary btn-sm"
                onClick={() => navigate("/student/my-applications")}
            >
                View All
            </button>

        </div>

        <table className="table align-middle">

            <thead className="table-light">

                <tr>

                    <th>Company</th>

                    <th>Job Role</th>

                    <th>Package</th>

                    <th>Status</th>

                    <th>Applied</th>

                </tr>

            </thead>

            <tbody>

                {

                    recentApplications.length === 0 ?

                        (

                            <tr>

                                <td
                                    colSpan="5"
                                    className="text-center text-muted"
                                >

                                    No applications yet.

                                </td>

                            </tr>

                        )

                        :

                        recentApplications.map((application) => (

                            <tr key={application.id}>

                                <td className="fw-semibold">

                                    {application.companyName}

                                </td>

                                <td>

                                    {application.jobTitle}

                                </td>

                                <td>

                                    ₹ {application.packageLpa} LPA

                                </td>

                                <td>

                                    <span
                                        className={`badge ${
                                            application.status === "SELECTED"
                                                ? "bg-success"
                                                : application.status === "REJECTED"
                                                ? "bg-danger"
                                                : application.status === "SHORTLISTED"
                                                ? "bg-warning text-dark"
                                                : application.status === "INTERVIEW_SCHEDULED"
                                                ? "bg-info text-dark"
                                                : "bg-secondary"
                                        }`}
                                    >

                                        {application.status}

                                    </span>

                                </td>

                                <td>

                                    {

                                        application.appliedAt

                                            ?

                                            new Date(
                                                application.appliedAt
                                            ).toLocaleDateString()

                                            :

                                            "-"

                                    }

                                </td>

                            </tr>

                        ))

                }

            </tbody>

        </table>

    </div>

</div>

        </div>

    );

}

export default StudentDashboard;