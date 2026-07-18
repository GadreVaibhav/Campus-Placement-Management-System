import { useEffect, useState } from "react";
import { toast } from "react-toastify";

import { getMyApplications } from "../services/StudentService";

function MyApplications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {

        loadApplications();

    }, []);

    const loadApplications = async () => {

        try {

            const response = await getMyApplications();

            setApplications(response.data);

        }

        catch {

            toast.error("Unable to load applications.");

        }

    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>My Applications</h3>

                </div>

                <div className="card-body">

                    {
                        applications.length === 0 ?

                            (

                                <div className="alert alert-info">

                                    You haven't applied for any jobs yet.

                                </div>

                            )

                            :

                            (

                                <table className="table table-bordered table-hover">

                                    <thead>

                                        <tr>

                                            <th>Job</th>

                                            <th>Company</th>

                                            <th>Package</th>

                                            <th>Status</th>

                                            <th>Applied On</th>

                                        </tr>

                                    </thead>

                                    <tbody>

                                        {

                                            applications.map((application) => (

                                                <tr key={application.id}>

                                                    <td>

                                                        {application.jobTitle}

                                                    </td>

                                                    <td>

                                                        {application.companyName}

                                                    </td>

                                                    <td>

                                                        {application.packageLpa} LPA

                                                    </td>

                                                    <td>

                                                        <span
                                                            className={
                                                                application.status === "SELECTED"
                                                                    ? "badge bg-success"

                                                                    : application.status === "REJECTED"

                                                                    ? "badge bg-danger"

                                                                    : application.status === "SHORTLISTED"

                                                                    ? "badge bg-warning text-dark"

                                                                    : "badge bg-primary"
                                                            }
                                                        >

                                                            {application.status}

                                                        </span>

                                                    </td>

                                                    <td>

                                                       {
                                                            application.appliedAt
                                                                ? new Date(application.appliedAt).toLocaleDateString()
                                                                : "-"
                                                        }

                                                    </td>

                                                </tr>

                                            ))

                                        }

                                    </tbody>

                                </table>

                            )

                    }

                </div>

            </div>

        </div>

    );

}

export default MyApplications;