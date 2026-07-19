import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

import {
    scheduleInterview,
    updateInterview,
    getRecruiterApplications
} from "../../services/recruiterInterviewService";

function RecruiterInterviewModal({

    show,
    handleClose,
    interview,
    reload

}) {

    const [applications, setApplications] = useState([]);

    const [formData, setFormData] = useState({

        applicationId: "",

        interviewTime: "",

        interviewMode: "ONLINE",

        interviewerName: "",

        feedback: ""

    });

    useEffect(() => {

        if (show) {

            loadApplications();

        }

    }, [show]);

    useEffect(() => {

        if (interview) {

            setFormData({

                applicationId: interview.applicationId,

                interviewTime: interview.interviewTime
                    ? interview.interviewTime.substring(0, 16)
                    : "",

                interviewMode: interview.interviewMode,

                interviewerName: interview.interviewerName,

                feedback: interview.feedback || ""

            });

        } else {

            setFormData({

                applicationId: "",

                interviewTime: "",

                interviewMode: "ONLINE",

                interviewerName: "",

                feedback: ""

            });

        }

    }, [interview]);

    const loadApplications = async () => {

        try {

            const data = await getRecruiterApplications();

            setApplications(data);

        } catch (error) {

            console.error(error);

        }

    };

    const handleChange = (e) => {

        const { name, value } = e.target;

        setFormData({

            ...formData,

            [name]: value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if (interview) {

                await updateInterview(

                    interview.interviewId,

                    formData

                );

                alert("Interview updated successfully.");

            } else {

                await scheduleInterview(formData);

                alert("Interview scheduled successfully.");

            }

            reload();

            handleClose();

        } catch (error) {

            console.error(error);

            alert("Operation failed.");

        }

    };

    return (

        <Modal

            show={show}

            onHide={handleClose}

            centered

            size="lg"

        >

            <Modal.Header closeButton>

                <Modal.Title>

                    {interview

                        ? "Update Interview"

                        : "Schedule Interview"}

                </Modal.Title>

            </Modal.Header>

            <Form onSubmit={handleSubmit}>

                <Modal.Body>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Select Application

                        </Form.Label>

                        <Form.Select

                            name="applicationId"

                            value={formData.applicationId}

                            onChange={handleChange}

                            disabled={interview != null}

                            required

                        >

                            <option value="">

                                Select Application

                            </option>

                            {applications.map((app) => (

                                <option

                                    key={app.applicationId}

                                    value={app.applicationId}

                                >

                                    {app.studentName}

                                    {" - "}

                                    {app.jobRole}

                                    {" - "}

                                    {app.companyName}

                                </option>

                            ))}

                        </Form.Select>

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Interview Time

                        </Form.Label>

                        <Form.Control

                            type="datetime-local"

                            name="interviewTime"

                            value={formData.interviewTime}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Interview Mode

                        </Form.Label>

                        <Form.Select

                            name="interviewMode"

                            value={formData.interviewMode}

                            onChange={handleChange}

                        >

                            <option value="ONLINE">

                                ONLINE

                            </option>

                            <option value="OFFLINE">

                                OFFLINE

                            </option>

                        </Form.Select>

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Interviewer Name

                        </Form.Label>

                        <Form.Control

                            type="text"

                            name="interviewerName"

                            value={formData.interviewerName}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group>

                        <Form.Label>

                            Feedback

                        </Form.Label>

                        <Form.Control

                            as="textarea"

                            rows={3}

                            name="feedback"

                            value={formData.feedback}

                            onChange={handleChange}

                        />

                    </Form.Group>

                </Modal.Body>

                <Modal.Footer>

                    <Button

                        variant="secondary"

                        onClick={handleClose}

                    >

                        Cancel

                    </Button>

                    <Button type="submit">

                        {interview

                            ? "Update"

                            : "Schedule"}

                    </Button>

                </Modal.Footer>

            </Form>

        </Modal>

    );

}

export default RecruiterInterviewModal;