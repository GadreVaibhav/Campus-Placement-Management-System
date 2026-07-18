import { useEffect, useState } from "react";

import {
    FaBuilding,
    FaBriefcase,
    FaClipboardList,
    FaCalendarCheck
} from "react-icons/fa";

import DashboardCard from "../../components/admin/DashboardCard";

import RecentApplications from "../../components/recruiter/RecentApplications";
import UpcomingInterviews from "../../components/recruiter/UpcomingInterviews";
import RecentPlacementDrives from "../../components/recruiter/RecentPlacementDrives";

function RecruiterDashboard() {

    const [dashboard, setDashboard] = useState({

        totalDrives: 0,

        activeJobs: 0,

        totalApplications: 0,

        totalInterviews: 0

    });

    useEffect(() => {

        loadDashboard();

    }, []);

    const loadDashboard = async () => {

        /*
            Backend API will be connected here later.

            Example:

            const response = await getRecruiterDashboard();

            setDashboard(response.data);
        */

    };

    return (

        <>

            <h2 className="fw-bold mb-4">

                Recruiter Dashboard

            </h2>

            <div className="row">

                <DashboardCard
                    title="Placement Drives"
                    value={dashboard.totalDrives}
                    icon={<FaBuilding />}
                    color="#0d6efd"
                />

                <DashboardCard
                    title="Active Jobs"
                    value={dashboard.activeJobs}
                    icon={<FaBriefcase />}
                    color="#198754"
                />

                <DashboardCard
                    title="Applications"
                    value={dashboard.totalApplications}
                    icon={<FaClipboardList />}
                    color="#ffc107"
                />

                <DashboardCard
                    title="Interviews"
                    value={dashboard.totalInterviews}
                    icon={<FaCalendarCheck />}
                    color="#dc3545"
                />

            </div>

            <RecentApplications />

            <UpcomingInterviews />

            <RecentPlacementDrives />

        </>

    );

}

export default RecruiterDashboard;