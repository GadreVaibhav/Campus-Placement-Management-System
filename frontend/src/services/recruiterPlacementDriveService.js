import api from "../api/api";

export const getRecentPlacementDrives = async () => {

    const response = await api.get(
        "/recruiter/dashboard/placement-drives"
    );

    return response.data;
};

export const getRecruiterPlacementDrives = getRecentPlacementDrives;