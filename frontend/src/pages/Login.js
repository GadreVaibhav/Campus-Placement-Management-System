import { useState } from "react";
import {
  FaEnvelope,
  FaLock,
  FaEye,
  FaEyeSlash,
} from "react-icons/fa";

import { Link, useNavigate } from "react-router-dom";

import { login } from "../services/authService";

function Login() {

  const navigate = useNavigate();

  const [showPassword, setShowPassword] = useState(false);

  const [loading, setLoading] = useState(false);

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      setLoading(true);

      localStorage.clear();

const response = await login(formData);

localStorage.setItem("token", response.data.token);
localStorage.setItem("role", response.data.role);

      alert("Login Successful!");

      if (response.data.role === "ADMIN") {
        navigate("/admin/dashboard");
      } else if (response.data.role === "STUDENT") {
        navigate("/student");
      } else {
        navigate("/recruiter");
      }

    } catch (error) {

      alert(
        error.response?.data?.message ||
        "Invalid Email or Password"
      );

    } finally {

      setLoading(false);

    }

  };

  return (

    <div className="container-fluid vh-100">

      <div className="row h-100">

        {/* Left Side */}

        <div className="col-lg-6 d-none d-lg-flex align-items-center justify-content-center bg-primary text-white">

          <div className="text-center">

            <h1 className="display-4 fw-bold">
              Campus Placement
            </h1>

            <h2>Management System</h2>

            <p className="mt-4 fs-5">
              Connecting Students, Recruiters and Opportunities.
            </p>

          </div>

        </div>

        {/* Right Side */}

        <div className="col-lg-6 d-flex align-items-center justify-content-center">

          <div
            className="card shadow-lg p-5 border-0"
            style={{ width: "420px" }}
          >

            <h2 className="text-center mb-4">
              Welcome Back 👋
            </h2>

            <form onSubmit={handleSubmit}>

              {/* Email */}

              <div className="mb-3">

                <label className="form-label">
                  Email
                </label>

                <div className="input-group">

                  <span className="input-group-text">
                    <FaEnvelope />
                  </span>

                  <input
                    type="email"
                    name="email"
                    className="form-control"
                    placeholder="Enter email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                  />

                </div>

              </div>

              {/* Password */}

              <div className="mb-4">

                <label className="form-label">
                  Password
                </label>

                <div className="input-group">

                  <span className="input-group-text">
                    <FaLock />
                  </span>

                  <input
                    type={showPassword ? "text" : "password"}
                    name="password"
                    className="form-control"
                    placeholder="Enter password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                  />

                  <button
                    className="btn btn-outline-secondary"
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                  >
                    {showPassword ? <FaEyeSlash /> : <FaEye />}
                  </button>

                </div>

              </div>

              <button
                type="submit"
                className="btn btn-primary w-100"
                disabled={loading}
              >
                {loading ? "Logging in..." : "Login"}
              </button>

            </form>

            <p className="text-center mt-4">

              Don't have an account?

              <Link to="/register">
                {" "}Register
              </Link>

            </p>

          </div>

        </div>

      </div>

    </div>

  );
}

export default Login;