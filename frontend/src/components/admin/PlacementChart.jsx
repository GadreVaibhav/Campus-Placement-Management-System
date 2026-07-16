import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
} from "chart.js";

import { Bar } from "react-chartjs-2";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
);

function PlacementChart({ dashboard }) {

    const data = {
        labels: [
            "Students",
            "Companies",
            "Recruiters",
            "Drives",
            "Applications",
            "Selected"
        ],
        datasets: [
            {
                label: "Statistics",
                data: [
                    dashboard.totalStudents,
                    dashboard.totalCompanies,
                    dashboard.totalRecruiters,
                    dashboard.totalPlacementDrives,
                    dashboard.totalApplications,
                    dashboard.selectedStudents
                ],
                backgroundColor: [
                    "#0d6efd",
                    "#198754",
                    "#ffc107",
                    "#6f42c1",
                    "#dc3545",
                    "#20c997"
                ]
            }
        ]
    };

    const options = {
        responsive: true,
        plugins: {
            legend: {
                display: false
            },
            title: {
                display: true,
                text: "Placement Statistics"
            }
        }
    };

    return (
        <div className="card shadow-sm border-0 mt-4">
            <div className="card-body">
                <Bar data={data} options={options} />
            </div>
        </div>
    );
}

export default PlacementChart;