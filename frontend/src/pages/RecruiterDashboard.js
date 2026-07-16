import { Link } from "react-router-dom";

function RecruiterDashboard() {

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                Recruiter Dashboard
            </h2>

            <div className="row g-4">

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body text-center">

                            <h4>Placement Drives</h4>

                            <Link
                                to="/recruiter/drives"
                                className="btn btn-primary mt-3"
                            >
                                Manage Drives
                            </Link>

                        </div>

                    </div>

                </div>

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body text-center">

                            <h4>Applications</h4>

                            <Link
                                to="/recruiter/applications"
                                className="btn btn-success mt-3"
                            >
                                View Applications
                            </Link>

                        </div>

                    </div>

                </div>

                <div className="col-md-4">

                    <div className="card shadow">

                        <div className="card-body text-center">

                            <h4>Interviews</h4>

                            <Link
                                to="/recruiter/interviews"
                                className="btn btn-warning mt-3"
                            >
                                Schedule Interview
                            </Link>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default RecruiterDashboard;