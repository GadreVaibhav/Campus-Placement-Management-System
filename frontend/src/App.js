import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";

import Profile from "./pages/Profile";
import StudentDashboard from "./pages/StudentDashboard";
import UploadResume from "./pages/UploadResume";
import PlacementDrives from "./pages/PlacementDrives";
import Applications from "./pages/Applications";

import EditProfile from "./pages/EditProfile";


import RecruiterDashboard from "./pages/RecruiterDashboard";
import RecruiterApplications from "./pages/RecruiterApplications";
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
                element={<PlacementDrives />}
            />

            <Route
                path="/student/applications"
                element={<Applications />}
            />

            <Route
                path="/student/documents"
                element={<UploadResume />}
            />

            <Route
                path="/student/profile/edit"
                element={<EditProfile />}
            />

        </Route>

        {/* Recruiter */}

         <Route
          element={
            <ProtectedRoute allowedRole="RECRUITER">
              <Layout />
            </ProtectedRoute>
          }
         >

          <Route path="/recruiter" element={<RecruiterDashboard />} />

          <Route
            path="/recruiter/applications"
            element={<RecruiterApplications />}
          />

          <Route
            path="/recruiter/interview/:applicationId"
            element={<ScheduleInterview />}
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

    </BrowserRouter>

  );

}

export default App;