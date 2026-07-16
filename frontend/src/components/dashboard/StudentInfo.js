import { useEffect, useState } from "react";
import { getLoggedInStudent } from "../../services/StudentService";

function StudentInfo() {

    const [student, setStudent] = useState(null);

    const [loading, setLoading] = useState(true);

    const [error, setError] = useState("");

    useEffect(() => {

        loadStudent();

    }, []);

    const loadStudent = async () => {

        try {

            const response = await getLoggedInStudent();

            setStudent(response.data);

        } catch (err) {

            console.error(err);

            setError("Unable to load student information.");

        } finally {

            setLoading(false);

        }

    };

    if (loading) {

        return (
            <div className="alert alert-info">
                Loading student information...
            </div>
        );
    }

    if (error) {

        return (
            <div className="alert alert-danger">
                {error}
            </div>
        );
    }

    return (

        <div className="card shadow border-0 mb-4">

            <div className="card-header bg-primary text-white">

                <h4 className="mb-0">
                    Student Information
                </h4>

            </div>

            <div className="card-body">

                <div className="row">

                    <div className="col-md-6">

                        <p><strong>Name:</strong> {student.name}</p>

                        <p><strong>Email:</strong> {student.email}</p>

                        <p><strong>Phone:</strong> {student.phone}</p>

                        <p><strong>Branch:</strong> {student.branch}</p>

                        <p><strong>Graduation Year:</strong> {student.graduationYear}</p>

                    </div>

                    <div className="col-md-6">

                        <p><strong>Primary Language:</strong> {student.primaryLanguage}</p>

                        <p><strong>Skill:</strong> {student.skill}</p>

                        <p>
                            <strong>CGPA:</strong>

                            <span className="badge bg-success ms-2">

                                {student.cgpa}

                            </span>

                        </p>

                        <p>

                            <strong>Resume:</strong>

                            {

                                student.resumeUrl ?

                                    <span className="badge bg-success ms-2">

                                        Uploaded

                                    </span>

                                    :

                                    <span className="badge bg-danger ms-2">

                                        Not Uploaded

                                    </span>

                            }

                        </p>

                        <p>

                            <strong>Placement:</strong>

                            {

                                student.isPlaced ?

                                    <span className="badge bg-success ms-2">

                                        Placed

                                    </span>

                                    :

                                    <span className="badge bg-warning text-dark ms-2">

                                        Not Placed

                                    </span>

                            }

                        </p>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentInfo;