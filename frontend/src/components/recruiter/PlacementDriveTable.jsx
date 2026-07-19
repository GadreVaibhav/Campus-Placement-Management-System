import { Button, Table } from "react-bootstrap";
import { FaEdit, FaTrash } from "react-icons/fa";

import { deletePlacementDrive } from "../../services/PlacementDriveService";

function PlacementDriveTable({

    drives,

    reload,

    onEdit

}) {

    const handleDelete = async (id) => {

        const confirmDelete = window.confirm(
            "Are you sure you want to delete this placement drive?"
        );

        if (!confirmDelete) return;

        try {

    await deletePlacementDrive(id);

    alert("Placement Drive deleted successfully.");

    reload();

} catch (error) {

    if (error.response?.data?.message) {

        alert(error.response.data.message);

    } else {

        alert("Failed to delete placement drive.");

    }
}
    };

    return (

        <div className="card shadow border-0">

            <div className="card-body">

                <Table bordered hover responsive>

                    <thead className="table-primary">

                        <tr>

                            <th>ID</th>

                            <th>Company</th>

                            <th>Job Role</th>

                            <th>Package (LPA)</th>

                            <th>Minimum CGPA</th>

                            <th>Drive Date</th>

                            <th>Status</th>

                            <th width="150">Actions</th>

                        </tr>

                    </thead>

                    <tbody>

                        {drives.length === 0 ? (

                            <tr>

                                <td colSpan="8" className="text-center">

                                    No Placement Drives Found

                                </td>

                            </tr>

                        ) : (

                            drives.map((drive) => (

                                <tr key={drive.id}>

                                    <td>{drive.driveId}</td>

                                    <td>{drive.companyName}</td>

                                    <td>{drive.jobRole}</td>

                                    <td>{drive.packageOffered}</td>

                                    <td>{drive.minimumCgpa}</td>

                                    <td>{drive.driveDate}</td>

                                    <td>{drive.status}</td>

                                    <td>

                                        <Button

                                            size="sm"

                                            variant="warning"

                                            className="me-2"

                                            onClick={() => {
                                                console.log(drive);
                                                onEdit(drive);
                                            }}

                                        >

                                            <FaEdit />

                                        </Button>

                                        <Button

                                            size="sm"

                                            variant="danger"

                                           onClick={() => handleDelete(drive.id)}
                                        >

                                            <FaTrash />

                                        </Button>

                                    </td>

                                </tr>

                            ))

                        )}

                    </tbody>

                </Table>

            </div>

        </div>

    );

}

export default PlacementDriveTable;