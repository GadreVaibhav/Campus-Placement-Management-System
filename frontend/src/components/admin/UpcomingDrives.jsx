import { useEffect, useState } from "react";
import { getUpcomingDrives } from "../../services/PlacementDriveService";

function UpcomingDrives() {

    const [drives, setDrives] = useState([]);

    useEffect(() => {
        loadDrives();
    }, []);

    const loadDrives = async () => {

        try {

            const response = await getUpcomingDrives();

            setDrives(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="card shadow-sm border-0 mt-4">

            <div className="card-header bg-success text-white">

                <h5 className="mb-0">
                    Upcoming Placement Drives
                </h5>

            </div>

            <div className="card-body">

                {

                    drives.length === 0 ?

                        (

                            <div className="text-center text-muted">

                                No Upcoming Placement Drives

                            </div>

                        )

                        :

                        (

                            <table className="table table-hover">

                                <thead>

                                    <tr>

                                        <th>Company</th>

                                        <th>Job Role</th>

                                        <th>Drive Date</th>

                                        <th>Registration Deadline</th>

                                        <th>Status</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    {

                                        drives.map((drive) => (

                                            <tr key={drive.id}>

                                                <td>{drive.companyName}</td>

                                                <td>{drive.jobRole}</td>

                                                <td>{drive.driveDate}</td>

                                                <td>{drive.registrationDeadline}</td>

                                                <td>

                                                    <span className="badge bg-success">

                                                        {drive.status}

                                                    </span>

                                                </td>

                                            </tr>

                                        ))

                                    }

                                </tbody>

                            </table>

                        )

                }

            </div>

        </div>

    );

}

export default UpcomingDrives;