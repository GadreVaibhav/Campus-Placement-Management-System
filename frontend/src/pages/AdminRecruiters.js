import { useEffect, useState } from "react";

import {
    getAllRecruiters,
    deleteRecruiter,
    updateRecruiter
} from "../services/RecruiterManagementService";
import { getAllCompanies } from "../services/CompanyManagementService";
function AdminRecruiters() {

    const [recruiters, setRecruiters] = useState([]);

    const [editingRecruiter, setEditingRecruiter] = useState(null);
    const [companies, setCompanies] = useState([]);

    const [formData, setFormData] = useState({

        name: "",

        email: "",

        designation: "",

        phone: "",

        companyId: ""

    });

    useEffect(() => {

    loadRecruiters();

    loadCompanies();

}, []);

    const loadRecruiters = async () => {

        try {

            const response = await getAllRecruiters();

            setRecruiters(response.data);

        }

        catch (error) {

            console.error(error);

        }

    };

    const loadCompanies = async () => {

    try {

        const response = await getAllCompanies(0);

        setCompanies(response.data.content);

    }

    catch (error) {

        console.error(error);

    }

};

    const handleDelete = async (id) => {

    if (!window.confirm("Delete this recruiter?")) {

        return;

    }

    try {

        await deleteRecruiter(id);

        alert("Recruiter deleted successfully.");

        loadRecruiters();

    }

    catch (error) {

        console.error(error);

        alert("Delete failed.");

    }

};
const handleEdit = (recruiter) => {

    setEditingRecruiter(recruiter);

    setFormData({

        name: recruiter.name,

        email: recruiter.email,

        designation: recruiter.designation,

        phone: recruiter.phone,

        companyId: recruiter.companyId

    });

};
const handleUpdate = async () => {

    try {

        await updateRecruiter(

            editingRecruiter.id,

            formData

        );

        alert("Recruiter updated successfully.");

        setEditingRecruiter(null);

        loadRecruiters();

    }

    catch (error) {

        console.error(error);

        alert("Update failed.");

    }

};

    return (

    <div className="container mt-4">

        <h2 className="mb-4">

            Recruiter Management

        </h2>

        <table className="table table-bordered table-hover">

            <thead className="table-dark">

                <tr>

                    <th>ID</th>

                    <th>Name</th>

                    <th>Email</th>

                    <th>Designation</th>

                    <th>Phone</th>

                    <th>Company</th>

                    <th>Status</th>

                    <th>Actions</th>

                </tr>

            </thead>

            <tbody>

                {

                    recruiters.map((recruiter) => (

                        <tr key={recruiter.id}>

                            <td>{recruiter.id}</td>

                            <td>{recruiter.name}</td>

                            <td>{recruiter.email}</td>

                            <td>{recruiter.designation}</td>

                            <td>{recruiter.phone}</td>

                            <td>{recruiter.companyName}</td>

                            <td>

                                {

                                    recruiter.isActive ?

                                        <span className="badge bg-success">

                                            Active

                                        </span>

                                        :

                                        <span className="badge bg-danger">

                                            Inactive

                                        </span>

                                }

                            </td>

                            <td>

                                <div className="d-flex gap-2">

                                    <button

                                        className="btn btn-warning btn-sm"

                                        onClick={() => handleEdit(recruiter)}

                                    >

                                        Edit

                                    </button>

                                    <button

                                        className="btn btn-danger btn-sm"

                                        onClick={() => handleDelete(recruiter.id)}

                                    >

                                        Delete

                                    </button>

                                </div>

                            </td>

                        </tr>

                    ))

                }

            </tbody>

        </table>

        {
    editingRecruiter && (

        <div
            className="modal fade show"
            style={{
                display: "block",
                background: "rgba(0,0,0,.5)"
            }}
        >

            <div className="modal-dialog">

                <div className="modal-content">

                    <div className="modal-header">

                        <h5>Edit Recruiter</h5>

                        <button
                            className="btn-close"
                            onClick={() => setEditingRecruiter(null)}
                        ></button>

                    </div>

                    <div className="modal-body">

                        <input
                            className="form-control mb-2"
                            placeholder="Name"
                            value={formData.name}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    name: e.target.value
                                })
                            }
                        />

                        <input
                            className="form-control mb-2"
                            placeholder="Email"
                            value={formData.email}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    email: e.target.value
                                })
                            }
                        />

                        <input
                            className="form-control mb-2"
                            placeholder="Designation"
                            value={formData.designation}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    designation: e.target.value
                                })
                            }
                        />

                        <input
                            className="form-control mb-2"
                            placeholder="Phone"
                            value={formData.phone}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    phone: e.target.value
                                })
                            }
                        />

                        <select
                            className="form-select"
                            value={formData.companyId}
                            onChange={(e) =>
                                setFormData({
                                    ...formData,
                                    companyId: e.target.value
                                })
                            }
                        >

                            <option value="">
                                Select Company
                            </option>

                            {

                                companies.map(company => (

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

                    <div className="modal-footer">

                        <button
                            className="btn btn-secondary"
                            onClick={() => setEditingRecruiter(null)}
                        >

                            Cancel

                        </button>

                        <button
                            className="btn btn-success"
                            onClick={handleUpdate}
                        >

                            Update

                        </button>

                    </div>

                </div>

            </div>

        </div>

    )
}

    </div>

);

}

export default AdminRecruiters;