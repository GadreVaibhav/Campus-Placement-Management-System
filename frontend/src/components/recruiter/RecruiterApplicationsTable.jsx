import { Table } from "react-bootstrap";

function RecruiterApplicationsTable({

    applications

}) {

    return (

        <div className="card shadow border-0">

            <div className="card-body">

                <Table
                    bordered
                    hover
                    responsive
                >

                    <thead className="table-primary">

                        <tr>

                            <th>ID</th>

                            <th>Student</th>

                            <th>Email</th>

                            <th>CGPA</th>

                            <th>Company</th>

                            <th>Job Role</th>

                            <th>Package</th>

                            <th>Min CGPA</th>

                            <th>Drive Date</th>

                            <th>Status</th>

                            <th>Applied On</th>

                        </tr>

                    </thead>

                    <tbody>

                        {

                            applications.length === 0 ?

                            (

                                <tr>

                                    <td
                                        colSpan="11"
                                        className="text-center"
                                    >

                                        No Applications Found

                                    </td>

                                </tr>

                            )

                            :

                            (

                                applications.map(app => (

                                    <tr key={app.applicationId}>

                                        <td>{app.applicationId}</td>

                                        <td>{app.studentName}</td>

                                        <td>{app.studentEmail}</td>

                                        <td>{app.cgpa}</td>

                                        <td>{app.companyName}</td>

                                        <td>{app.jobRole}</td>

                                        <td>{app.packageOffered}</td>

                                        <td>{app.minimumCgpa}</td>

                                        <td>{app.driveDate}</td>

                                        <td>

                                            <span
                                                className="badge bg-primary"
                                            >

                                                {app.status}

                                            </span>

                                        </td>

                                        <td>

                                            {

                                                app.applicationDate
                                                ?.substring(0,10)

                                            }

                                        </td>

                                    </tr>

                                ))

                            )

                        }

                    </tbody>

                </Table>

            </div>

        </div>

    );

}

export default RecruiterApplicationsTable;