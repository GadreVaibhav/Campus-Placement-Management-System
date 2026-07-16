import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import { Outlet } from "react-router-dom";

function Layout() {

    return (

        <div className="d-flex">

            {/* Sidebar */}

            <Sidebar />

            {/* Main Content */}

            <div className="flex-grow-1">

                <Navbar />

                <div className="container-fluid p-4">

                    <Outlet />

                </div>

            </div>

        </div>

    );

}

export default Layout;