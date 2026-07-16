function DashboardCard({

    title,

    value,

    icon,

    color

}) {

    return (

        <div className="col-lg-3 col-md-6 mb-4">

            <div
                className="card border-0 shadow-sm h-100"
                style={{
                    borderRadius: "15px"
                }}
            >

                <div className="card-body">

                    <div className="d-flex justify-content-between align-items-center">

                        <div>

                            <h6 className="text-muted">
                                {title}
                            </h6>

                            <h2 className="fw-bold">
                                {value}
                            </h2>

                        </div>

                        <div
                            className="text-white d-flex justify-content-center align-items-center"
                            style={{
                                width: "60px",
                                height: "60px",
                                borderRadius: "50%",
                                background: color,
                                fontSize: "28px"
                            }}
                        >

                            {icon}

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default DashboardCard;