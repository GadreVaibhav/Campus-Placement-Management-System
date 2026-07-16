import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { register } from "../services/authService";

function Register() {

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    password: "",
    role: "STUDENT"
  });

  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      setLoading(true);

      await register(formData);

      alert("Registration Successful!");

      navigate("/login");

    } catch (err) {

      alert(err.response?.data?.message || "Registration Failed");

    } finally {

      setLoading(false);

    }

  };

  return (

    <div className="container vh-100 d-flex justify-content-center align-items-center">

      <div className="card shadow p-5" style={{width:"450px"}}>

        <h2 className="text-center mb-4">
          Create Account
        </h2>

        <form onSubmit={handleSubmit}>

          <div className="mb-3">

            <label>Email</label>

            <input
              type="email"
              className="form-control"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
            />

          </div>

          <div className="mb-3">

            <label>Password</label>

            <input
              type="password"
              className="form-control"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />

          </div>

          <div className="mb-4">

            <label>Role</label>

            <select
              className="form-select"
              name="role"
              value={formData.role}
              onChange={handleChange}
            >

              <option value="STUDENT">Student</option>

              <option value="RECRUITER">Recruiter</option>

              <option value="ADMIN">Admin</option>

            </select>

          </div>

          <button
            className="btn btn-success w-100"
            disabled={loading}
          >

            {loading ? "Creating..." : "Register"}

          </button>

        </form>

        <p className="text-center mt-3">

          Already have an account?

          <Link to="/login">
            {" "}Login
          </Link>

        </p>

      </div>

    </div>

  );

}

export default Register;