import { useEffect, useState } from "react";
import { getUpcomingInterviews } from "../../services/recruiterInterviewService";

function UpcomingInterviews() {

    const [interviews, setInterviews] = useState([]);

    useEffect(() => {
        loadInterviews();
    }, []);

    const loadInterviews = async () => {

        try {

            const data = await getUpcomingInterviews();

            setInterviews(data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="card shadow border-0 mt-4">

            <div className="card-header bg-success text-white">

                <h5 className="mb-0">

                    Upcoming Interviews

                </h5>

            </div>

            <div className="card-body">

                <table className="table table-hover">

                    <thead>

                        <tr>

                            <th>Student</th>

                            <th>Company</th>

                            <th>Job</th>

                            <th>Date & Time</th>

                            <th>Mode</th>

                        </tr>

                    </thead>

                    <tbody>

                        {interviews.map((item) => (

                            <tr key={item.interviewId}>

                                <td>{item.studentName}</td>

                                <td>{item.companyName}</td>

                                <td>{item.jobTitle}</td>

                                <td>{item.interviewTime}</td>

                                <td>{item.interviewMode}</td>

                            </tr>

                        ))}

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default UpcomingInterviews;