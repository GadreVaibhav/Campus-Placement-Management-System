import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { FaPlus } from "react-icons/fa";

import PlacementDriveTable from "../../components/recruiter/PlacementDriveTable";
import RecruiterPlacementDriveModal from "../../components/recruiter/RecruiterPlacementDriveModal";

import {
    getRecruiterPlacementDrives
} from "../../services/recruiterPlacementDriveService";

function RecruiterPlacementDrives() {

    const [drives, setDrives] = useState([]);

    const [showModal, setShowModal] = useState(false);

    const [selectedDrive, setSelectedDrive] = useState(null);

    useEffect(() => {

        loadDrives();

    }, []);

    const loadDrives = async () => {

        const data = await getRecruiterPlacementDrives();

console.log(data);

setDrives(data);
        try {

            const data = await getRecruiterPlacementDrives();

            setDrives(data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <>

            <div className="d-flex justify-content-between align-items-center mb-4">

                <h2 className="fw-bold">

                    Placement Drives

                </h2>

                <Button
                    onClick={() => {

                        setSelectedDrive(null);

                        setShowModal(true);

                    }}
                >

                    <FaPlus className="me-2"/>

                    Add Drive

                </Button>

            </div>

            <PlacementDriveTable

                drives={drives}

                reload={loadDrives}

                onEdit={(drive)=>{

                    setSelectedDrive(drive);

                    setShowModal(true);

                }}

            />

            <RecruiterPlacementDriveModal

                show={showModal}

                handleClose={() => setShowModal(false)}

                drive={selectedDrive}

                reload={loadDrives}

            />

        </>

    );

}

export default RecruiterPlacementDrives;