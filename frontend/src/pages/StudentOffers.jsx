import { useEffect, useState } from "react";
import {
    Container,
    Card,
    Table,
    Button,
    Spinner,
    Badge
} from "react-bootstrap";

import {
    getStudentOffers,
    acceptOffer,
    rejectOffer
} from "../services/offerService";

function StudentOffers() {

    const [offers, setOffers] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        loadOffers();
    }, []);

    const loadOffers = async () => {

        try {

            setLoading(true);

            const data = await getStudentOffers();

            console.log(data);

            setOffers(data);

        } catch (error) {

            console.error(error);

        } finally {

            setLoading(false);

        }

    };

    const handleAccept = async (offerId) => {

        try {

            await acceptOffer(offerId);

            alert("Offer Accepted Successfully");

            loadOffers();

        } catch (error) {

            console.error(error);

        }

    };

    const handleReject = async (offerId) => {

        try {

            await rejectOffer(offerId);

            alert("Offer Rejected");

            loadOffers();

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <Container className="mt-4">

            <Card className="shadow-sm">

                <Card.Header>

                    <h4 className="mb-0">

                        My Job Offers

                    </h4>

                </Card.Header>

                <Card.Body>

                    {loading ? (

                        <div className="text-center">

                            <Spinner animation="border" />

                        </div>

                    ) : (

                        <Table bordered hover responsive>

                            <thead>

                                <tr>

                                    <th>Company</th>

                                    <th>Job Role</th>

                                    <th>Package</th>

                                    <th>Location</th>

                                    <th>Joining Date</th>

                                    <th>Status</th>

                                    <th>Action</th>

                                </tr>

                            </thead>

                            <tbody>

                                {offers.length === 0 ? (

                                    <tr>

                                        <td
                                            colSpan="7"
                                            className="text-center"
                                        >
                                            No Offers Found
                                        </td>

                                    </tr>

                                ) : (

                                    offers.map((offer) => (

                                        <tr key={offer.offerId}>

                                            <td>

                                                {offer.companyName}

                                            </td>

                                            <td>

                                                {offer.jobTitle}

                                            </td>

                                            <td>

                                                {offer.packageOffered} LPA

                                            </td>

                                            <td>

                                                {offer.location}

                                            </td>

                                            <td>

                                                {offer.joiningDate}

                                            </td>

                                            <td>

                                                <Badge
                                                    bg={
                                                        offer.status === "ACCEPTED"
                                                            ? "success"
                                                            : offer.status === "REJECTED"
                                                            ? "danger"
                                                            : "warning"
                                                    }
                                                >
                                                    {offer.status}
                                                </Badge>

                                            </td>

                                            <td>
                                                {offer.status?.toUpperCase() === "OFFERED" ? (

                                                    <>
                                                        <Button
                                                            variant="success"
                                                            size="sm"
                                                            className="me-2"
                                                            onClick={() => handleAccept(offer.offerId)}
                                                        >
                                                            Accept
                                                        </Button>

                                                        <Button
                                                            variant="danger"
                                                            size="sm"
                                                            onClick={() => handleReject(offer.offerId)}
                                                        >
                                                            Reject
                                                        </Button>
                                                    </>

                                                ) : offer.status?.toUpperCase() === "ACCEPTED" ? (

                                                    <span className="badge bg-success">
                                                        ✓ Accepted
                                                    </span>

                                                ) : (

                                                    <span className="badge bg-danger">
                                                        ✕ Rejected
                                                    </span>

                                                )}
                                            </td>

                                        </tr>

                                    ))

                                )}

                            </tbody>

                        </Table>

                    )}

                </Card.Body>

            </Card>

        </Container>

    );

}

export default StudentOffers;