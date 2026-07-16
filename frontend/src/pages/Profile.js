import { useEffect, useState } from "react";
import { getLoggedInStudent } from "../services/StudentService";

function Profile() {

    const [student, setStudent] = useState(null);

    useEffect(() => {
        loadStudent();
    }, []);

    const loadStudent = async () => {

        try {

            const response = await getLoggedInStudent();

            setStudent(response.data);

        }

        catch (error) {

            console.log(error);

        }

    };

    if (!student) {

        return <h4>Loading Profile...</h4>;

    }

    return (

        <div className="container-fluid">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>Student Profile</h3>

                </div>

                <div className="card-body">

                    <div className="row">

                        <div className="col-md-6 mb-3">

                            <strong>Name</strong>

                            <p>{student.name}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Email</strong>

                            <p>{student.email}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Phone</strong>

                            <p>{student.phone}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Branch</strong>

                            <p>{student.branch}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Graduation Year</strong>

                            <p>{student.graduationYear}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Primary Language</strong>

                            <p>{student.primaryLanguage}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>CGPA</strong>

                            <p>{student.cgpa}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Skill</strong>

                            <p>{student.skill}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>10th %</strong>

                            <p>{student.tenthPercentage}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>12th %</strong>

                            <p>{student.twelfthPercentage}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Current Backlogs</strong>

                            <p>{student.currentBacklogs}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Total Backlogs</strong>

                            <p>{student.totalBacklogs}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>Placement Status</strong>

                            <p>

                                {student.isPlaced ? "Placed ✅" : "Not Placed"}

                            </p>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default Profile;