import { NavLink, useNavigate } from "react-router-dom";

import {
    FaTachometerAlt,
    FaUserGraduate,
    FaBuilding,
    FaUserTie,
    FaBriefcase,
    FaClipboardList,
    FaCalendarCheck,
    FaSignOutAlt,
    FaUniversity,
    FaSuitcase
} from "react-icons/fa";

import "../recruiter/recruiter.css";

function AdminSidebar() {

    const navigate = useNavigate();

    const handleLogout = () => {

        localStorage.clear();

        navigate("/");

    };

    return (

        <div className="student-sidebar">

            <div className="student-sidebar-header">

                <FaUniversity />

                <h3>Placement Portal</h3>

                <p>Admin Panel</p>

            </div>

            <nav className="student-menu">

                <NavLink
                    to="/admin/dashboard"
                    className="student-link"
                >
                    <FaTachometerAlt />
                    <span>Dashboard</span>
                </NavLink>

                <NavLink
                    to="/admin/students"
                    className="student-link"
                >
                    <FaUserGraduate />
                    <span>Students</span>
                </NavLink>

                <NavLink
                    to="/admin/companies"
                    className="student-link"
                >
                    <FaBuilding />
                    <span>Companies</span>
                </NavLink>

                <NavLink
                    to="/admin/recruiters"
                    className="student-link"
                >
                    <FaUserTie />
                    <span>Recruiters</span>
                </NavLink>

                <NavLink
                    to="/admin/jobs"
                    className="student-link"
                >
                    <FaSuitcase />
                    <span>Jobs</span>
                </NavLink>

                <NavLink
                    to="/admin/drives"
                    className="student-link"
                >
                    <FaBriefcase />
                    <span>Placement Drives</span>
                </NavLink>

                <NavLink
                    to="/admin/applications"
                    className="student-link"
                >
                    <FaClipboardList />
                    <span>Applications</span>
                </NavLink>

                <NavLink
                    to="/admin/interviews"
                    className="student-link"
                >
                    <FaCalendarCheck />
                    <span>Interviews</span>
                </NavLink>

            </nav>

            <button
                className="student-logout"
                onClick={handleLogout}
            >
                <FaSignOutAlt />
                Logout
            </button>

        </div>

    );

}

export default AdminSidebar;