import {
    FaBuilding,
    FaLaptop,
    FaCalendarAlt,
    FaUserTie,
    FaCommentDots
} from "react-icons/fa";

function InterviewCard({ interview }) {

    return (

        <div className="card shadow border-0">

            <div className="card-header bg-primary text-white">

                <h4>Interview Details</h4>

            </div>

            <div className="card-body">

                <p>
                    <FaBuilding className="me-2 text-primary" />
                    <strong>Company:</strong> {interview.companyName}
                </p>

                <p>
                    <FaLaptop className="me-2 text-success" />
                    <strong>Job Role:</strong> {interview.jobRole}
                </p>

                <p>
                    <FaCalendarAlt className="me-2 text-warning" />
                    <strong>Interview Time:</strong> {interview.interviewTime}
                </p>

                <p>
                    <strong>Mode:</strong> {interview.interviewMode}
                </p>

                <p>
                    <FaUserTie className="me-2 text-info" />
                    <strong>Interviewer:</strong> {interview.interviewerName}
                </p>

                <p>
                    <FaCommentDots className="me-2 text-secondary" />
                    <strong>Feedback:</strong>{" "}
                    {interview.feedback || "Pending"}
                </p>

            </div>

        </div>

    );

}

export default InterviewCard;