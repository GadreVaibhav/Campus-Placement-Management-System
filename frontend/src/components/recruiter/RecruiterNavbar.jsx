import { FaUserTie } from "react-icons/fa";

function RecruiterNavbar() {

    return (

        <div
            className="d-flex justify-content-between align-items-center px-4 py-3 bg-white shadow-sm"
            style={{
                borderBottom: "1px solid #e5e7eb"
            }}
        >

            <div>

                <h3
                    className="fw-bold mb-0"
                    style={{
                        color: "#0d6efd"
                    }}
                >
                    Recruiter Dashboard
                </h3>

                <small className="text-muted">
                    Campus Placement Management System
                </small>

            </div>

            <div
                className="d-flex align-items-center gap-3"
            >

                <FaUserTie
                    size={38}
                    color="#0d6efd"
                />

                <div>

                    <h6 className="mb-0 fw-bold">
                        Recruiter
                    </h6>

                    <small className="text-muted">
                        Welcome Back
                    </small>

                </div>

            </div>

        </div>

    );

}

export default RecruiterNavbar;