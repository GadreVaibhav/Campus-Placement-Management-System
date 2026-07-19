import api from "../api/api";

export const getAllPlacementDrives = (page = 0) => {
    return api.get(`/placement-drives?page=${page}`);
};

export const getPlacementDriveById = (id) => {
    return api.get(`/placement-drives/${id}`);
};

export const getUpcomingDrives = () => {
    return api.get("/placement-drives/upcoming");
};

export const deletePlacementDrive = (id) => {
    return api.delete(`/placement-drives/${id}`);
};

export const createPlacementDrive = (data) => {
    return api.post("/placement-drives", data);
};

export const updatePlacementDrive = (id, drive) => {

    return api.put(
        `/placement-drives/${id}`,
        drive
    );

};


