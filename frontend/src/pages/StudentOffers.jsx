import { useEffect, useState } from "react";

import {
    Container,
    Card,
    Table,
    Button,
    Spinner
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

            setOffers(data);

        }

        catch (error) {

            console.error(error);

        }

        finally {

            setLoading(false);

        }

    };

    const handleAccept = async (offerId) => {

        try {

            await acceptOffer(offerId);

            alert("Offer Accepted Successfully");

            loadOffers();

        }

        catch (error) {

            console.error(error);

        }

    };

    const handleReject = async (offerId) => {

        try {

            await rejectOffer(offerId);

            alert("Offer Rejected");

            loadOffers();

        }

        catch (error) {

            console.error(error);

        }

    };

    return (

        <Container className="mt-4">

            <Card>

                <Card.Header>

                    <h4>

                        My Job Offers

                    </h4>

                </Card.Header>

                <Card.Body>

                    {

                        loading ?

                            <div className="text-center">

                                <Spinner animation="border"/>

                            </div>

                            :

                            <Table
                                bordered
                                hover
                                responsive
                            >

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

                                    {

                                        offers.map(offer => (

                                            <tr
                                                key={offer.offerId}
                                            >

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

                                                    {offer.status}

                                                </td>

                                                <td>

                                                    {

                                                        offer.status === "PENDING"

                                                        ?

                                                        <>

                                                            <Button

                                                                size="sm"

                                                                className="me-2"

                                                                variant="success"

                                                                onClick={() =>

                                                                    handleAccept(

                                                                        offer.offerId

                                                                    )

                                                                }

                                                            >

                                                                Accept

                                                            </Button>

                                                            <Button

                                                                size="sm"

                                                                variant="danger"

                                                                onClick={() =>

                                                                    handleReject(

                                                                        offer.offerId

                                                                    )

                                                                }

                                                            >

                                                                Reject

                                                            </Button>

                                                        </>

                                                        :

                                                        "-"

                                                    }

                                                </td>

                                            </tr>

                                        ))

                                    }

                                </tbody>

                            </Table>

                    }

                </Card.Body>

            </Card>

        </Container>

    );

}

export default StudentOffers;