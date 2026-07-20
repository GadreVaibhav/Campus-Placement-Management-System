import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

import {
    createOffer,
    updateOffer
} from "../../services/offerService";

import {
    getRecruiterApplications
} from "../../services/recruiterApplicationService";

function RecruiterOfferModal({

    show,

    handleClose,

    offer,

    reload

}) {

    const [applications, setApplications] = useState([]);

   const [formData, setFormData] = useState({

    applicationId: "",

    packageOffered: "",

    location: "",

    joiningDate: ""

});

    useEffect(() => {

        if (show) {

            loadApplications();

        }

    }, [show]);

    const loadApplications = async () => {

        try {

            const token = localStorage.getItem("token");

            const data = await getRecruiterApplications(token);

            setApplications(data);

        }

        catch (error) {

            console.error(error);

        }

    };

    useEffect(() => {

        if (offer) {

            setFormData({

                applicationId: offer.applicationId,

                packageOffered: offer.packageOffered,

                location: offer.location,

                joiningDate: offer.joiningDate

            });

        }

        else {

            setFormData({

               applicationId: "",

                packageOffered: "",

                location: "",

                joiningDate: ""

            });

        }

    }, [offer]);

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

            if (offer) {

                await updateOffer(

                    offer.offerId,

                    formData

                );

                alert("Offer updated successfully.");

            }

            else {

                await createOffer(formData);

                alert("Offer created successfully.");

            }

            reload();

            handleClose();

        }

        catch (error) {

            console.error(error);

            alert("Failed to create offer.");

        }

    };

    return (

        <Modal

            show={show}

            onHide={handleClose}

            centered

        >

            <Modal.Header closeButton>

                <Modal.Title>

                    {

                        offer

                            ? "Update Offer"

                            : "Create Offer"

                    }

                </Modal.Title>

            </Modal.Header>

            <Form onSubmit={handleSubmit}>

                <Modal.Body>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Application

                        </Form.Label>

                       <Form.Select

    name="applicationId"

    value={formData.applicationId}

                            onChange={handleChange}

                            disabled={offer}

                            required

                        >

                            <option value="">

                                Select

                            </option>

                            {

                                applications.map(app => (

                                    <option

                                        key={app.applicationId}

                                        value={app.applicationId}

                                    >

                                        #{app.applicationId} - {app.studentName} - {app.jobRole} - {app.companyName}

                                    </option>

                                ))

                            }

                        </Form.Select>

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Package (LPA)

                        </Form.Label>

                        <Form.Control

                            type="number"

                            step="0.1"

                            name="packageOffered"

                            value={formData.packageOffered}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Location

                        </Form.Label>

                        <Form.Control

                            name="location"

                            value={formData.location}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group>

                        <Form.Label>

                            Joining Date

                        </Form.Label>

                        <Form.Control

                            type="date"

                            name="joiningDate"

                            value={formData.joiningDate}

                            onChange={handleChange}

                            required

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

                        {

                            offer

                                ? "Update"

                                : "Create"

                        }

                    </Button>

                </Modal.Footer>

            </Form>

        </Modal>

    );

}

export default RecruiterOfferModal;