import { Button, Table } from "react-bootstrap";
import { FaEdit, FaTrash } from "react-icons/fa";

import { deleteInterview } from "../../services/recruiterInterviewService";

function InterviewTable({

    interviews,

    reload,

    onEdit

}) {

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(

            "Are you sure you want to delete this interview?"

        );

        if (!confirmDelete) return;

        try {

            await deleteInterview(id);

            alert("Interview deleted successfully.");

            reload();

        }

        catch (error) {

            console.error(error);

            alert("Failed to delete interview.");

        }

    };

    return (

        <div className="card shadow border-0">

            <div className="card-body">

                <Table bordered hover responsive>

                    <thead className="table-primary">

                        <tr>

                            <th>ID</th>

                            <th>Student</th>

                            <th>Company</th>

                            <th>Job Role</th>

                            <th>Interview Time</th>

                            <th>Mode</th>

                            <th>Interviewer</th>

                            <th>Status</th>

                            <th width="150">Actions</th>

                        </tr>

                    </thead>

                    <tbody>

                        {interviews.length === 0 ? (

                            <tr>

                                <td colSpan="9" className="text-center">

                                    No Interviews Scheduled

                                </td>

                            </tr>

                        ) : (

                            interviews.map((interview) => (

                                <tr key={interview.interviewId}>

                                    <td>{interview.interviewId}</td>

                                    <td>{interview.studentName}</td>

                                    <td>{interview.companyName}</td>

                                    <td>{interview.jobTitle}</td>

                                    <td>{interview.interviewTime}</td>

                                    <td>{interview.interviewMode}</td>

                                    <td>{interview.interviewerName}</td>

                                    <td>

                                        <span className="badge bg-success">

                                            Scheduled

                                        </span>

                                    </td>

                                    <td>

                                        <Button

                                            size="sm"

                                            variant="warning"

                                            className="me-2"

                                            onClick={() => onEdit(interview)}

                                        >

                                            <FaEdit />

                                        </Button>

                                        <Button

                                            size="sm"

                                            variant="danger"

                                            onClick={() =>

                                                handleDelete(

                                                    interview.interviewId

                                                )

                                            }

                                        >

                                            <FaTrash />

                                        </Button>

                                    </td>

                                </tr>

                            ))

                        )}

                    </tbody>

                </Table>

            </div>

        </div>

    );

}

export default InterviewTable;