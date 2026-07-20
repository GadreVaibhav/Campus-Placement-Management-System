import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
import StudentDashboard from "./pages/StudentDashboard";
import PlacementDrive from "./pages/PlacementDrive";


import RecruiterLayout from "./components/recruiter/RecruiterLayout";
import RecruiterProfile from "./pages/recruiter/RecruiterProfile";
import RecruiterPlacementDrives from "./pages/recruiter/RecruiterPlacementDrives";
import RecruiterInterviews from "./pages/recruiter/RecruiterInterviews";
import RecruiterDashboard from "./pages/recruiter/RecruiterDashboard";
import RecruiterApplications from "./pages/recruiter/RecruiterApplications";
import RecruiterOffers from "./pages/recruiter/RecruiterOffers";

import EditProfile from "./pages/EditProfile";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Documents from "./pages/Documents";
import JobDetails from "./pages/JobDetails";
import MyApplications from "./pages/MyApplications";
import StudentInterviews from "./pages/StudentInterviews";
import StudentOffers from "./pages/StudentOffers";



import ScheduleInterview from "./pages/ScheduleInterview";

import AdminDashboard from "./pages/AdminDashboard";
import AdminStudents from "./pages/AdminStudents";
import AdminCompanies from "./pages/AdminCompanies";

import Layout from "./components/layout/Layout";
import AdminLayout from "./components/layout/AdminLayout";
import ProtectedRoute from "./routes/ProtectedRoute";

import PlacementDriveManagement from "./pages/PlacementDriveManagement";
import CreatePlacementDrive from "./pages/CreatePlacementDrive";
import EditPlacementDrive from "./pages/EditPlacementDrive";
import ApplicationManagement from "./pages/ApplicationManagement";
import InterviewManagement from "./pages/InterviewManagement";
import EditInterview from "./pages/EditInterview";
import AdminRecruiters from "./pages/AdminRecruiters";
import AdminJobs from "./pages/AdminJobs";

import StudentLayout from "./components/student/StudentLayout";
function App() {

  return (

    <BrowserRouter>

      <Routes>

        {/* Public Routes */}

       <Route path="/" element={<Login />} />

<Route path="/login" element={<Login />} />

<Route path="/register" element={<Register />} />

        {/* Student Routes */}
        <Route
            element={
                <ProtectedRoute allowedRole="STUDENT">
                    <StudentLayout />
                </ProtectedRoute>
            }
        >

            <Route
                path="/student"
                element={<Navigate to="/student/dashboard" replace />}
            />

            <Route
                path="/student/dashboard"
                element={<StudentDashboard />}
            />

            <Route
                path="/student/profile"
                element={<Profile />}
            />

           <Route
                path="/student/jobs"
                element={<PlacementDrive />}
            />

            <Route
                path="/student/interviews"
                element={<StudentInterviews />}
            />

        

            <Route
                path="/student/profile/edit"
                element={<EditProfile />}
            />

            <Route path="/student/edit-profile" element={<EditProfile />} />

             <  Route path="/student/documents" element={<Documents />} />

             <Route
                    path="/student/jobs/:id"
                    element={<JobDetails />}
            />

            <Route

                path="/student/my-applications"

                element={<MyApplications />}

            />

                     <Route
                        path="/student/offers"
                        element={<StudentOffers />}
                    />

        </Route>

            {/* Recruiter routs */}
            
               <Route
                    element={
                        <ProtectedRoute allowedRole="RECRUITER">
                            <RecruiterLayout />
                        </ProtectedRoute>
                    }
                >

                    <Route
                        path="/recruiter"
                        element={<Navigate to="/recruiter/dashboard" replace />}
                    />

                    <Route
                        path="/recruiter/dashboard"
                        element={<RecruiterDashboard />}
                    />

                    <Route
                        path="/recruiter/profile"
                        element={<RecruiterProfile />}
                    />

                    <Route
                        path="/recruiter/drives"
                        element={<RecruiterPlacementDrives />}
                    />

                    <Route
                        path="/recruiter/interviews"
                        element={<RecruiterInterviews />}
                    />

                    <Route
                        path="/recruiter/applications"
                        element={<RecruiterApplications />}
                    />

                    <Route
                        path="/recruiter/offers"
                        element={<RecruiterOffers />}
                    />

                </Route>

      

        {/* Admin */}

        <Route
            element={
              <ProtectedRoute allowedRole="ADMIN">
                <AdminLayout />
              </ProtectedRoute>
            }
          >
          <Route
            path="/admin"
            element={<Navigate to="/admin/dashboard" replace />}
          />

          <Route
              path="/admin/dashboard"
              element={<AdminDashboard />}
          />

          <Route
              path="/admin/students"
              element={<AdminStudents />}
          />

          <Route
              path="/admin/companies"
              element={<AdminCompanies />}
          />

          <Route
              path="/admin/drives"
              element={<PlacementDriveManagement />}
          />

          <Route
              path="/admin/drives/create"
              element={<CreatePlacementDrive />}
          />

          <Route
              path="/admin/drives/edit/:id"
              element={<EditPlacementDrive />}
          />

          <Route
              path="/admin/applications"
              element={<ApplicationManagement />}
          />

          <Route

              path="/admin/interviews"

              element={<InterviewManagement />}

          />

          <Route
              path="/admin/interviews/edit/:id"
              element={<EditInterview />}
          />

          <Route
              path="/admin/recruiters"
              element={<AdminRecruiters />}
          />

          <Route
              path="/admin/jobs"
              element={<AdminJobs />}
          />

        </Route>

      </Routes>

      <>
    {/* Your Routes */}


    <ToastContainer
        position="top-right"
        autoClose={3000}
    />
</>

    </BrowserRouter>

    

  );

}

export default App;