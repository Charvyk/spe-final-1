import React, {useState, useEffect} from "react";
import axios from "axios";

const UpdateCgpa = ({ value }) => {
    const [formData, setFormData] = useState(value);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
          ...formData,
          [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8070/student/update",formData)
    };


    return (
        <div>
            <h3>change cgpa</h3>
            <form onSubmit = {handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="graduationYear" className="form-label">CGPA</label>
                    <input type="number" className="form-control" id="cgpa" name="cgpa" value={formData.cgpa} onChange={handleInputChange} />
                </div>
                <button type="submit" className="btn btn-primary">Update</button>
            </form>
        </div>
  );
};

export default UpdateCgpa;