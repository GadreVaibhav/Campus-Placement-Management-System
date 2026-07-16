import {
  FaTachometerAlt,
  FaUserGraduate,
  FaFileAlt,
  FaBuilding,
  FaClipboardList,
  FaCalendarAlt,
  FaCog,
  FaSignOutAlt,
  FaUniversity
} from "react-icons/fa";

import { Link, useLocation, useNavigate } from "react-router-dom";

function Sidebar() {

  const location = useLocation();
  const navigate = useNavigate();

const handleLogout = () => {

    localStorage.clear();
    navigate("/");

};
  const menuItems = [
    { title: "Dashboard", icon: <FaTachometerAlt />, path: "/student" },
    { title: "Profile", icon: <FaUserGraduate />, path: "/profile" },
    { title: "Documents", icon: <FaFileAlt />, path: "/documents" },
    { title: "Companies", icon: <FaBuilding />, path: "/companies" },
    { title: "Applications", icon: <FaClipboardList />, path: "/applications" },
    { title: "Interviews", icon: <FaCalendarAlt />, path: "/interviews" },
    { title: "Settings", icon: <FaCog />, path: "/settings" }
  ];

  return (

    <div
      className="text-white shadow-lg"
      style={{
        width: "270px",
        minHeight: "100vh",
        background: "linear-gradient(180deg,#0f172a,#1e3a8a)"
      }}
    >

      <div className="text-center py-4 border-bottom">

        <FaUniversity size={45} />

        <h4 className="mt-3 fw-bold">
          Placement Portal
        </h4>

        <small className="text-light">
          Student Panel
        </small>

      </div>

      <div className="px-3 py-4">

        {menuItems.map((item) => (

          <Link
            key={item.title}
            to={item.path}
            className={`d-flex align-items-center mb-3 text-decoration-none p-3 rounded ${
              location.pathname === item.path
                ? "bg-white text-primary"
                : "text-white"
            }`}
            style={{
              transition: "0.3s"
            }}
          >

            <span className="me-3 fs-5">
              {item.icon}
            </span>

            <span className="fw-semibold">
              {item.title}
            </span>

          </Link>

        ))}

      </div>

      <div className="mt-auto px-3">

        <button
            onClick={handleLogout}
            className="btn text-danger d-flex align-items-center p-3 border-0 bg-transparent"
        >

            <FaSignOutAlt className="me-3" />

            Logout

        </button>

      </div>

    </div>

  );

}

export default Sidebar;