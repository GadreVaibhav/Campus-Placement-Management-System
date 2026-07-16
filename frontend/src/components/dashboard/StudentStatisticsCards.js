import {
    FaClipboardList,
    FaBriefcase,
    FaCalendarCheck,
    FaCheckCircle
} from "react-icons/fa";

function StudentStatisticsCards({ dashboard }) {

    if (!dashboard) {

        return null;

    }

    const cards = [

        {
            title: "Applied Drives",
            value: dashboard.appliedDrives,
            color: "primary",
            icon: <FaClipboardList />
        },

        {
            title: "Upcoming Drives",
            value: dashboard.upcomingDrives,
            color: "warning",
            icon: <FaBriefcase />
        },

        {
            title: "Interviews",
            value: dashboard.interviews,
            color: "info",
            icon: <FaCalendarCheck />
        },

        {
            title: "Selected",
            value: dashboard.selectedDrives,
            color: "success",
            icon: <FaCheckCircle />
        }

    ];

    return (

        <div className="row mt-3">

            {

                cards.map((card, index) => (

                    <div
                        className="col-md-3 mb-4"
                        key={index}
                    >

                        <div className={`card border-0 shadow bg-${card.color} text-white`}>

                            <div className="card-body">

                                <div className="d-flex justify-content-between align-items-center">

                                    <div>

                                        <h6>{card.title}</h6>

                                        <h3>{card.value}</h3>

                                    </div>

                                    <div
                                        style={{ fontSize: "40px" }}
                                    >

                                        {card.icon}

                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>

                ))

            }

        </div>

    );

}

export default StudentStatisticsCards;