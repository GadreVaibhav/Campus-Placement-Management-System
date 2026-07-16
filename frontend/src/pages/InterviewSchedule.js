import { useEffect, useState } from "react";
import { getInterview } from "../services/InterviewService";
import InterviewCard from "../components/interview/InterviewCard";

function InterviewSchedule() {

    const [interview, setInterview] = useState(null);

    useEffect(() => {
        loadInterview();
    }, []);

    const loadInterview = async () => {

        try {

            // Temporary Application ID
            const applicationId = 1;

            const response = await getInterview(applicationId);

            setInterview(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                Interview Schedule
            </h2>

            {

                interview ?

                    <InterviewCard interview={interview} />

                    :

                    <div className="alert alert-info">

                        No interview scheduled yet.

                    </div>

            }

        </div>

    );

}

export default InterviewSchedule;