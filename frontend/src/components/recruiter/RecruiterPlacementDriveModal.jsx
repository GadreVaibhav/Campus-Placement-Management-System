import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";

import {
    createPlacementDrive,
    updatePlacementDrive
} from "../../services/PlacementDriveService";

function RecruiterPlacementDriveModal({

    show,

    handleClose,

    drive,

    reload

}) {

    const [formData, setFormData] = useState({

        

        jobRole: "",

        packageOffered: "",

        minimumCgpa: "",

        driveDate: "",

        registrationDeadline: "",

        status: "UPCOMING"

    });

    useEffect(() => {

        if (drive) {

            setFormData({

                jobRole: drive.jobRole || "",

                packageOffered: drive.packageOffered || "",

                minimumCgpa: drive.minimumCgpa || "",

                driveDate: drive.driveDate || "",

                registrationDeadline:
                    drive.registrationDeadline || "",

                status: drive.status || "UPCOMING"

            });

        } else {

            setFormData({

                

                jobRole: "",

                packageOffered: "",

                minimumCgpa: "",

                driveDate: "",

                registrationDeadline: "",

                status: "UPCOMING"

            });

        }

    }, [drive]);

    const handleChange = (e) => {

        setFormData({

            ...formData,

            [e.target.name]: e.target.value

        });

    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            if (drive) {

              await updatePlacementDrive(drive.id, formData);
                alert("Placement Drive Updated.");

            } else {

                await createPlacementDrive(formData);

                alert("Placement Drive Created.");

            }

            reload();

            handleClose();

        } catch (error) {

            console.error(error);

            alert("Operation Failed.");

        }

    };

    return (

        <Modal show={show} onHide={handleClose}>

            <Form onSubmit={handleSubmit}>

                <Modal.Header closeButton>

                    <Modal.Title>

                        {drive
                            ? "Edit Placement Drive"
                            : "Add Placement Drive"}

                    </Modal.Title>

                </Modal.Header>

                <Modal.Body>

                    

                    <Form.Group className="mb-3">

                        <Form.Label>Job Role</Form.Label>

                        <Form.Control

                            name="jobRole"

                            value={formData.jobRole}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>Package (LPA)</Form.Label>

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

                        <Form.Label>Minimum CGPA</Form.Label>

                        <Form.Control

                            type="number"

                            step="0.1"

                            name="minimumCgpa"

                            value={formData.minimumCgpa}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>Drive Date</Form.Label>

                        <Form.Control

                            type="date"

                            name="driveDate"

                            value={formData.driveDate}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group className="mb-3">

                        <Form.Label>

                            Registration Deadline

                        </Form.Label>

                        <Form.Control

                            type="date"

                            name="registrationDeadline"

                            value={formData.registrationDeadline}

                            onChange={handleChange}

                            required

                        />

                    </Form.Group>

                    <Form.Group>

                        <Form.Label>Status</Form.Label>

                        <Form.Select

                            name="status"

                            value={formData.status}

                            onChange={handleChange}

                        >

                            <option value="UPCOMING">

                                UPCOMING

                            </option>

                            <option value="LIVE">

                                LIVE

                            </option>

                            <option value="COMPLETED">

                                COMPLETED

                            </option>
                            <option value="CANCELLED">CANCELLED</option>

                        </Form.Select>

                    </Form.Group>

                </Modal.Body>

                <Modal.Footer>

                    <Button
                        variant="secondary"
                        onClick={handleClose}
                    >
                        Cancel
                    </Button>

                    <Button
                        variant="primary"
                        type="submit"
                    >
                        {drive ? "Update" : "Save"}
                    </Button>

                </Modal.Footer>

            </Form>

        </Modal>

    );

}

export default RecruiterPlacementDriveModal;