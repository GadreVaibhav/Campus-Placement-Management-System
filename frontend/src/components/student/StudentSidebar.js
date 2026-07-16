import { NavLink, useNavigate } from "react-router-dom";

import {
    FaTachometerAlt,
    FaUserGraduate,
    FaFileAlt,
    FaBriefcase,
    FaClipboardList,
    FaCalendarCheck,
    FaCog,
    FaSignOutAlt,
    FaUniversity
} from "react-icons/fa";

import "./student.css";

function StudentSidebar() {

    const navigate = useNavigate();

    const handleLogout = () => {

        localStorage.clear();

        navigate("/");

    };

    return (

        <div className="student-sidebar">

            <div className="student-sidebar-header">

                <FaUniversity size={42} />

                <h3>Placement Portal</h3>

                <p>Student Panel</p>

            </div>

            <nav className="student-menu">

                <NavLink
                    to="/student/dashboard"
                    className="student-link"
                >
                    <FaTachometerAlt />
                    <span>Dashboard</span>
                </NavLink>

                <NavLink
                    to="/student/profile"
                    className="student-link"
                >
                    <FaUserGraduate />
                    <span>Profile</span>
                </NavLink>

                <NavLink
                    to="/student/documents"
                    className="student-link"
                >
                    <FaFileAlt />
                    <span>Documents</span>
                </NavLink>

                <NavLink
                    to="/student/jobs"
                    className="student-link"
                >
                    <FaBriefcase />
                    <span>Available Jobs</span>
                </NavLink>

                <NavLink
                    to="/student/applications"
                    className="student-link"
                >
                    <FaClipboardList />
                    <span>Applications</span>
                </NavLink>

                <NavLink
                    to="/student/interviews"
                    className="student-link"
                >
                    <FaCalendarCheck />
                    <span>Interviews</span>
                </NavLink>

                <NavLink
                    to="/student/settings"
                    className="student-link"
                >
                    <FaCog />
                    <span>Settings</span>
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

export default StudentSidebar;