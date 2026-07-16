import Swal from "sweetalert2";

export const successAlert = (title) => {

    Swal.fire({
        icon: "success",
        title,
        showConfirmButton: false,
        timer: 1800
    });

};

export const errorAlert = (title) => {

    Swal.fire({
        icon: "error",
        title
    });

};

export const confirmDelete = async () => {

    return await Swal.fire({

        title: "Delete this record?",

        text: "This action cannot be undone.",

        icon: "warning",

        showCancelButton: true,

        confirmButtonColor: "#d33",

        cancelButtonColor: "#3085d6",

        confirmButtonText: "Delete"

    });

};