import { Outlet } from "react-router-dom";

import RecruiterSidebar from "./RecruiterSidebar";
import RecruiterNavbar from "./RecruiterNavbar";

function RecruiterLayout() {

    return (

        <div className="d-flex">

            <RecruiterSidebar />

            <div
                className="flex-grow-1"
                style={{
                    background: "#f5f7fb",
                    minHeight: "100vh",
                    marginLeft: "260px"
                }}
            >

                {/* Fixed Navbar */}
                <RecruiterNavbar />

                {/* Scrollable Content */}
                <div
                    className="p-4"
                    style={{
                        marginTop: "90px"
                    }}
                >

                    <Outlet />

                </div>

            </div>

        </div>

    );

}

export default RecruiterLayout;