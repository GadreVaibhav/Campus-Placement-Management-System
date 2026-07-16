import {
    FaBell,
    FaSearch,
    FaUserCircle
} from "react-icons/fa";

import { useEffect, useState } from "react";

import { getLoggedInStudent } from "../../services/StudentService";

function Navbar() {

    const [name, setName] = useState("");

    const role = localStorage.getItem("role");

    useEffect(() => {

        if (role === "STUDENT") {

            loadStudent();

        }

    }, []);

    const loadStudent = async () => {

        try {

            const response = await getLoggedInStudent();

            setName(response.data.name);

        } catch (error) {

            console.log(error);

        }

    };

    return (

        <nav className="navbar navbar-light bg-white shadow-sm px-4">

            <div className="d-flex align-items-center">

                <FaSearch className="text-secondary me-2"/>

                <input
                    className="form-control border-0"
                    placeholder="Search..."
                    style={{
                        width: "250px",
                        boxShadow: "none"
                    }}
                />

            </div>

            <div className="d-flex align-items-center">

                <FaBell
                    className="me-4 text-secondary"
                    size={20}
                />

                <FaUserCircle
                    size={38}
                    className="text-primary me-2"
                />

                <div>

                    <div className="fw-bold">

                        {name || "User"}

                    </div>

                    <small className="text-muted">

                        {role}

                    </small>

                </div>

            </div>

        </nav>

    );

}

export default Navbar;