      import { useEffect, useState } from "react";

import {
    getAllCompanies,
    searchCompanies,
    deleteCompany,
    updateCompany
} from "../services/CompanyManagementService";
        function AdminCompanies() {

          const [companies, setCompanies] = useState([]);

const [companyName, setCompanyName] = useState("");

const [industry, setIndustry] = useState("");

const [location, setLocation] = useState("");

const [isActive, setIsActive] = useState("");

const [page, setPage] = useState(0);

const [totalPages, setTotalPages] = useState(0);

const [editingCompany, setEditingCompany] = useState(null);

const [formData, setFormData] = useState({

    companyName: "",

    email: "",

    website: "",

    industry: "",

    location: "",

    description: "",

    contactPerson: "",

    contactNumber: ""

});
         useEffect(() => {

    loadCompanies();

}, [page]);

            // ==============================
            // Load Companies
            // ==============================

           const loadCompanies = async () => {

    try {

        const response = await getAllCompanies(page);

        setCompanies(response.data.content);

        setTotalPages(response.data.totalPages);

    } catch (error) {

        console.error(error);

    }

};

            // ==============================
            // Search
            // ==============================
const handleSearch = async () => {

    try {

        const response = await searchCompanies(

            companyName,

            industry,

            location,

            isActive

        );

        setCompanies(response.data.content);

    }

    catch (error) {

        console.error(error);

    }

};

            // ==============================
            // Reset
            // ==============================

           const resetFilters = () => {

    setCompanyName("");

    setIndustry("");

    setLocation("");

    setIsActive("");

    setPage(0);

    loadCompanies();

};

const handleDelete = async (companyId) => {

    if (!window.confirm("Delete this company?")) {

        return;

    }

    try {

        await deleteCompany(companyId);

        alert("Company deleted successfully.");

        loadCompanies();

    }

    catch (error) {

        console.error(error);

        alert("Delete failed.");

    }

};

const handleEdit = (company) => {

    setEditingCompany(company);

    setFormData({

        companyName: company.companyName,

        email: company.email,

        website: company.website,

        industry: company.industry,

        location: company.location,

        description: company.description,

        contactPerson: company.contactPerson,

        contactNumber: company.contactNumber

    });

};

const handleUpdate = async () => {

    try {

        await updateCompany(
            editingCompany.id,
            formData
        );

        alert("Company updated successfully.");

        setEditingCompany(null);

        loadCompanies();

    } catch (error) {

        console.error(error);

        alert("Update failed.");

    }

};

return (


           <div className="container mt-4">

<h2 className="mb-4">

Company Management

</h2>

<div className="row mb-4 align-items-end">

    <div className="col-md-3">

        <input

            type="text"

            className="form-control"

            placeholder="Company Name"

            value={companyName}

            onChange={(e)=>setCompanyName(e.target.value)}

        />

    </div>

    <div className="col-md-2">

        <input

            type="text"

            className="form-control"

            placeholder="Industry"

            value={industry}

            onChange={(e)=>setIndustry(e.target.value)}

        />

    </div>

    <div className="col-md-2">

        <input

            type="text"

            className="form-control"

            placeholder="Location"

            value={location}

            onChange={(e)=>setLocation(e.target.value)}

        />

    </div>

    <div className="col-md-2">

        <select

            className="form-select"

            value={isActive}

            onChange={(e)=>setIsActive(e.target.value)}

        >

            <option value="">All</option>

            <option value="true">Active</option>

            <option value="false">Inactive</option>

        </select>

    </div>

    <div className="col-md-3 d-flex gap-2">

        <button

            className="btn btn-primary w-100"

            onClick={handleSearch}

        >

            Search

        </button>

        <button

            className="btn btn-secondary w-100"

            onClick={resetFilters}

        >

            Reset

        </button>

    </div>

</div>

<table className="table table-bordered table-hover">

<thead className="table-dark">

<tr>

<th>ID</th>

<th>Company</th>

<th>Industry</th>

<th>Location</th>

<th>Status</th>

<th>Actions</th>

</tr>

</thead>

<tbody>

{

companies.map(company=>(

<tr key={company.id}>

<td>{company.id}</td>

<td>{company.companyName}</td>

<td>{company.industry}</td>

<td>{company.location}</td>

<td>

{

company.isActive ?

<span className="badge bg-success">

Active

</span>

:

<span className="badge bg-danger">

Inactive

</span>

}

</td>

<td>

<div className="d-flex gap-2">

<button

className="btn btn-warning btn-sm"

onClick={()=>handleEdit(company)}

>

Edit

</button>

<button

className="btn btn-danger btn-sm"

onClick={()=>handleDelete(company.id)}

>

Delete

</button>

</div>

</td>

</tr>

))

}

</tbody>

</table>

<div className="d-flex justify-content-between mt-3">

<button

className="btn btn-secondary"

disabled={page===0}

onClick={()=>setPage(page-1)}

>

Previous

</button>

<span>

Page {page+1} of {totalPages}

</span>

<button

className="btn btn-secondary"

disabled={page+1>=totalPages}

onClick={()=>setPage(page+1)}

>

Next

</button>

</div>

{
editingCompany && (

<div
className="modal fade show"
style={{
display:"block",
background:"rgba(0,0,0,.5)"
}}
>

<div className="modal-dialog modal-lg">

<div className="modal-content">

<div className="modal-header">

<h5>Edit Company</h5>

<button
className="btn-close"
onClick={()=>setEditingCompany(null)}
></button>

</div>

<div className="modal-body">

<input
className="form-control mb-2"
placeholder="Company Name"
value={formData.companyName || ""}
onChange={(e)=>
setFormData({
...formData,
companyName:e.target.value
})
}
/>

<input
className="form-control mb-2"
placeholder="Email"
value={formData.email || ""}
onChange={(e)=>
setFormData({
...formData,
email:e.target.value
})
}
/>

<input
className="form-control mb-2"
placeholder="Website"
value={formData.website || ""}
onChange={(e)=>
setFormData({
...formData,
website:e.target.value
})
}
/>

<input
className="form-control mb-2"
placeholder="Industry"
value={formData.industry || ""}
onChange={(e)=>
setFormData({
...formData,
industry:e.target.value
})
}
/>

<input
className="form-control mb-2"
placeholder="Location"
value={formData.location || ""}
onChange={(e)=>
setFormData({
...formData,
location:e.target.value
})
}
/>

<textarea
className="form-control mb-2"
rows="3"
placeholder="Description"
value={formData.description || ""}
onChange={(e)=>
setFormData({
...formData,
description:e.target.value
})
}
/>

<input
className="form-control mb-2"
placeholder="Contact Person"
value={formData.contactPerson || ""}
onChange={(e)=>
setFormData({
...formData,
contactPerson:e.target.value
})
}
/>

<input
className="form-control"
placeholder="Contact Number"
value={formData.contactNumber || ""}
onChange={(e)=>
setFormData({
...formData,
contactNumber:e.target.value
})
}
/>

</div>

<div className="modal-footer">

<button
className="btn btn-secondary"
onClick={()=>setEditingCompany(null)}
>
Cancel
</button>

<button
className="btn btn-success"
onClick={handleUpdate}
>
Update
</button>

</div>

</div>

</div>

</div>

)
}
</div>

);

}

export default AdminCompanies;
