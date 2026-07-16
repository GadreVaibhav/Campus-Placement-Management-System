import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { scheduleInterview } from "../services/InterviewService";

function ScheduleInterview() {

    const { applicationId } = useParams();

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        interviewTime: "",
        interviewMode: "",
        interviewerName: "",
        feedback: ""
    });

    const handleChange = (e) => {

        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await scheduleInterview(applicationId, formData);

            alert("Interview Scheduled Successfully!");

            navigate("/recruiter/applications");

        } catch (error) {

            console.error(error);

            alert("Failed to schedule interview.");

        }

    };

    return (

        <div className="container mt-4">

            <h2>Schedule Interview</h2>

            <form onSubmit={handleSubmit}>

                <div className="mb-3">

                    <label>Interview Time</label>

                    <input
                        type="datetime-local"
                        name="interviewTime"
                        className="form-control"
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Interview Mode</label>

                    <input
                        type="text"
                        name="interviewMode"
                        className="form-control"
                        onChange={handleChange}
                        placeholder="Online / Offline"
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Interviewer Name</label>

                    <input
                        type="text"
                        name="interviewerName"
                        className="form-control"
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Feedback</label>

                    <textarea
                        name="feedback"
                        className="form-control"
                        onChange={handleChange}
                    />

                </div>

                <button className="btn btn-primary">
                    Schedule Interview
                </button>

            </form>

        </div>

    );

}

export default ScheduleInterview;