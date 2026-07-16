import { useEffect, useState } from "react";
import {
    getAllInterviews,
    deleteInterview
} from "../services/InterviewService";
import { useNavigate } from "react-router-dom";
function InterviewManagement() {

    const [interviews, setInterviews] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadInterviews();
    }, []);

    const loadInterviews = async () => {

        try {

            const response = await getAllInterviews();

            setInterviews(response.data);

        } catch (error) {

            console.error(error);

            alert("Unable to load interviews.");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Delete this interview?")) {

            return;

        }

        try {

            await deleteInterview(id);

            loadInterviews();

        } catch (error) {

            console.error(error);

            alert("Unable to delete interview.");

        }

    };

    return (

        <div className="container-fluid">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Interview Management</h2>

            </div>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>
                        <th>Company</th>
                        <th>Job Role</th>
                        <th>Interview Time</th>
                        <th>Mode</th>
                        <th>Interviewer</th>
                        <th>Feedback</th>
                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        interviews.map((interview) => (

                            <tr key={interview.id}>

                                <td>{interview.id}</td>

                                <td>{interview.companyName}</td>

                                <td>{interview.jobRole}</td>

                                <td>{interview.interviewTime}</td>

                                <td>{interview.interviewMode}</td>

                                <td>{interview.interviewerName}</td>

                                <td>{interview.feedback}</td>

                                <td>

                                    <button
                                        className="btn btn-warning btn-sm me-2"
                                        onClick={() =>
                                            navigate(`/admin/interviews/edit/${interview.id}`)
                                        }
                                     >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(interview.id)}
                                    >
                                        Delete
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

export default InterviewManagement;