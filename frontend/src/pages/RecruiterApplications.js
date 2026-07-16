import { useEffect, useState } from "react";
import {
    getAllApplications,
    updateApplicationStatus
} from "../services/ApplicationService";
import { Link, useNavigate } from "react-router-dom";
import {
    interviewExists
} from "../services/InterviewService";
function RecruiterApplications() {

    const navigate = useNavigate();
    const [applications, setApplications] = useState([]);

    useEffect(() => {
        loadApplications();
    }, []);

   const loadApplications = async () => {

    try {

        const response = await getAllApplications();

        const updatedApplications = await Promise.all(

            response.data.map(async (application) => {

                const exists = await interviewExists(application.id);

                return {
                    ...application,
                    interviewScheduled: exists.data
                };

            })

        );

        setApplications(updatedApplications);

    } catch (error) {

        console.error(error);

    }

};
    const handleStatusUpdate = async (applicationId, status) => {

    try {

        await updateApplicationStatus(applicationId, status);

        alert("Application updated successfully!");

        loadApplications();

    } catch (error) {

        console.error(error);

        alert("Failed to update application.");

    }

};

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                Student Applications
            </h2>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>
                        <th>Student</th>
                        <th>Company</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Applied On</th>
                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        applications.map((application) => (

                            <tr key={application.id}>

                                <td>{application.id}</td>

                                <td>{application.studentName}</td>

                                <td>{application.companyName}</td>

                                <td>{application.jobRole}</td>

                                <td>{application.status}</td>

                               <td>{application.applicationDate}</td>

                                <td>

                                    <button
                                        className="btn btn-success btn-sm me-2"
                                        onClick={() =>
                                            handleStatusUpdate(application.id, "SHORTLISTED")
                                        }
                                    >
                                        Shortlist
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm me-2"
                                        onClick={() =>
                                            handleStatusUpdate(application.id, "REJECTED")
                                        }
                                     >
                                        Reject
                                    </button>
                                   <button
                                        className={
                                            application.interviewScheduled
                                                ? "btn btn-warning btn-sm"
                                                : "btn btn-primary btn-sm"
                                        }
                                        onClick={() =>
                                            navigate(`/recruiter/interview/${application.id}`)
                                        }
                                    >
                                        {
                                            application.interviewScheduled
                                                ? "Edit Interview"
                                                : "Schedule Interview"
                                        }
                                    </button>

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default RecruiterApplications;