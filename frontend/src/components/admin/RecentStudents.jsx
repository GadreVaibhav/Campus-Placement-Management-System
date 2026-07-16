import { useEffect, useState } from "react";
import { getRecentStudents } from "../../services/dashboardService";

function RecentStudents() {

    const [students, setStudents] = useState([]);

    useEffect(() => {
        loadStudents();
    }, []);

    const loadStudents = async () => {

        try {

            const response = await getRecentStudents();

            setStudents(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="card shadow-sm border-0 mt-4">

            <div className="card-header bg-primary text-white">

                <h5 className="mb-0">

                    Recent Registered Students

                </h5>

            </div>

            <div className="card-body">

                <table className="table table-hover">

                    <thead>

                    <tr>

                        <th>Name</th>

                        <th>Branch</th>

                        <th>CGPA</th>

                        <th>Skill</th>

                    </tr>

                    </thead>

                    <tbody>

                    {

                        students.map(student => (

                            <tr key={student.studentId}>

                                <td>{student.name}</td>

                                <td>{student.branch}</td>

                                <td>{student.cgpa}</td>

                                <td>{student.skill}</td>

                            </tr>

                        ))

                    }

                    </tbody>

                </table>

            </div>

        </div>

    );

}

export default RecentStudents;