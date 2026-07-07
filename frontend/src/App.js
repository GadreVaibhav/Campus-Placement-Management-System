import Drives from './Drives';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

// --- Admin Component ---
function AdminDashboard() {
  const [companies, setCompanies] = useState([]);
  const [driveData, setDriveData] = useState({
    companyId: '',
    jobRole: '',
    packageOffered: '',
    minimumCgpa: '',
    driveDate: '',
    registrationDeadline: '',
    status: 'LIVE' // Changed default to LIVE
  });

 useEffect(() => {
    const url = 'http://localhost:8082/api/companies';
    console.log("Attempting to fetch from:", url); // Check your console for this
    
    axios.get(url)
      .then(res => {
        console.log("Companies received:", res.data);
        setCompanies(res.data);
      })
      .catch(err => console.error("Error fetching companies", err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // UPDATED: Sending request to the new endpoint structure
    try {
      await axios.post(`http://localhost:8082/api/drives/company/${driveData.companyId}`, {
        jobRole: driveData.jobRole,
        packageOffered: driveData.packageOffered,
        minimumCgpa: driveData.minimumCgpa,
        driveDate: driveData.driveDate,
        registrationDeadline: driveData.registrationDeadline,
        status: driveData.status
      });
      alert("Placement Drive Added Successfully!");
    } catch (error) {
      console.error("Error adding drive", error);
      alert("Failed to add drive. Check console for details.");
    }
  };

  return (
    <div className="p-10 max-w-2xl mx-auto">
      <h2 className="text-2xl font-bold mb-6 text-gray-800">Create Placement Drive</h2>
      
      {companies.length === 0 ? (
        <div className="bg-yellow-100 p-4 rounded text-yellow-800 mb-4">
          <strong>Warning:</strong> No companies found. Please add a Company.
        </div>
      ) : null}

      <form onSubmit={handleSubmit} className="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div className="col-span-1 md:col-span-2">
          <label className="block text-sm font-bold text-gray-700">Select Company</label>
          <select 
            required
            className="border p-2 w-full rounded mt-1" 
            onChange={(e) => setDriveData({...driveData, companyId: e.target.value})}
          >
            <option value="">-- Choose a Company --</option>
            {companies.map(c => (
              <option key={c.id} value={c.id}>{c.companyName}</option>
            ))}
          </select>
        </div>

        <div>
          <label className="block text-sm font-bold text-gray-700">Job Role</label>
          <input required type="text" className="border p-2 w-full rounded mt-1" onChange={(e) => setDriveData({...driveData, jobRole: e.target.value})} />
        </div>

        <div>
          <label className="block text-sm font-bold text-gray-700">Package (LPA)</label>
          <input required type="number" step="0.1" className="border p-2 w-full rounded mt-1" onChange={(e) => setDriveData({...driveData, packageOffered: e.target.value})} />
        </div>

        <div>
          <label className="block text-sm font-bold text-gray-700">Min CGPA</label>
          <input required type="number" step="0.1" className="border p-2 w-full rounded mt-1" onChange={(e) => setDriveData({...driveData, minimumCgpa: e.target.value})} />
        </div>

        <div>
          <label className="block text-sm font-bold text-gray-700">Status</label>
          <select className="border p-2 w-full rounded mt-1" onChange={(e) => setDriveData({...driveData, status: e.target.value})}>
            <option value="LIVE">Live</option>
            <option value="UPCOMING">Upcoming</option>
            <option value="COMPLETED">Completed</option>
          </select>
        </div>

        <div>
          <label className="block text-sm font-bold text-gray-700">Drive Date</label>
          <input required type="date" className="border p-2 w-full rounded mt-1" onChange={(e) => setDriveData({...driveData, driveDate: e.target.value})} />
        </div>

        <div>
          <label className="block text-sm font-bold text-gray-700">Registration Deadline</label>
          <input required type="date" className="border p-2 w-full rounded mt-1" onChange={(e) => setDriveData({...driveData, registrationDeadline: e.target.value})} />
        </div>

        <div className="col-span-1 md:col-span-2 mt-4">
          <button type="submit" className="bg-green-600 text-white p-3 w-full rounded font-bold hover:bg-green-700 shadow-md">
            Publish Placement Drive
          </button>
        </div>
      </form>
    </div>
  );
}

// --- Main App Component ---
function App() {
  const [isAdmin, setIsAdmin] = useState(false);
  const [drives, setDrives] = useState([]);

  useEffect(() => {
    // Fetch all drives for the student view
    axios.get('http://localhost:8082/api/companies')
      .then(response => setDrives(response.data))
      .catch(error => console.error("Error fetching drives", error));
  }, []);
function App() {
  return (
    <div className="App">
      <h1>Welcome to Placement Portal</h1>
      <Drives /> {/* This tells React to display your drives list here */}
    </div>
  );
}
  return (
    <div className="min-h-screen bg-gray-50 pb-10">
      <nav className="p-4 bg-gray-900 text-white flex justify-center space-x-8 shadow-md">
        <button onClick={() => setIsAdmin(false)} className={`transition ${!isAdmin ? 'font-bold text-blue-400 underline' : 'hover:text-blue-200'}`}>Student Dashboard</button>
        <button onClick={() => setIsAdmin(true)} className={`transition ${isAdmin ? 'font-bold text-blue-400 underline' : 'hover:text-blue-200'}`}>Admin Portal</button>
      </nav>

      {isAdmin ? (
        <AdminDashboard />
      ) : (
        <div className="p-10 max-w-6xl mx-auto">
          <h1 className="text-4xl font-extrabold text-gray-800 mb-8 text-center">Active Placement Drives</h1>
          
          {drives.length === 0 ? (
            <p className="text-center text-gray-500">No active drives available right now.</p>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
              {drives.map(drive => (
                <div key={drive.id} className="bg-white rounded-xl shadow-lg border border-gray-100 overflow-hidden transform hover:-translate-y-1 transition duration-300">
                  <div className="bg-blue-600 p-4 text-white">
                    <h2 className="text-2xl font-bold">{drive.company?.companyName || "Unknown Company"}</h2>
                    <span className="inline-block bg-blue-800 text-xs px-2 py-1 rounded mt-2 font-semibold uppercase">{drive.status}</span>
                  </div>
                  <div className="p-6 space-y-3">
                    <p className="text-gray-700"><span className="font-semibold">Role:</span> {drive.jobRole}</p>
                    <p className="text-gray-700"><span className="font-semibold">Package:</span> {drive.packageOffered} LPA</p>
                    <p className="text-gray-700"><span className="font-semibold">Min CGPA:</span> {drive.minimumCgpa}</p>
                    <hr className="my-2" />
                    <p className="text-sm text-red-600 font-semibold">Deadline: {drive.registrationDeadline}</p>
                    <button className="mt-4 w-full bg-blue-50 text-blue-700 font-bold py-2 rounded border border-blue-200 hover:bg-blue-600 hover:text-white transition">
                      Apply Now
                    </button>
                  </div>
                </div>
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default App;