import { useEffect, useState } from "react";
import {
    getRecruiterProfile,
    updateRecruiterProfile
} from "../../services/recruiterProfileService";

function RecruiterProfile() {

    const [profile, setProfile] = useState({});

    useEffect(() => {
        loadProfile();
    }, []);

    const loadProfile = async () => {
        try {
            const data = await getRecruiterProfile();
            setProfile(data);
        } catch (error) {
            console.error(error);
        }
    };

    const handleChange = (e) => {
        setProfile({
            ...profile,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await updateRecruiterProfile(profile);

            alert("Profile Updated Successfully");

        } catch (error) {

            console.error(error);

        }
    };

    return (

        <div className="container">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h4>My Profile</h4>

                </div>

                <div className="card-body">

                    <form onSubmit={handleSubmit}>

                        <div className="mb-3">

                            <label>Name</label>

                            <input
                                type="text"
                                name="name"
                                className="form-control"
                                value={profile.name || ""}
                                onChange={handleChange}
                            />

                        </div>

                        <div className="mb-3">

                            <label>Email</label>

                            <input
                                type="email"
                                className="form-control"
                                value={profile.email || ""}
                                disabled
                            />

                        </div>

                        <div className="mb-3">

                            <label>Designation</label>

                            <input
                                type="text"
                                name="designation"
                                className="form-control"
                                value={profile.designation || ""}
                                onChange={handleChange}
                            />

                        </div>

                        <div className="mb-3">

                            <label>Phone</label>

                            <input
                                type="text"
                                name="phone"
                                className="form-control"
                                value={profile.phone || ""}
                                onChange={handleChange}
                            />

                        </div>

                        <div className="mb-3">

                            <label>Company</label>

                            <input
                                type="text"
                                className="form-control"
                                value={profile.companyName || ""}
                                disabled
                            />

                        </div>

                        <button
                            className="btn btn-primary"
                            type="submit">

                            Update Profile

                        </button>

                    </form>

                </div>

            </div>

        </div>

    );

}

export default RecruiterProfile;