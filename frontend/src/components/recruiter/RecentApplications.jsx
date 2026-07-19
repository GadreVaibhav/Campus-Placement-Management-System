import { useEffect, useState } from "react";
import { getRecentApplications } from "../../services/recruiterApplicationService";

function RecentApplications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {
        loadApplications();
    }, []);

    const loadApplications = async () => {
        try {
            const data = await getRecentApplications();
            console.log(data);
            setApplications(data);
        } catch (error) {
            console.error(error);
        }
    };

    return (

        <div className="card shadow border-0 mt-4">

            <div className="card-header bg-primary text-white">

                <h5 className="mb-0">
                    Recent Applications
                </h5>

            </div>

            <div className="card-body">

                {applications.length === 0 ? (

                    <p className="text-muted">
                        No recent applications found.
                    </p>

                ) : (

                    <table className="table table-hover">

                        <thead>

                            <tr>

                                <th>Student</th>

                                <th>Company</th>

                                <th>Job Role</th>

                                <th>Status</th>

                            </tr>

                        </thead>

                        <tbody>

                            {applications.map((application, index) => (

                                <tr key={index}>

                                    <td>{application.studentName}</td>

                                    <td>{application.companyName}</td>

                                    <td>{application.jobRole}</td>

                                    <td>

                                        <span className="badge bg-success">

                                            {application.status}

                                        </span>

                                    </td>

                                </tr>

                            ))}

                        </tbody>

                    </table>

                )}

            </div>

        </div>

    );

}

export default RecentApplications;