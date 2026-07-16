import { useEffect, useState } from "react";
import { getRecentApplications } from "../../services/ApplicationService";

function RecentApplications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {
        loadApplications();
    }, []);

    const loadApplications = async () => {

        try {

            const response = await getRecentApplications();

            setApplications(response.data);

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

                <table className="table table-hover">

                    <thead>

                    <tr>

                        <th>Student</th>

                        <th>Company</th>

                        <th>Role</th>

                        <th>Status</th>

                        <th>Date</th>

                    </tr>

                    </thead>

                    <tbody>

                    {

                        applications.map(app => (

                            <tr key={app.id}>

                                <td>{app.studentName}</td>

                                <td>{app.companyName}</td>

                                <td>{app.jobRole}</td>

                                <td>

                                    <span className="badge bg-success">

                                        {app.status}

                                    </span>

                                </td>

                                <td>

                                    {new Date(app.applicationDate)
                                        .toLocaleDateString()}

                                </td>

                            </tr>

                        ))

                    }

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default RecentApplications;