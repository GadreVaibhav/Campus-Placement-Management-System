import { useEffect, useState } from "react";
import {
    getAllPlacementDrives,
    deletePlacementDrive
} from "../services/PlacementDriveService";
import { useNavigate } from "react-router-dom";

function PlacementDriveManagement() {

    const [drives, setDrives] = useState([]);
    
    const [page, setPage] = useState(0);

    const [totalPages, setTotalPages] = useState(0);
    const navigate = useNavigate();
    useEffect(() => {

        loadDrives();

    }, [page]);

    const loadDrives = async () => {

        try {

            const response =
                await getAllPlacementDrives(page);

            setDrives(response.data.content);

            setTotalPages(response.data.totalPages);

        } catch (error) {

            console.error(error);

            alert("Unable to load placement drives.");

        }

    };

    const handleDelete = async (id) => {

        if (!window.confirm("Delete this placement drive?")) {

            return;

        }

        try {

            await deletePlacementDrive(id);

            loadDrives();

        } catch (error) {

            console.error(error);

            alert("Unable to delete drive.");

        }

    };

    return (

        <div className="container-fluid">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Placement Drive Management</h2>

                <button
                    className="btn btn-primary"
                    onClick={() => navigate("/admin/drives/create")}
                >

                    + Create Drive

                </button>

            </div>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>

                        <th>Company</th>

                        <th>Job Role</th>

                        <th>Package</th>

                        <th>Min CGPA</th>

                        <th>Drive Date</th>

                        <th>Deadline</th>

                        <th>Status</th>

                        <th>Action</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        drives.map((drive) => (

                            <tr key={drive.id}>

                                <td>{drive.id}</td>

                                <td>{drive.companyName}</td>

                                <td>{drive.jobRole}</td>

                                <td>{drive.packageOffered} LPA</td>

                                <td>{drive.minimumCgpa}</td>

                                <td>{drive.driveDate}</td>

                                <td>{drive.registrationDeadline}</td>

                                <td>

                                    <span
                                        className={
                                            drive.status === "OPEN"
                                                ? "badge bg-success"
                                                : "badge bg-danger"
                                        }
                                    >

                                        {drive.status}

                                    </span>

                                </td>

                                <td>
                                    <button
                                        className="btn btn-warning btn-sm me-2"
                                        onClick={() =>
                                            navigate(`/admin/drives/edit/${drive.id}`)
                                        }
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() =>
                                            handleDelete(drive.id)
                                        }
                                    >

                                        Delete

                                    </button>

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

            <div className="d-flex justify-content-between">

                <button
                    className="btn btn-secondary"
                    disabled={page === 0}
                    onClick={() => setPage(page - 1)}
                >

                    Previous

                </button>

                <h5>

                    Page {page + 1} of {totalPages}

                </h5>

                <button
                    className="btn btn-secondary"
                    disabled={page + 1 >= totalPages}
                    onClick={() => setPage(page + 1)}
                >

                    Next

                </button>

            </div>

        </div>

    );

}

export default PlacementDriveManagement;