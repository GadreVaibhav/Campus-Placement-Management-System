import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
    getJobById,
    applyJob
} from "../services/StudentService";
import { toast } from "react-toastify";
function JobDetails() {

    const { id } = useParams();

    const navigate = useNavigate();

    const [job, setJob] = useState(null);

    const [applying, setApplying] = useState(false);

    useEffect(() => {
        loadJob();
    }, []);

    const loadJob = async () => {

        try {

            const response = await getJobById(id);

            setJob(response.data);

        } catch (error) {

            console.log(error);

        }

    };

    if (!job) {

        return (
            <div className="container mt-5">
                Loading...
            </div>
        );

    }

    const handleApply = async () => {

    try {

        setApplying(true);

        await applyJob(id);

        toast.success("Application submitted successfully.");

    }

    catch (error) {

        if (error.response?.data?.message) {

            toast.error(error.response.data.message);

        }

        else {

            toast.error("Unable to apply.");

        }

    }

    finally {

        setApplying(false);

    }

};

    return (

        <div className="container mt-5">

            <button
                className="btn btn-secondary mb-3"
                onClick={() => navigate(-1)}
            >
                ← Back
            </button>

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h2>{job.jobTitle}</h2>

                    <h5>{job.companyName}</h5>

                </div>

                <div className="card-body">

                    <p><strong>📍 Location:</strong> {job.location}</p>

                    <p><strong>💰 Package:</strong> {job.packageLpa} LPA</p>

                    <p><strong>🎓 Required CGPA:</strong> {job.eligibilityCgpa}</p>

                    <p><strong>🛠 Skills:</strong> {job.skillsRequired}</p>

                    <p><strong>📅 Last Date:</strong> {job.lastDate}</p>

                    <hr />

                    <h5>Description</h5>

                    <p>{job.description}</p>

                    <hr />

                    <button
                        className="btn btn-success"
                        onClick={handleApply}
                        disabled={applying}
                    >

                        {applying ? "Applying..." : "Apply Now"}

                    </button>

                </div>

            </div>

        </div>

    );

}

export default JobDetails;