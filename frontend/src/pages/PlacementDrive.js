import { useEffect, useState } from "react";
import { getAvailableJobs } from "../services/StudentService";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";

function AvailableJobs() {

    const [jobs, setJobs] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadJobs();
    }, []);

    const loadJobs = async () => {

        try {

            const response = await getAvailableJobs();

            setJobs(response.data);

        } catch {

            toast.error("Unable to load available jobs.");

        }

    };

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                Available Jobs
            </h2>

            {jobs.length === 0 ? (

                <div className="alert alert-info">

                    No jobs available.

                </div>

            ) : (

                <div className="row">

                    {jobs.map(job => (

                        <div
                            className="col-md-6 mb-4"
                            key={job.id}
                        >

                            <div className="card shadow h-100">

                                <div className="card-body">

                                    <h4 className="text-primary">

                                        {job.jobTitle}

                                    </h4>

                                    <h6 className="text-muted">

                                        {job.companyName}

                                    </h6>

                                    <hr />

                                    <p>

                                        <strong>📍 Location:</strong>

                                        {" "}

                                        {job.location}

                                    </p>

                                    <p>

                                        <strong>💰 Package:</strong>

                                        {" "}

                                        {job.packageLpa} LPA

                                    </p>

                                    <p>

                                        <strong>🎓 Required CGPA:</strong>

                                        {" "}

                                        {job.eligibilityCgpa}

                                    </p>

                                    <p>

                                        <strong>🛠 Skills:</strong>

                                        {" "}

                                        {job.skillsRequired}

                                    </p>

                                    <p>

                                        <strong>📅 Last Date:</strong>

                                        {" "}

                                        {job.lastDate}

                                    </p>

                                    <p>

                                        <strong>Status:</strong>

                                        {" "}

                                        {job.eligible ? (

                                            <span className="badge bg-success">

                                                Eligible

                                            </span>

                                        ) : (

                                            <span className="badge bg-danger">

                                                Not Eligible

                                            </span>

                                        )}

                                    </p>

                                   <button
                                        className="btn btn-primary w-100"
                                        onClick={() => navigate(`/student/jobs/${job.id}`)}
                                    >
                                        View Details
                                    </button>

                                </div>

                            </div>

                        </div>

                    ))}

                </div>

            )}

        </div>

    );

}

export default AvailableJobs;