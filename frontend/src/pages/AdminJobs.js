import { useEffect, useState } from "react";

import {
    getAllJobs,
    searchJobs,
    deleteJob,
    updateJob,
    createJob
} from "../services/JobManagementService";

import { getAllCompanies } from "../services/CompanyManagementService";

import { getAllRecruiters } from "../services/RecruiterManagementService";

import { Modal, Button } from "react-bootstrap";
import { toast } from "react-toastify";
import {
    confirmDelete,
    successAlert,
    errorAlert
} from "../utils/alert";


function AdminJobs() {

    const [jobs, setJobs] = useState([]);
    const [companies, setCompanies] = useState([]);

const [recruiters, setRecruiters] = useState([]);

const [editingJob, setEditingJob] = useState(null);
const [isAddMode, setIsAddMode] = useState(false);

const [formData, setFormData] = useState({

    jobTitle: "",

    description: "",

    packageLpa: "",

    location: "",

    eligibilityCgpa: "",

    skillsRequired: "",

    lastDate: "",

    status: "",

    companyId: "",

    recruiterId: ""

});

    const [page, setPage] = useState(0);

    const [totalPages, setTotalPages] = useState(0);

    const [jobTitle, setJobTitle] = useState("");

    const [location, setLocation] = useState("");

    const [status, setStatus] = useState("");

    const [eligibilityCgpa, setEligibilityCgpa] = useState("");

   useEffect(() => {

    loadJobs();

    loadCompanies();

    loadRecruiters();

}, [page]);
    const loadJobs = async () => {

        try {

            const response = await getAllJobs(page);

            setJobs(response.data.content);

            setTotalPages(response.data.totalPages);

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

const loadRecruiters = async () => {

    try {

        const response = await getAllRecruiters();

        setRecruiters(response.data);

    }

    catch (error) {

        console.error(error);

    }

};

    const handleSearch = async () => {

        try {

            const response = await searchJobs(

                jobTitle,

                location,

                status,

                null,

                null,

                eligibilityCgpa === "" ? null : eligibilityCgpa

            );

            setJobs(response.data.content);

        }

        catch (error) {

            console.error(error);

        }

    };

    const resetFilters = () => {

        setJobTitle("");

        setLocation("");

        setStatus("");

        setEligibilityCgpa("");

        setPage(0);

        loadJobs();

    };

    const handleDelete = async (id) => {

       const result = await confirmDelete();

        if (!result.isConfirmed) {
            return;
        }

        try {

            await deleteJob(id);

            successAlert("Job Deleted Successfully");

            loadJobs();

        }

        catch (error) {

            console.error(error);

            errorAlert("Delete Failed");

        }

    };

    const handleEdit = (job) => {

    setEditingJob(job);

    setFormData({

        jobTitle: job.jobTitle,

        description: job.description,

        packageLpa: job.packageLpa,

        location: job.location,

        eligibilityCgpa: job.eligibilityCgpa,

        skillsRequired: job.skillsRequired,

        lastDate: job.lastDate,

        status: job.status,

        companyId: job.companyId,

        recruiterId: job.recruiterId

    });

  };
  const handleAddJob = () => {

    setIsAddMode(true);

    setEditingJob({});

    setFormData({

        jobTitle: "",

        description: "",

        packageLpa: "",

        location: "",

        eligibilityCgpa: "",

        skillsRequired: "",

        lastDate: "",

        status: "OPEN",

        companyId: "",

        recruiterId: ""

    });

};

const handleUpdate = async () => {

    try {

        await updateJob(

            editingJob.id,

            formData

        );

        toast.success("Job Updated Successfully");

        setEditingJob(null);

        loadJobs();

    }

    catch (error) {

        console.error(error);

       toast.error("Something went wrong");

    }

};
const handleSave = async () => {

    try {

        if (isAddMode) {

            await createJob(formData);

            toast.success("Job Created Successfully");

        } else {

            await updateJob(editingJob.id, formData);

            toast.success("Job Updated Successfully");

        }

        setEditingJob(null);

        setIsAddMode(false);

        loadJobs();

    }

    catch (error) {

        console.error(error);

        toast.error("Something went wrong");

    }

};
    return (

        <div className="container mt-4">

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2>Job Management</h2>

                <button
                    className="btn btn-success"
                    onClick={handleAddJob}
                >
                    + Add Job
                </button>

            </div>

            <div className="row mb-4">

                <div className="col-md-3">

                    <input

                        className="form-control"

                        placeholder="Job Title"

                        value={jobTitle}

                        onChange={(e) => setJobTitle(e.target.value)}

                    />

                </div>

                <div className="col-md-2">

                    <input

                        className="form-control"

                        placeholder="Location"

                        value={location}

                        onChange={(e) => setLocation(e.target.value)}

                    />

                </div>

                <div className="col-md-2">

                    <input

                        className="form-control"

                        placeholder="Status"

                        value={status}

                        onChange={(e) => setStatus(e.target.value)}

                    />

                </div>

                <div className="col-md-2">

                   <input 
                        type="number" 
                        className="form-control" 
                        placeholder="CGPA" 
                        min="0"
                        max="10"
                        step="0.01"
                        value={eligibilityCgpa} 
                        onChange={(e) => {
                            const val = e.target.value;
                            if (val === "" || (Number(val) >= 0 && Number(val) <= 10)) {
                            setEligibilityCgpa(val);
                            }
                        }} 
                    />



                </div>

                <div className="col-md-3 d-flex gap-2">

                    <button
                        className="btn btn-primary w-100"
                        onClick={handleSearch}
                    >
                        Search
                    </button>

                    <button
                        className="btn btn-secondary w-100"
                        onClick={resetFilters}
                    >
                        Reset
                    </button>

                </div>

            </div>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">

                    <tr>

                        <th>ID</th>

                        <th>Title</th>

                        <th>Company</th>

                        <th>Recruiter</th>

                        <th>Location</th>

                        <th>CGPA</th>

                        <th>Status</th>

                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        jobs.map(job => (

                            <tr key={job.id}>

                                <td>{job.id}</td>

                                <td>{job.jobTitle}</td>

                                <td>{job.companyName}</td>

                                <td>{job.recruiterName}</td>

                                <td>{job.location}</td>

                                <td>{job.eligibilityCgpa}</td>

                                <td>{job.status}</td>

                                <td>

                                <div className="d-flex gap-2">

                                    <button
                                        className="btn btn-warning btn-sm"
                                        onClick={() => handleEdit(job)}
                                    >
                                        Edit
                                    </button>

                                    <button
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleDelete(job.id)}
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

            <div className="d-flex justify-content-between">

                <button

                    className="btn btn-secondary"

                    disabled={page === 0}

                    onClick={() => setPage(page - 1)}

                >

                    Previous

                </button>

                <span>

                    Page {page + 1} of {totalPages}

                </span>

                <button

                    className="btn btn-secondary"

                    disabled={page + 1 >= totalPages}

                    onClick={() => setPage(page + 1)}

                >

                    Next

                </button>

            </div>


<Modal
    show={editingJob !== null}
    onHide={() => setEditingJob(null)}
    size="lg"
>

    <Modal.Header closeButton>

        <Modal.Title>

           {isAddMode ? "Add Job" : "Edit Job"}

        </Modal.Title>

    </Modal.Header>

    <Modal.Body>

        <div className="row">

            <div className="col-md-6 mb-3">

                <label>Job Title</label>

                <input
                    className="form-control"
                    value={formData.jobTitle}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            jobTitle: e.target.value
                        })
                    }
                />

            </div>

            <div className="col-md-6 mb-3">

                <label>Location</label>

                <input
                    className="form-control"
                    value={formData.location}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            location: e.target.value
                        })
                    }
                />

            </div>

            <div className="col-md-6 mb-3">

                <label>Package (LPA)</label>

                <input
                    type="number"
                    className="form-control"
                    value={formData.packageLpa}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                           packageLpa: Number(e.target.value)
                        })
                    }
                />

            </div>

            <div className="col-md-6 mb-3">

                <label>Eligibility CGPA</label>

                <input
                    type="number"
                    className="form-control"
                    value={formData.eligibilityCgpa}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            eligibilityCgpa: Number(e.target.value)
                        })
                    }
                />

            </div>

            <div className="col-md-12 mb-3">

                <label>Description</label>

                <textarea
                    rows="3"
                    className="form-control"
                    value={formData.description}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            description: e.target.value
                        })
                    }
                />

            </div>

            <div className="col-md-12 mb-3">

                <label>Skills Required</label>

                <input
                    className="form-control"
                    value={formData.skillsRequired}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            skillsRequired: e.target.value
                        })
                    }
                />

            </div>

            <div className="col-md-6 mb-3">

                <label>Last Date</label>

                <input
                    type="date"
                    className="form-control"
                    value={formData.lastDate}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            lastDate: e.target.value
                        })
                    }
                />

            </div>

            <div className="col-md-6 mb-3">

                <label>Status</label>

                <select
                    className="form-select"
                    value={formData.status}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            status: e.target.value
                        })
                    }
                 >

                    <option value="OPEN">OPEN</option>

                    <option value="CLOSED">CLOSED</option>

                </select>

            </div>

            <div className="col-md-6 mb-3">

                <label>Company</label>

                     <select
                        className="form-select"
                        value={formData.companyId}
                        onChange={(e) =>
                            setFormData({
                                ...formData,
                                companyId: Number(e.target.value)
                            })
                        }
                     >
                        <option value="">Select Company</option>

                        {companies.map(company => (
                            <option
                                key={company.id}
                                value={company.id}
                            >
                                {company.companyName}
                            </option>
                        ))}
                    </select>

            </div>

            <div className="col-md-6 mb-3">

                <label>Recruiter</label>

                <select
                    className="form-select"
                    value={formData.recruiterId}
                    onChange={(e) =>
                        setFormData({
                            ...formData,
                            recruiterId: Number(e.target.value)
                        })
                    }
                 >
                    <option value="">Select Recruiter</option>

                    {recruiters.map(recruiter => (
                        <option
                            key={recruiter.id}
                            value={recruiter.id}
                        >
                            {recruiter.name}
                        </option>
                    ))}
                </select>

            </div>

        </div>

    </Modal.Body>

    <Modal.Footer>

        <Button
            variant="secondary"
            onClick={() => setEditingJob(null)}
        >

            Cancel

        </Button>

        <Button
            variant="primary"
            onClick={handleSave}
        >

            {isAddMode ? "Create Job" : "Update Job"}

        </Button>

    </Modal.Footer>

</Modal>
        </div>

        

    );

}

export default AdminJobs;