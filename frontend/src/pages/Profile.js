import { useEffect, useState } from "react";
import { getLoggedInStudent } from "../services/StudentService";
import { useNavigate } from "react-router-dom";
function Profile() {

    const [student, setStudent] = useState(null);
    const navigate = useNavigate();

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
    <h3 className="mb-0">Student Profile</h3>
</div>


                <div className="card-body">

                    <div className="row">

                        <div className="col-md-6 mb-3">

                           <strong>
    <i className="bi bi-person-fill me-2 text-primary"></i>
    Name
</strong>

                           <p>{student.name || "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>
    <i className="bi bi-envelope-fill me-2 text-primary"></i>
    Email
</strong>

                            <p>{student.email || "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>
    <i className="bi bi-telephone-fill me-2 text-primary"></i>
    Phone
</strong>

                            <p>{student.phone || "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                           <strong>
    <i className="bi bi-mortarboard-fill me-2 text-primary"></i>
    Branch
</strong>

                        <p>{student.branch || "Not Provided"}</p>
                        </div>

                        <div className="col-md-6 mb-3">

                           <strong>
    <i className="bi bi-calendar-event-fill me-2 text-primary"></i>
    Graduation Year
</strong>

                            <p>{student.graduationYear || "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                           <strong>
    <i className="bi bi-translate me-2 text-primary"></i>
    Primary Language
</strong>

                            <p>{student.primaryLanguage || "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>
    <i className="bi bi-award-fill me-2 text-primary"></i>
    CGPA
</strong>

                            <p>{student.cgpa ?? "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>
    <i className="bi bi-code-slash me-2 text-primary"></i>
    Skills
</strong>
                           <p>{student.skill || "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                           <strong>
    <i className="bi bi-book-fill me-2 text-primary"></i>
    10th Percentage
</strong>

                           <p>{student.tenthPercentage ?? "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>
    <i className="bi bi-journal-bookmark-fill me-2 text-primary"></i>
    12th Percentage
</strong>

                            <p>{student.twelfthPercentage ?? "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                            <strong>
    <i className="bi bi-exclamation-circle-fill me-2 text-primary"></i>
    Current Backlogs
</strong>
                       <p>{student.currentBacklogs ?? "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                         <strong>
    <i className="bi bi-list-check me-2 text-primary"></i>
    Total Backlogs
</strong>

                           <p>{student.totalBacklogs ?? "Not Provided"}</p>

                        </div>

                        <div className="col-md-6 mb-3">

                           <strong>
    <i className="bi bi-briefcase-fill me-2 text-primary"></i>
    Placement Status
</strong>

                            <p>

                                {student.isPlaced ? "Placed ✅" : "Not Placed"}

                            </p>

                        </div>

                    </div>

                </div>


            </div>
            
                <div className="d-flex justify-content-end mt-4">

                    <button
                        className="btn btn-primary"
                        onClick={() => navigate("/student/edit-profile")}
                    >
                        <i className="bi bi-pencil-square me-2"></i>
                        Edit Profile
                    </button>

                </div>
        </div>
        

    );

}

export default Profile;