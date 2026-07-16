import AdminSidebar from "./AdminSidebar";
import AdminNavbar from "./AdminNavbar";
import { Outlet } from "react-router-dom";

function AdminLayout() {

    return (

        <div className="d-flex">

            <AdminSidebar />

            <div
                className="flex-grow-1"
                style={{
                    marginLeft: "260px",
                    height: "100vh",
                    overflowY: "auto",
                    background: "#f5f7fb"
                }}
            >

                <AdminNavbar />

                <div className="p-4">

                    <Outlet />

                </div>

            </div>

        </div>

    );

}

export default AdminLayout;