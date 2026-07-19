import { useEffect, useState } from "react";
import { getRecentPlacementDrives } from "../../services/recruiterPlacementDriveService";

function RecentPlacementDrives() {

    const [drives, setDrives] = useState([]);

    useEffect(() => {
        loadDrives();
    }, []);

    const loadDrives = async () => {

        try {

            const data = await getRecentPlacementDrives();

            setDrives(data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="card shadow border-0 mt-4">

            <div className="card-header bg-warning">

                <h5 className="mb-0">

                    Recent Placement Drives

                </h5>

            </div>

            <div className="card-body">

                <table className="table table-hover">

                    <thead>

                        <tr>

                            <th>Company</th>

                            <th>Job Role</th>

                            <th>Drive Date</th>

                            <th>Status</th>

                        </tr>

                    </thead>

                    <tbody>

                        {drives.map((drive) => (

                            <tr key={drive.driveId}>

                                <td>{drive.companyName}</td>

                                <td>{drive.jobRole}</td>

                                <td>{drive.driveDate}</td>

                                <td>{drive.status}</td>

                            </tr>

                        ))}

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default RecentPlacementDrives;