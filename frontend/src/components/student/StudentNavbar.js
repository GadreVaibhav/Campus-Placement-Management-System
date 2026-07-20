import { FaUserGraduate } from "react-icons/fa";

function StudentNavbar() {

    return (

        <div
            className="d-flex justify-content-between align-items-center px-4 py-3 bg-white shadow-sm"
            style={{
                borderBottom: "1px solid #e5e7eb",
                position: "fixed",
                top: 0,
                left: "260px",      // Sidebar width
                right: 0,
                zIndex: 1000,
                height: "80px"
            }}
        >

            <div>

                <h3
                    className="fw-bold mb-0"
                    style={{
                        color: "#0d6efd"
                    }}
                >
                    Student Dashboard
                </h3>

                <small className="text-muted">
                    Campus Placement Management System
                </small>

            </div>

            <div
                className="d-flex align-items-center gap-3"
            >

                <FaUserGraduate
                    size={38}
                    color="#0d6efd"
                />

                <div>

                    <h6 className="mb-0 fw-bold">
                        Student
                    </h6>

                    <small className="text-muted">
                        Welcome Back
                    </small>

                </div>

            </div>

        </div>

    );

}

export default StudentNavbar;