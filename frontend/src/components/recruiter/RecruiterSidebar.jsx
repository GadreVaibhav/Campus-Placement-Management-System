import { NavLink, useNavigate } from "react-router-dom";

import {
    FaTachometerAlt,
    FaUserTie,
    FaBriefcase,
    FaClipboardList,
    FaCalendarCheck,
    FaUserGraduate,
    FaSignOutAlt,
    FaUniversity
} from "react-icons/fa";

import "./recruiter.css";

function RecruiterSidebar() {

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

                <p>Recruiter Panel</p>

            </div>

            <nav className="student-menu">

                <NavLink
                    to="/recruiter/dashboard"
                    className="student-link"
                >
                    <FaTachometerAlt />
                    <span>Dashboard</span>
                </NavLink>

                <NavLink
                    to="/recruiter/profile"
                    className="student-link"
                >
                    <FaUserTie />
                    <span>My Profile</span>
                </NavLink>

                <NavLink
                    to="/recruiter/drives"
                    className="student-link"
                >
                    <FaBriefcase />
                    <span>Placement Drives</span>
                </NavLink>

                <NavLink
                    to="/recruiter/applications"
                    className="student-link"
                >
                    <FaClipboardList />
                    <span>Applications</span>
                </NavLink>

                <NavLink
                    to="/recruiter/interviews"
                    className="student-link"
                >
                    <FaCalendarCheck />
                    <span>Interviews</span>
                </NavLink>

                <NavLink
                    to="/recruiter/students"
                    className="student-link"
                >
                    <FaUserGraduate />
                    <span>Students</span>
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

export default RecruiterSidebar;