import { useEffect, useState } from "react";

import {
    getAllApplications,
    updateApplicationStatus,
    deleteApplication
} from "../services/ApplicationService";

function ApplicationManagement() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {

        loadApplications();

    }, []);

    const loadApplications = async () => {

        try {

            const response = await getAllApplications();

            setApplications(response.data);

        }

        catch (error) {

            console.error(error);

            alert("Unable to load applications.");

        }

    };

    const changeStatus = (index, status) => {

        const updated = [...applications];

        updated[index].status = status;

        setApplications(updated);

    };

    const handleUpdate = async (application) => {

        try {

            await updateApplicationStatus(

                application.id,

                application.status

            );

            alert("Application status updated successfully.");

            loadApplications();

        }

        catch (error) {

            console.error(error);

            alert("Unable to update application.");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Delete this application?")) {

            return;

        }

        try {

            await deleteApplication(id);

            alert("Application deleted successfully.");

            loadApplications();

        }

        catch (error) {

            console.error(error);

            alert("Unable to delete application.");

        }

    };

    const badgeColor = (status) => {

        switch (status) {

            case "APPLIED":
                return "primary";

            case "UNDER_REVIEW":
                return "warning";

            case "SHORTLISTED":
                return "info";

            case "SELECTED":
                return "success";

            case "REJECTED":
                return "danger";

            default:
                return "secondary";
        }

    };

    return (

        <div className="container-fluid">

            <h2 className="mb-4">
                Application Management
            </h2>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>

                        <th>Student</th>

                        <th>Company</th>

                        <th>Job Role</th>

                        <th>Status</th>

                        <th>Applied Date</th>

                        <th width="260">
                            Action
                        </th>

                    </tr>

                </thead>

                <tbody>

                    {

                        applications.map((application, index) => (

                            <tr key={application.id}>

                                <td>{application.id}</td>

                                <td>{application.studentName}</td>

                                <td>{application.companyName}</td>

                                <td>{application.jobRole}</td>

                                <td>

                                    <span
                                        className={`badge bg-${badgeColor(application.status)}`}
                                    >

                                        {application.status}

                                    </span>

                                </td>

                                <td>

                                    {application.applicationDate}

                                </td>

                                <td>

                                    <div className="d-flex">

                                        <select

                                            className="form-select me-2"

                                            value={application.status}

                                            onChange={(e) =>

                                                changeStatus(

                                                    index,

                                                    e.target.value

                                                )

                                            }

                                        >

                                            <option value="APPLIED">
                                                APPLIED
                                            </option>

                                            <option value="UNDER_REVIEW">
                                                UNDER REVIEW
                                            </option>

                                            <option value="SHORTLISTED">
                                                SHORTLISTED
                                            </option>

                                            <option value="SELECTED">
                                                SELECTED
                                            </option>

                                            <option value="REJECTED">
                                                REJECTED
                                            </option>

                                        </select>

                                        <button

                                            className="btn btn-success me-2"

                                            onClick={() =>
                                                handleUpdate(application)
                                            }

                                        >

                                            Update

                                        </button>

                                        <button

                                            className="btn btn-danger"

                                            onClick={() =>
                                                handleDelete(application.id)
                                            }

                                        >

                                            Delete

                                        </button>

                                    </div>

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </div>

    );

}

export default ApplicationManagement;