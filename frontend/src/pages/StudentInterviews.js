import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { getMyInterviews } from "../services/StudentInterviewService";

function StudentInterviews() {

    const [interviews, setInterviews] = useState([]);

    useEffect(() => {
        loadInterviews();
    }, []);

    const loadInterviews = async () => {

        try {

            const response = await getMyInterviews();

            setInterviews(response.data);

        } catch {

            toast.error("Unable to load interviews.");

        }

    };

    return (

        <div className="container mt-4">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>My Interviews</h3>

                </div>

                <div className="card-body">

                    {

                        interviews.length === 0 ?

                            (

                                <div className="alert alert-info">

                                    No interviews scheduled yet.

                                </div>

                            )

                            :

                            (

                                <table className="table table-bordered table-hover">

                                    <thead>

                                        <tr>

                                            <th>Company</th>
                                            <th>Job Role</th>
                                            <th>Interview Time</th>
                                            <th>Mode</th>
                                            <th>Interviewer</th>
                                            <th>Feedback</th>

                                        </tr>

                                    </thead>

                                    <tbody>

                                        {

                                            interviews.map(interview => (

                                                <tr key={interview.id}>

                                                    <td>{interview.companyName}</td>

                                                    <td>{interview.jobRole}</td>

                                                    <td>

                                                        {new Date(
                                                            interview.interviewTime
                                                        ).toLocaleString()}

                                                    </td>

                                                    <td>{interview.interviewMode}</td>

                                                    <td>{interview.interviewerName}</td>

                                                    <td>

                                                        {

                                                            interview.feedback ?

                                                                interview.feedback

                                                                :

                                                                "Pending"

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

export default StudentInterviews;