import {
    FaTachometerAlt,
    FaUserGraduate,
    FaBuilding,
    FaUserTie,
    FaBriefcase,
    FaClipboardList,
    FaCalendarAlt,
    FaSignOutAlt,
    FaSuitcase
} from "react-icons/fa";

import { Link, useLocation, useNavigate } from "react-router-dom";

function AdminSidebar() {

    const location = useLocation();
    const navigate = useNavigate();

    const logout = () => {
    localStorage.clear();
    navigate("/");
};

    const menu = [

        {
            title: "Dashboard",
            icon: <FaTachometerAlt />,
            path: "/admin/dashboard"
        },

        {
            title: "Students",
            icon: <FaUserGraduate />,
            path: "/admin/students"
        },

        {
            title: "Companies",
            icon: <FaBuilding />,
            path: "/admin/companies"
        },

        {
            title: "Recruiters",
            icon: <FaUserTie />,
            path: "/admin/recruiters"
        },

        {
            title: "Jobs",
            icon: <FaSuitcase />,
            path: "/admin/jobs"
        },
        
        {
            title: "Placement Drives",
            icon: <FaBriefcase />,
            path: "/admin/drives"
        },

        {
            title: "Applications",
            icon: <FaClipboardList />,
            path: "/admin/applications"
        },

        {
            title: "Interviews",
            icon: <FaCalendarAlt />,
            path: "/admin/interviews"
        }

    ];

    return (

       <div
    className="bg-dark text-white"
    style={{
        width: "260px",
        height: "100vh",
        position: "fixed",
        left: 0,
        top: 0,
        overflowY: "auto",
        zIndex: 1000
    }}
>

            <h3 className="text-center py-4">
                Placement Portal
            </h3>

            <hr />

            {

                menu.map((item) => (

                    <Link

                        key={item.title}

                        to={item.path}

                        className={`d-flex align-items-center text-decoration-none p-3 ${
                            location.pathname === item.path
                                ? "bg-primary text-white"
                                : "text-white"
                        }`}

                    >

                        <span className="me-3">

                            {item.icon}

                        </span>

                        {item.title}

                    </Link>

                ))

            }

            <button

                onClick={logout}

                className="btn btn-danger m-3 w-75"

            >

                <FaSignOutAlt />

                {" "}Logout

            </button>

        </div>

    );

}

export default AdminSidebar;