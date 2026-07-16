import api from "../api/api";

export const getAllStudents = (
    page = 0,
    size = 5,
    sortBy = "studentId",
    direction = "asc"
) => {

    return api.get(
        `/students?page=${page}&size=${size}&sortBy=${sortBy}&direction=${direction}`
    );

};

export const searchStudents = (
    name,
    branch,
    skill,
    cgpa,
    graduationYear,
    placed,
    page = 0,
    size = 5
) => {

    return api.get("/students/search", {

        params: {
            name,
            branch,
            skill,
            cgpa,
            graduationYear,
            placed,
            page,
            size
        }

    });

};

export const deleteStudent = (studentId) => {

    return api.delete(`/students/${studentId}`);

};

// ADD THIS

export const updateStudent = (studentId, student) => {

    return api.put(`/students/${studentId}`, student);

};