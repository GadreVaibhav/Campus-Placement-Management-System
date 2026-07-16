import { useEffect, useState } from "react";
import { getAllPlacementDrives } from "../../services/PlacementDriveService";
import { applyForDrive } from "../../services/ApplicationService";
import { getLoggedInStudent } from "../../services/StudentService";
import { getApplicationsByStudent } from "../../services/StudentApplicationService";
function LatestPlacementDrives() {

    const [drives, setDrives] = useState([]);
const [appliedDrives, setAppliedDrives] = useState([]);

  useEffect(() => {

    loadDrives();

    loadApplications();

}, []);

    const loadDrives = async () => {

        try {

            const response = await getAllPlacementDrives();

            // Spring Page object
            setDrives(response.data.content);

        } catch (error) {

            console.error(error);

        }

    };
    const loadApplications = async () => {

    try {

        const student = await getLoggedInStudent();

        const studentId = student.data.studentId;

        const response =
            await getApplicationsByStudent(studentId);

        setAppliedDrives(

            response.data.map(app => app.placementDriveId)

        );

    } catch (error) {

        console.error(error);

    }

};
const handleApply = async (driveId) => {

    try {

        // Get logged-in student
        const studentResponse = await getLoggedInStudent();

        const studentId = studentResponse.data.studentId;

        await applyForDrive(studentId, driveId);

        // Update UI immediately
        setAppliedDrives(prev => [...prev, driveId]);

        alert("Application submitted successfully!");

    } catch (error) {

        console.error(error);

        if (
            error.response &&
            error.response.data &&
            error.response.data.message
        ) {

            alert(error.response.data.message);

        } else {

            alert("Failed to apply.");

        }

    }

};
    return (

        <div className="card shadow border-0 mt-4">

            <div className="card-header bg-dark text-white">

                <h5 className="mb-0">
                    Latest Placement Drives
                </h5>

            </div>

            <div className="card-body">

                <table className="table table-hover">

                                <thead>

                                    <tr>
                                <th>Company</th>
                                <th>Role</th>
                                <th>Package</th>
                                <th>Drive Date</th>
                                <th>Status</th>
                                <th>Action</th>
            </tr>

                                </thead>

                    <tbody>

                        {

                            drives.map((drive) => (

                                <tr key={drive.id}>

    <td>{drive.companyName}</td>

    <td>{drive.jobRole}</td>

    <td>{drive.packageOffered} LPA</td>

    <td>{drive.driveDate}</td>

    <td>
        <span className="badge bg-success">
            {drive.status}
        </span>
    </td>

   <td>

    {

        appliedDrives.includes(drive.id)

        ?

        <button
            className="btn btn-secondary btn-sm"
            disabled
        >
            ✓ Applied
        </button>

        :

        <button
            className="btn btn-success btn-sm"
            onClick={() => handleApply(drive.id)}
        >
            Apply
        </button>

    }

</td>

</tr>

                            ))

                        }

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default LatestPlacementDrives;