import React, { useState } from 'react';
import axios from 'axios';

function AdminDashboard() {
  const [job, setJob] = useState({ companyName: '', role: '', jobPackage: '' });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post('http://localhost:8082/api/jobs', job);
    alert("Job Drive Added!");
  };

  return (
    <div className="p-10">
      <h2 className="text-2xl font-bold mb-4">Add New Job Drive</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input 
          placeholder="Company Name" 
          className="border p-2 w-full"
          onChange={(e) => setJob({...job, companyName: e.target.value})} 
        />
        <input 
          placeholder="Role" 
          className="border p-2 w-full"
          onChange={(e) => setJob({...job, role: e.target.value})} 
        />
        <input 
          placeholder="Package (LPA)" 
          className="border p-2 w-full"
          onChange={(e) => setJob({...job, jobPackage: e.target.value})} 
        />
        <button type="submit" className="bg-green-600 text-white p-2 rounded">Add Job</button>
      </form>
    </div>
  );
}

export default AdminDashboard;