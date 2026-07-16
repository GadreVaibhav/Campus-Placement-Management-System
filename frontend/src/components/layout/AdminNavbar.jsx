import { FaBell, FaUserCircle } from "react-icons/fa";

function AdminNavbar() {

    const role = localStorage.getItem("role") || "ADMIN";

    return (

        <nav className="navbar navbar-light bg-white shadow-sm px-4">

            <div>
                <h4 className="mb-0">
                    Admin Dashboard
                </h4>
            </div>

            <div className="d-flex align-items-center">

                <FaBell
                    size={20}
                    className="me-4 text-secondary"
                />

                <FaUserCircle
                    size={38}
                    className="text-primary me-2"
                />

                <div>

                    <div className="fw-bold">
                        Admin
                    </div>

                    <small className="text-muted">
                        {role}
                    </small>

                </div>

            </div>

        </nav>

    );

}

export default AdminNavbar;