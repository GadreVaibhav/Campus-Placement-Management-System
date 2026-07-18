import { useEffect, useState } from "react";
import { getMyApplications } from "../services/ApplicationService";

function StudentApplications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {
        loadApplications();
    }, []);

    const loadApplications = async () => {

        try {

            const response = await getMyApplications();

            setApplications(response.data);

        } catch (error) {

            console.error(error);

            alert("Unable to load applications.");

        }

    };

    return (

        <div className="container-fluid">

            <h2 className="mb-4">
                My Applications
            </h2>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>Company</th>

                        <th>Job Role</th>

                        <th>Package (LPA)</th>

                        <th>Status</th>

                        <th>Applied Date</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        applications.length === 0 ?

                            (

                                <tr>

                                    <td
                                        colSpan="5"
                                        className="text-center">

                                        No Applications Found

                                    </td>

                                </tr>

                            )

                            :

                            applications.map((application) => (

                                <tr key={application.id}>

                                    <td>
                                        {application.companyName}
                                    </td>

                                    <td>
                                        {application.jobTitle}
                                    </td>

                                    <td>
                                        {application.packageLpa}
                                    </td>

                                    <td>

                                        <span className={`badge ${

                                            application.status === "SELECTED"

                                                ? "bg-success"

                                                : application.status === "REJECTED"

                                                    ? "bg-danger"

                                                    : "bg-warning text-dark"

                                        }`}>

                                            {application.status}

                                        </span>

                                    </td>

                                    <td>

                                        {new Date(

                                            application.appliedAt

                                        ).toLocaleDateString()}

                                    </td>

                                </tr>

                            ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default StudentApplications;