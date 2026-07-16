import { Outlet } from "react-router-dom";
import StudentSidebar from "./StudentSidebar";
import StudentNavbar from "./StudentNavbar";

function StudentLayout() {
    return (
        <div className="d-flex">
            <StudentSidebar />

                <div
                    className="flex-grow-1"
                    style={{
                        background:"#f5f7fb",
                        minHeight:"100vh",
                        marginLeft:"260px"
                    }}
                >
                <StudentNavbar />

                <div className="p-4">
                    <Outlet />
                </div>
            </div>
        </div>
    );
}

export default StudentLayout;