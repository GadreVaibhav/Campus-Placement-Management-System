import { useEffect, useState } from "react";

import {
    FaBuilding,
    FaBriefcase,
    FaClipboardList,
    FaCalendarCheck
} from "react-icons/fa";

import DashboardCard from "../../components/admin/DashboardCard";
import { getRecruiterDashboard } from "../../services/recruiterDashboardService";
import RecentApplications from "../../components/recruiter/RecentApplications";
import UpcomingInterviews from "../../components/recruiter/UpcomingInterviews";
import RecentPlacementDrives from "../../components/recruiter/RecentPlacementDrives";
import UpcomingPlacementDrives from "../../components/recruiter/UpcomingPlacementDrives";
function RecruiterDashboard() {

    const [dashboard, setDashboard] = useState({

    totalDrives: 0,

    activeDrives: 0,

    totalApplications: 0,

    scheduledInterviews: 0

});

    useEffect(() => {

        loadDashboard();

    }, []);

   const loadDashboard = async () => {

    try {

        const data = await getRecruiterDashboard();

        console.log(data);

        setDashboard(data);

    } catch (error) {

        console.error(error);

    }

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
                    title="Active Drives"
                    value={dashboard.activeDrives}
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
                    value={dashboard.scheduledInterviews}
                    icon={<FaCalendarCheck />}
                    color="#dc3545"
                />

            </div>

            <RecentApplications />

            <UpcomingInterviews />

            <RecentPlacementDrives />

            <UpcomingPlacementDrives />
            

        </>

    );

}

export default RecruiterDashboard;