import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Drives() {
    const [drives, setDrives] = useState([]);

    useEffect(() => {
        // Fetching the drives from your Spring Boot API
        
        axios.get('http://localhost:8082/api/drives/all')
            .then(response => {
                setDrives(response.data);
            })
            .catch(error => {
                console.error("Error fetching drives:", error);
            });
    }, []);

    return (
        <div style={{ padding: '20px' }}>
            <h2>Active Placement Drives</h2>
            <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))', gap: '20px' }}>
                {drives.map(drive => (
                    <div key={drive.id} style={{ border: '1px solid #ccc', padding: '15px', borderRadius: '8px' }}>
                        <h3>{drive.companyName || "Unknown Company"}</h3>
                        <p><strong>Role:</strong> {drive.jobRole}</p>
                        <p><strong>Package:</strong> {drive.packageOffered} LPA</p>
                        <p><strong>Min CGPA:</strong> {drive.minimumCgpa}</p>
                        <p><strong>Deadline:</strong> {drive.deadline}</p>
                        <button onClick={() => alert('Applied to ' + drive.jobRole)}>Apply Now</button>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Drives;