import { useEffect, useState } from "react";
import { getLoggedInStudent } from "../../services/StudentService";

function DashboardCards() {

    const [student, setStudent] = useState(null);

    useEffect(() => {

        loadStudent();

    }, []);

    const loadStudent = async () => {

        try {

            const response = await getLoggedInStudent();

            setStudent(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    if (!student) {

        return null;

    }

    const cards = [

        {
            title: "CGPA",
            value: student.cgpa,
            color: "success",
            icon: "bi bi-mortarboard-fill"
        },

        {
            title: "Skill",
            value: student.skill,
            color: "primary",
            icon: "bi bi-code-slash"
        },

        {
            title: "Branch",
            value: student.branch,
            color: "warning",
            icon: "bi bi-diagram-3-fill"
        },

        {
            title: "Placement",
            value: student.isPlaced ? "Placed" : "Not Placed",
            color: student.isPlaced ? "success" : "danger",
            icon: "bi bi-briefcase-fill"
        }

    ];

    return (

        <div className="row">

            {

                cards.map((card, index) => (

                    <div className="col-md-3 mb-4" key={index}>

                        <div className={`card bg-${card.color} text-white shadow border-0`}>

                            <div className="card-body">

                                <div className="d-flex justify-content-between">

                                    <div>

                                        <h6>{card.title}</h6>

                                        <h4>{card.value}</h4>

                                    </div>

                                    <i
                                        className={card.icon}
                                        style={{ fontSize: "40px" }}
                                    ></i>

                                </div>

                            </div>

                        </div>

                    </div>

                ))

            }

        </div>

    );

}

export default DashboardCards;