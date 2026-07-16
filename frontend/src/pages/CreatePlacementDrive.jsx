import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { createPlacementDrive } from "../services/PlacementDriveService";
import { getAllCompanies } from "../services/CompanyManagementService";

function CreatePlacementDrive() {

    const navigate = useNavigate();
    const [companies, setCompanies] = useState([]);
    

    const [drive, setDrive] = useState({

        companyId: "",

        jobRole: "",

        packageOffered: "",

        minimumCgpa: "",

        driveDate: "",

        registrationDeadline: "",

        status: "OPEN"

    });
    useEffect(() => {

    loadCompanies();

}, []);

const loadCompanies = async () => {

    try {

        const response = await getAllCompanies();

        setCompanies(response.data.content);

    } catch (error) {

        console.error(error);

    }

};
    const handleChange = (e) => {

        setDrive({

            ...drive,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

    e.preventDefault();

    try {

        await createPlacementDrive(drive);

        alert("Placement Drive Created Successfully");

        navigate("/admin/drives");

    }

    catch (error) {

        console.error(error);

        alert("Unable to create placement drive.");

    }

};
    
    
    return (

        <div className="container mt-4">

            <h2 className="mb-4">

                Create Placement Drive

            </h2>

            <form onSubmit={handleSubmit}>

               <div className="mb-3">

                    <label>Company</label>

                    <select
                        className="form-select"
                        name="companyId"
                        value={drive.companyId}
                        onChange={handleChange}
                        required
                    >

                        <option value="">

                            Select Company

                        </option>

                        {

                            companies.map((company) => (

                                <option
                                    key={company.id}
                                    value={company.id}
                                >

                                    {company.companyName}

                                </option>

                            ))

                        }

                    </select>

                </div>

                <div className="mb-3">

                    <label>Job Role</label>

                    <input
                        type="text"
                        className="form-control"
                        name="jobRole"
                        value={drive.jobRole}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Package Offered</label>

                    <input
                        type="number"
                        step="0.01"
                        className="form-control"
                        name="packageOffered"
                        value={drive.packageOffered}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Minimum CGPA</label>

                    <input
                        type="number"
                        step="0.01"
                        className="form-control"
                        name="minimumCgpa"
                        value={drive.minimumCgpa}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Drive Date</label>

                    <input
                        type="date"
                        className="form-control"
                        name="driveDate"
                        value={drive.driveDate}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Registration Deadline</label>

                    <input
                        type="date"
                        className="form-control"
                        name="registrationDeadline"
                        value={drive.registrationDeadline}
                        onChange={handleChange}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label>Status</label>

                    <select
                        className="form-select"
                        name="status"
                        value={drive.status}
                        onChange={handleChange}
                    >

                        <option value="OPEN">

                            OPEN

                        </option>

                        <option value="CLOSED">

                            CLOSED

                        </option>

                        <option value="UPCOMING">

                            UPCOMING

                        </option>

                    </select>

                </div>

                <button
                    className="btn btn-success"
                    type="submit"
                >

                    Update Placement Drive

                </button>

            </form>

        </div>

    );

}

export default CreatePlacementDrive;