import { Outlet } from "react-router-dom";

import AdminSidebar from "./AdminSidebar";
import AdminNavbar from "./AdminNavbar";

function AdminLayout() {

    return (

        <div className="d-flex">

            <AdminSidebar />

            <div
                className="flex-grow-1"
                style={{
                    background: "#f5f7fb",
                    minHeight: "100vh",
                    marginLeft: "260px"
                }}
            >

                <AdminNavbar />

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

export default AdminLayout;