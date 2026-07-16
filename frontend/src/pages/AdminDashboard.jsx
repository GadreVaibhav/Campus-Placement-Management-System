import { useEffect, useState } from "react";

import { getAdminDashboard } from "../services/dashboardService";
import DashboardCard from "../components/admin/DashboardCard";
import PlacementChart from "../components/admin/PlacementChart";
import RecentApplications from "../components/admin/RecentApplications";
import UpcomingDrives from "../components/admin/UpcomingDrives";
import RecentStudents from "../components/admin/RecentStudents";
import { getStudentsByBranch } from "../services/dashboardService";
import StudentsByBranchChart from "../components/admin/StudentsByBranchChart";
import {
    FaUserGraduate,
    FaBuilding,
    FaUserTie,
    FaBriefcase,
    FaClipboardList,
    FaCheckCircle,
    FaFileAlt,
    FaCalendarAlt
} from "react-icons/fa";
function AdminDashboard() {

    const [dashboard, setDashboard] = useState(null);
    const [branchData, setBranchData] = useState([]);

   useEffect(() => {

    loadDashboard();

    loadBranchData();

}, []);
    const loadDashboard = async () => {

        try {

            const response = await getAdminDashboard();

            setDashboard(response.data);

        } catch (error) {

            console.error(error);

            alert("Unable to load dashboard.");

        }

    };
   const loadBranchData = async () => {

    try {

        const response = await getStudentsByBranch();

        

        setBranchData(response.data);

    } catch (error) {

        console.error(error);

    }

};

   if (!dashboard) {

    return (

        <div className="text-center mt-5">

            <div className="spinner-border text-primary"></div>

            <h5 className="mt-3">
                Loading Dashboard...
            </h5>

        </div>

    );

}
    return (

    <>

        <h2 className="fw-bold mb-4">
            Admin Dashboard
        </h2>

       <div className="row">

                    <DashboardCard
                        title="Students"
                        value={dashboard.totalStudents}
                        icon={<FaUserGraduate />}
                        color="#0d6efd"
                    />

                    <DashboardCard
                        title="Companies"
                        value={dashboard.totalCompanies}
                        icon={<FaBuilding />}
                        color="#198754"
                    />

                    <DashboardCard
                        title="Recruiters"
                        value={dashboard.totalRecruiters}
                        icon={<FaUserTie />}
                        color="#ffc107"
                    />

                    <DashboardCard
                        title="Placement Drives"
                        value={dashboard.totalPlacementDrives}
                        icon={<FaBriefcase />}
                        color="#6f42c1"
                    />

                    <DashboardCard
                        title="Applications"
                        value={dashboard.totalApplications}
                        icon={<FaClipboardList />}
                        color="#dc3545"
                    />

                    <DashboardCard
                        title="Selected Students"
                        value={dashboard.selectedStudents}
                        icon={<FaCheckCircle />}
                        color="#20c997"
                    />

                    <DashboardCard
                        title="Documents"
                        value={dashboard.documentsUploaded}
                        icon={<FaFileAlt />}
                        color="#fd7e14"
                    />

                    <DashboardCard
                        title="Upcoming Drives"
                        value={dashboard.upcomingDrives}
                        icon={<FaCalendarAlt />}
                        color="#0dcaf0"
                    />

                    

        </div>

        
        
        <RecentApplications />
        <UpcomingDrives />
        <RecentStudents />
        <div className="mt-4">

            <StudentsByBranchChart  // to show student by branch 
                data={branchData}
            />

        </div>
        <PlacementChart dashboard={dashboard} />
        


    </>

);
}

export default AdminDashboard;