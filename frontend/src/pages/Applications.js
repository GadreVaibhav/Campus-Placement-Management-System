import { useEffect, useState } from "react";
import { getLoggedInStudent } from "../services/StudentService";
import { getApplicationsByStudent } from "../services/StudentApplicationService";

function Applications() {

    const [applications, setApplications] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadApplications();
    }, []);

    const loadApplications = async () => {

        try {

            // Get logged-in student
            const studentResponse = await getLoggedInStudent();

            const studentId = studentResponse.data.studentId;

            // Get student's applications
            const applicationResponse =
                await getApplicationsByStudent(studentId);

            setApplications(applicationResponse.data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (
            <div className="container mt-5 text-center">
                <div className="spinner-border text-primary"></div>
                <h5 className="mt-3">Loading Applications...</h5>
            </div>
        );

    }

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                My Applications
            </h2>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>Company</th>

                        <th>Job Role</th>

                        <th>Status</th>

                        <th>Applied On</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        applications.length === 0 ?

                            <tr>

                                <td colSpan="4" className="text-center">

                                    No applications found.

                                </td>

                            </tr>

                            :

                            applications.map((application) => (

                                <tr key={application.id}>

                                    <td>{application.companyName}</td>

                                    <td>{application.jobRole}</td>

                                    <td>

                                        <span className="badge bg-primary">

                                            {application.status}

                                        </span>

                                    </td>

                                    <td>

                                        {application.applicationDate}

                                    </td>

                                </tr>

                            ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default Applications;