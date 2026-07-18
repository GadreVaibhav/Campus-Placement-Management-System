import { useState } from "react";
import { toast } from "react-toastify";
import {
    uploadResume,
    deleteResume,
    downloadResume
} from "../services/StudentService";

function Documents() {

    const [file, setFile] = useState(null);

    const handleFileChange = (e) => {

        setFile(e.target.files[0]);

    };

    const handleUpload = async () => {

        if (!file) {

            toast.warning("Please select a PDF.");

            return;

        }

        const formData = new FormData();

        formData.append("file", file);

        try {

            await uploadResume(formData);

            toast.success("Resume uploaded successfully.");

        }

        catch {

            toast.error("Unable to upload resume.");

        }

    };

    const handleDownload = async () => {

        try {

            const response = await downloadResume();

            const url = window.URL.createObjectURL(
                new Blob([response.data])
            );

            const link = document.createElement("a");

            link.href = url;

            link.download = "Resume.pdf";

            link.click();

        }

        catch {

            toast.error("Resume not found.");

        }

    };

    const handleDelete = async () => {

        try {

            await deleteResume();

            toast.success("Resume deleted.");

        }

        catch {

            toast.error("Unable to delete resume.");

        }

    };

    return (

        <div className="container mt-5">

            <div className="card shadow">

                <div className="card-header bg-primary text-white">

                    <h3>Documents</h3>

                </div>

                <div className="card-body">

                    <h5>Resume</h5>

                    <input

                        type="file"

                        accept=".pdf"

                        className="form-control mb-3"

                        onChange={handleFileChange}

                    />

                    <button
                        className="btn btn-success me-2"
                        onClick={handleUpload}
                    >
                        Upload Resume
                    </button>

                    <button
                        className="btn btn-primary me-2"
                        onClick={handleDownload}
                    >
                        Download Resume
                    </button>

                    <button
                        className="btn btn-danger"
                        onClick={handleDelete}
                    >
                        Delete Resume
                    </button>

                </div>

            </div>

        </div>

    );

}

export default Documents;