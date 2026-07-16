import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer
} from "recharts";

function StudentsByBranchChart({ data }) {

    return (

        <div className="card shadow p-3">

            <h5 className="mb-3">
                Students by Branch
            </h5>

            <ResponsiveContainer width="100%" height={300}>

                <BarChart data={data}>

                    <CartesianGrid strokeDasharray="3 3" />

                    <XAxis dataKey="branch" />

                    <YAxis />

                    <Tooltip />

                    <Bar
                        dataKey="count"
                        fill="#0d6efd"
                    />

                </BarChart>

            </ResponsiveContainer>

        </div>

    );

}

export default StudentsByBranchChart;