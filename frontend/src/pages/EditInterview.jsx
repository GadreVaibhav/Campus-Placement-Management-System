import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import {
    getAllInterviews,
    updateInterview
} from "../services/InterviewService";

function EditInterview() {

    const navigate = useNavigate();

    const { id } = useParams();

    const [interview, setInterview] = useState({

        interviewTime: "",

        interviewMode: "Online",

        interviewerName: "",

        feedback: ""

    });

    useEffect(() => {

        loadInterview();

    }, []);

    const loadInterview = async () => {

        try {

            const response = await getAllInterviews();

            const selectedInterview = response.data.find(

                item => item.id === parseInt(id)

            );

            if (selectedInterview) {

                setInterview({

                    interviewTime: selectedInterview.interviewTime,

                    interviewMode: selectedInterview.interviewMode,

                    interviewerName: selectedInterview.interviewerName,

                    feedback: selectedInterview.feedback

                });

            }

        } catch (error) {

            console.error(error);

            alert("Unable to load interview.");

        }

    };

    const handleChange = (e) => {

        setInterview({

            ...interview,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await updateInterview(id, interview);

            alert("Interview updated successfully.");

            navigate("/admin/interviews");

        } catch (error) {

            console.error(error);

            alert("Unable to update interview.");

        }

    };

    return (

        <div className="container mt-4">

            <h2>Edit Interview</h2>

            <form onSubmit={handleSubmit}>

                <div className="mb-3">

                    <label>Interview Time</label>

                    <input

                        type="datetime-local"

                        className="form-control"

                        name="interviewTime"

                        value={interview.interviewTime}

                        onChange={handleChange}

                    />

                </div>

                <div className="mb-3">

                    <label>Interview Mode</label>

                    <select

                        className="form-control"

                        name="interviewMode"

                        value={interview.interviewMode}

                        onChange={handleChange}

                    >

                        <option>Online</option>

                        <option>Offline</option>

                    </select>

                </div>

                <div className="mb-3">

                    <label>Interviewer Name</label>

                    <input

                        type="text"

                        className="form-control"

                        name="interviewerName"

                        value={interview.interviewerName}

                        onChange={handleChange}

                    />

                </div>

                <div className="mb-3">

                    <label>Feedback</label>

                    <textarea

                        className="form-control"

                        name="feedback"

                        value={interview.feedback}

                        onChange={handleChange}

                    />

                </div>

                <button className="btn btn-primary">

                    Update Interview

                </button>

            </form>

        </div>

    );

}

export default EditInterview;