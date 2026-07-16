import { useEffect, useState } from "react";
import {
    getAllStudents,
    searchStudents,
    deleteStudent,
    updateStudent
} from "../services/StudentManagementService";

function AdminStudents() {

    const [students, setStudents] = useState([]);
    const [name, setName] = useState("");
    const [branch, setBranch] = useState("");
    const [skill, setSkill] = useState("");
    const [cgpa, setCgpa] = useState("");
    const [graduationYear, setGraduationYear] = useState("");
    const [placed, setPlaced] = useState("");
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [editingStudent, setEditingStudent] = useState(null);

    const [formData, setFormData] = useState({

        name: "",

        email: "",

        branch: "",

        cgpa: "",

        skill: "",

        graduationYear: ""

    });

   useEffect(() => {

    loadStudents();

    }, [page]);

    const loadStudents = async () => {

        try {

            const response = await getAllStudents(page);

            setStudents(response.data.content);

            setTotalPages(response.data.totalPages);

        } catch (error) {

            console.error(error);

        }

    };

    const handleSearch = async () => {

    try {

      const response = await searchStudents(

    name,
    branch,
    skill,
    cgpa,
    graduationYear,
    placed

);

        setStudents(response.data.content);

    } catch (error) {

        console.error(error);

    }

};

const resetFilters = () => {

    setName("");
    setBranch("");
    setSkill("");
    setCgpa("");
    setGraduationYear("");
    setPlaced("");

    setPage(0);

    loadStudents();

};

const handleEdit = (student) => {

    setEditingStudent(student);

    setFormData({

        name: student.name,

        email: student.email,

        branch: student.branch,

        cgpa: student.cgpa,

        skill: student.skill,

        graduationYear: student.graduationYear

    });

};

const handleUpdate = async () => {

    try {

        await updateStudent(

            editingStudent.studentId,

            formData

        );

        alert("Student updated successfully.");

        setEditingStudent(null);

        loadStudents();

    }

    catch (error) {

        console.error(error);

        alert("Update failed.");

    }

};

    const handleDelete = async (studentId) => {

        if (!window.confirm("Delete this student?")) {

            return;

        }

        try {

            await deleteStudent(studentId);

            alert("Student deleted successfully.");

            loadStudents();

        } catch (error) {

            console.error(error);

            alert("Failed to delete student.");

        }

    };

    return (

        <div className="container mt-4">

            <h2 className="mb-4">

                Student Management

            </h2>

            <div className="row mb-4 align-items-end">

                {/* Name */}
                <div className="col-md-2">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                    />
                </div>

                {/* Branch */}
                <div className="col-md-2">
                    <select
                        className="form-select"
                        value={branch}
                        onChange={(e) => setBranch(e.target.value)}
                    >
                        <option value="">All Branches</option>
                        <option value="CSE">CSE</option>
                        <option value="AIML">AIML</option>
                        <option value="AIDS">AIDS</option>
                        <option value="CIVIL">CIVIL</option>
                        <option value="Mechanical">Mechanical</option>
                        <option value="Electrical">Electrical</option>
                    </select>
                </div>

                {/* Skill */}
                <div className="col-md-2">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Skill"
                        value={skill}
                        onChange={(e) => setSkill(e.target.value)}
                    />
                </div>

                {/* CGPA */}
                <div className="col-md-1">
                     <input
                        type="number"
                        className="form-control"
                        placeholder="CGPA"
                        value={cgpa}
                        min="0"
                        max="10"
                        step="0.1"
                        onChange={(e) => setCgpa(e.target.value)}
                    />
                </div>
                 {/* Year */}
                <div className="col-md-2">

                   <input
                        type="number"
                        className="form-control"
                        placeholder="Graduation Year"
                        value={graduationYear}
                        min="2023"
                        max="2035"
                        step="1"
                        onChange={(e) => setGraduationYear(e.target.value)}
                    />
                </div>

                {/* Placement */}
                <div className="col-md-1">
                    <select
                        className="form-control"
                        value={placed}
                        onChange={(e) => setPlaced(e.target.value)}
                    >
                        <option value="">All</option>
                        <option value="true">Placed</option>
                        <option value="false">Not Placed</option>
                    </select>
                </div>

                {/* Buttons */}
                <div className="col-md-2 d-flex gap-2">

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
                        <th>Name</th>
                        <th>Email</th>
                        <th>Branch</th>
                        <th>CGPA</th>
                        <th>Placed</th>
                        <th>Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        students.map(student => (

                            <tr key={student.studentId}>

                                <td>{student.studentId}</td>

                                <td>{student.name}</td>

                                <td>{student.email}</td>

                                <td>{student.branch}</td>

                                <td>{student.cgpa}</td>

                                <td>

                                    {

                                        student.isPlaced ?

                                            <span className="badge bg-success">

                                                Yes

                                            </span>

                                            :

                                            <span className="badge bg-danger">

                                                No

                                            </span>

                                    }

                                </td>

                                <td>

                                    <div className="d-flex gap-2">

                                        <button

                                            className="btn btn-warning btn-sm"

                                            onClick={() => handleEdit(student)}

                                        >

                                            Edit

                                        </button>

                                        <button

                                            className="btn btn-danger btn-sm"

                                            onClick={() =>
                                                handleDelete(student.studentId)
                                            }

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
            <div className="d-flex justify-content-between mt-3">

                <button
                    className="btn btn-secondary"
                    disabled={page === 0}
                    onClick={() => setPage(page - 1)}
                >
                    Previous
                </button>

                <span className="align-self-center">

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
                                    {
                        editingStudent && (

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

                        <h5>Edit Student</h5>

                        <button
                        className="btn-close"
                        onClick={() => setEditingStudent(null)}
                        ></button>

                        </div>

                        <div className="modal-body">

                        <input
                        className="form-control mb-2"
                        placeholder="Name"
                        value={formData.name}
                        onChange={(e)=>
                        setFormData({
                        ...formData,
                        name:e.target.value
                        })
                        }
                        />

                        <input
                        className="form-control mb-2"
                        placeholder="Email"
                        value={formData.email}
                        onChange={(e)=>
                        setFormData({
                        ...formData,
                        email:e.target.value
                        })
                        }
                        />

                        <input
                        className="form-control mb-2"
                        placeholder="Branch"
                        value={formData.branch}
                        onChange={(e)=>
                        setFormData({
                        ...formData,
                        branch:e.target.value
                        })
                        }
                        />

                        <input
                        className="form-control mb-2"
                        placeholder="CGPA"
                        value={formData.cgpa}
                        onChange={(e)=>
                        setFormData({
                        ...formData,
                        cgpa:e.target.value
                        })
                        }
                        />

                        <input
                        className="form-control mb-2"
                        placeholder="Skill"
                        value={formData.skill}
                        onChange={(e)=>
                        setFormData({
                        ...formData,
                        skill:e.target.value
                        })
                        }
                        />

                        <input
                        className="form-control"
                        placeholder="Graduation Year"
                        value={formData.graduationYear}
                        onChange={(e)=>
                        setFormData({
                        ...formData,
                        graduationYear:e.target.value
                        })
                        }
                        />

                        </div>

                        <div className="modal-footer">

                        <button
                        className="btn btn-secondary"
                        onClick={() => setEditingStudent(null)}
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

export default AdminStudents;