import Sidebar from "../components/layout/Sidebar";
import Navbar from "../components/layout/Navbar";

import StudentInfo from "../components/dashboard/StudentInfo";
import DashboardCards from "../components/dashboard/StudentDashboardCards";
import LatestDrives from "../components/dashboard/LatestDrives";
import NotificationPanel from "../components/dashboard/NotificationPanel";
import QuickActions from "../components/dashboard/QuickActions";
import { useEffect, useState } from "react";
import { getStudentDashboard } from "../services/StudentDashboardService";
import StudentStatisticsCards
from "../components/dashboard/StudentStatisticsCards";
function StudentDashboard() {
    const [dashboard, setDashboard] = useState(null);
    useEffect(() => {

    loadDashboard();

}, []);
const loadDashboard = async () => {

    console.log("Loading Student Dashboard...");

    try {

        const response = await getStudentDashboard(1);

        console.log("API Success");

        console.log(response);

        console.log(response.data);

        setDashboard(response.data);

    } catch (error) {

        console.log("API Failed");

        console.log(error);

    }

};

    return (

        <div className="d-flex">

            <Sidebar />

            <div className="flex-grow-1 bg-light">

                <Navbar />

                <div className="container-fluid p-4">

                   <StudentInfo />

                    <DashboardCards />

                    <StudentStatisticsCards
                        dashboard={dashboard}
                    />

                    <div className="row mt-4">

                        <div className="col-lg-8">

                            <LatestDrives />

                        </div>

                        <div className="col-lg-4">

                            <NotificationPanel />

                        </div>

                    </div>

                    <div className="mt-4">

                        <QuickActions />

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentDashboard;