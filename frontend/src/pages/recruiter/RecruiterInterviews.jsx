import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { FaPlus } from "react-icons/fa";

import InterviewTable from "../../components/recruiter/InterviewTable";
import RecruiterInterviewModal from "../../components/recruiter/RecruiterInterviewModal";

import {
    getRecruiterInterviews
} from "../../services/recruiterInterviewService";

function RecruiterInterviews() {

    const [interviews, setInterviews] = useState([]);

    const [showModal, setShowModal] = useState(false);

    const [selectedInterview, setSelectedInterview] = useState(null);

    useEffect(() => {

        loadInterviews();

    }, []);

    const loadInterviews = async () => {

        try {

            const data = await getRecruiterInterviews();

            setInterviews(data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2 className="fw-bold">

                    Interview Management

                </h2>

                <Button
                    onClick={() => {

                        setSelectedInterview(null);

                        setShowModal(true);

                    }}
                >

                    <FaPlus className="me-2" />

                    Schedule Interview

                </Button>

            </div>

            <InterviewTable

                interviews={interviews}

                reload={loadInterviews}

                onEdit={(interview) => {

                    setSelectedInterview(interview);

                    setShowModal(true);

                }}

            />

            <RecruiterInterviewModal

                show={showModal}

                handleClose={() => setShowModal(false)}

                interview={selectedInterview}

                reload={loadInterviews}

            />

        </>

    );

}

export default RecruiterInterviews;