import { useEffect, useState } from "react";

import {
    Container,
    Card,
    Table,
    Button,
    Spinner
} from "react-bootstrap";

import {

    getRecruiterOffers,

    deleteOffer

} from "../../services/offerService";

import RecruiterOfferModal from "../../components/recruiter/RecruiterOfferModal";

function RecruiterOffers() {

    const [offers, setOffers] = useState([]);

    const [loading, setLoading] = useState(true);

    const [showModal, setShowModal] = useState(false);

    const [selectedOffer, setSelectedOffer] = useState(null);

    useEffect(() => {

        loadOffers();

    }, []);

    const loadOffers = async () => {

        try {

            setLoading(true);

            const data = await getRecruiterOffers();

            setOffers(data);

        }

        catch (error) {

            console.error(error);

        }

        finally {

            setLoading(false);

        }

    };

    const handleDelete = async (offerId) => {

        if (!window.confirm("Delete this offer?")) {

            return;

        }

        try {

            await deleteOffer(offerId);

            loadOffers();

        }

        catch (error) {

            console.error(error);

            alert("Unable to delete offer.");

        }

    };

    return (

        <Container className="mt-4">

            <Card>

                <Card.Header
                    className="d-flex justify-content-between align-items-center"
                >

                    <h4 className="mb-0">

                        Offer Management

                    </h4>

                    <Button

                        onClick={() => {

                            setSelectedOffer(null);

                            setShowModal(true);

                        }}

                    >

                        + Create Offer

                    </Button>

                </Card.Header>

                <Card.Body>

                    {

                        loading ?

                            <div className="text-center">

                                <Spinner animation="border" />

                            </div>

                            :

                            <Table
                                bordered
                                hover
                                responsive
                            >

                                <thead>

                                    <tr>

                                        <th>Student</th>

                                        <th>Company</th>

                                        <th>Job</th>

                                        <th>Package</th>

                                        <th>Location</th>

                                        <th>Joining</th>

                                        <th>Status</th>

                                        <th>Action</th>

                                    </tr>

                                </thead>

                                <tbody>

                                    {

                                        offers.map(offer => (

                                            <tr
                                                key={offer.offerId}
                                            >

                                                <td>

                                                    {offer.studentName}

                                                </td>

                                                <td>

                                                    {offer.companyName}

                                                </td>

                                                <td>

                                                    {offer.jobTitle}

                                                </td>

                                                <td>

                                                    {offer.packageOffered}

                                                </td>

                                                <td>

                                                    {offer.location}

                                                </td>

                                                <td>

                                                    {offer.joiningDate}

                                                </td>

                                                <td>

                                                    {offer.status}

                                                </td>

                                                <td>

                                                    <Button

                                                        size="sm"

                                                        className="me-2"

                                                        onClick={() => {

                                                            setSelectedOffer(offer);

                                                            setShowModal(true);

                                                        }}

                                                    >

                                                        Edit

                                                    </Button>

                                                    <Button

                                                        size="sm"

                                                        variant="danger"

                                                        onClick={() =>

                                                            handleDelete(

                                                                offer.offerId

                                                            )

                                                        }

                                                    >

                                                        Delete

                                                    </Button>

                                                </td>

                                            </tr>

                                        ))

                                    }

                                </tbody>

                            </Table>

                    }

                </Card.Body>

            </Card>

            <RecruiterOfferModal

                show={showModal}

                handleClose={() => setShowModal(false)}

                offer={selectedOffer}

                reload={loadOffers}

            />

        </Container>

    );

}

export default RecruiterOffers;