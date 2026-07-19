import { useEffect, useState } from "react";

import RecruiterApplicationsTable
from "../../components/recruiter/RecruiterApplicationsTable";

import {
    getRecruiterApplications
}
from "../../services/recruiterApplicationService";

function RecruiterApplications() {

    const [applications, setApplications] = useState([]);

    useEffect(() => {

        loadApplications();

    }, []);

    const loadApplications = async () => {

        try {

            const data =
            await getRecruiterApplications();

            setApplications(data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <>

            <h2 className="fw-bold mb-4">

                Applications

            </h2>

            <RecruiterApplicationsTable

                applications={applications}

            />

        </>

    );

}

export default RecruiterApplications;