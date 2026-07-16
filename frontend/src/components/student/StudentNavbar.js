import { FaBell } from "react-icons/fa";

function StudentNavbar() {

    const today = new Date().toLocaleDateString("en-IN", {
        weekday: "long",
        day: "numeric",
        month: "long",
        year: "numeric"
    });

    return (

        <div
            className="student-navbar d-flex justify-content-between align-items-center"
        >

            <div>

                <h4 className="mb-1">
                    Welcome Back 👋
                </h4>

                <small className="text-muted">
                    {today}
                </small>

            </div>

            <div className="d-flex align-items-center gap-4">

                <button className="notification-btn">

                    <FaBell />

                </button>

                <div className="student-user">

                    <img
                        src="https://ui-avatars.com/api/?name=Student&background=2a5298&color=fff"
                        alt="Student"
                    />

                    <div>

                        <strong>Student</strong>

                        <div className="text-muted small">
                            Placement Portal
                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentNavbar;