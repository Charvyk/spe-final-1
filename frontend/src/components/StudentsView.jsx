import React, { useState, useEffect } from "react";
import axios from 'axios';
import Modal from 'react-modal';  // Import the modal library
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";
import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";
import { Link } from "react-router-dom";
import UpdateCgpa from "./UpdateCgpa";

const Students = () => {
  const [data, setData] = useState([]);
  const [filter, setFilter] = useState(data);
  const [loading, setLoading] = useState(false);
  const [batchset, setBatchset] = useState(false);


  let componentMounted = true;

 //   const dispatch = useDispatch();

  const batches = [
    {
      degree: 'IMT',
      year: 2020
    }, 
    {
      degree: 'IMT',
      year: 2021
    }, 
    {
      degree: 'IMT',
      year: 2022
    }, 
    {
      "degree": "IMT",
      "year": 2023
    },
    {
      "degree": "MT",
      "year": 2020
    },
    {
      "degree": "MT",
      "year": 2021
    },
    {
      "degree": "MT",
      "year": 2022
    },
    {
      "degree": "MT",
      "year": 2023
    }
    ];

  
  //Fore new student creation
  const [formData, setFormData] = useState({
    s_batch_code: '',
    rollnumber: '',
    firstname: '',
    lastname: '',
    email: '',
    photourl: '',
    totalcredits: '',
    graduationYear: ''
  });
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!batchset) {
      for (let i = 0; i < batches.length; i++) {
        axios.post("http://localhost:8070/utils/addBatch", batches[i])
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          if(error.response.status == 400){
            console.log("already batch is added");
          }
          else{
            console.log("other error when adding batch");
          }
        })
      }
      setBatchset(true);    
    }




    axios.post("http://localhost:8070/student/add", formData)
    .then((response)=>{
      console.log(response)
    })
    .catch((error) => {
      console.log(error.response.status)
      console.log("student already added or else batch is invalid");
    })
    console.log('Form submitted:', formData);
    
  };

  //For UPDATE cgpa of students
  const [editcgpa, seteditcgpa] = useState(
    0.0
  );
  const handlecgpaChange = (e) => {
    const { name, value } = e.target;
    seteditcgpa(
      value
    );
  };
  const handlecgpaSubmit = (student,e) => {
    e.preventDefault();
    const payload = {
      s_batch_code: student.s_batch_code,
      rollnumber: student.rollnumber,
      firstname: student.firstname,
      lastname: student.lastname,
      email: student.email,
      photourl: student.photourl,
      totalcredits: student.totalcredits,
      graduationYear: student.graduationYear,
      cgpa: editcgpa
    }
    axios.post("http://localhost:8070/student/update", payload)
  }
  const [isComponentVisible, setIsComponentVisible] = useState(false);

  const handleEditButtonClick = () => {
    // Toggle the state to show/hide the component
    setIsComponentVisible(!isComponentVisible);
  };


  //For READ students
  useEffect(() => {
    const getStudents = async () => {
      setLoading(true);
      const response = await fetch("http://localhost:8070/student/all");
      console.log(response);
      if (componentMounted) {
        setData(await response.clone().json());
        setFilter(await response.json());
        setLoading(false);
      }

      return () => {
        componentMounted = false;
      };
    };

    getStudents();
  }, []);



  //for deleteling
  const handleDelete = (student_id,e) => {
    e.preventDefault();
    console.log("http://localhost:8070/student/delete?id="+student_id)
    axios.delete("http://localhost:8070/student/delete?id="+student_id)
    .then((response) => {
      console.log("deleted successfully!")
    })
    .catch((error) => {
      console.log("no student with this id found")
    })
  }

  const filterStudent = (batch) => {
    const updatedList = data.filter((item) => item.s_batch_code.batch_id===batch );
    setFilter(updatedList);
  }
  const ShowStudents = () => {
    return (
      <>
        <div className="buttons text-center py-5">
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => setFilter(data)}>All</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("MT2020")}>MT2020</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("MT2021")}>MT2021</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("MT2022")}>MT2022</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("MT2023")}>MT2023</button>

          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("IMT2020")}>IMT2020</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("IMT2021")}>IMT2021</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("IMT2022")}>IMT2022</button>
          <button className="btn btn-outline-dark btn-sm m-2" onClick={() => filterStudent("IMT2023")}>IMT2023</button>
        </div>

        {filter.map((student) => {
          return (

            <div id={student.student_id} key={student.student_id} className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
                <div className="card">
                    <img className="card-img-top" src={student.photourl} alt="" />
                    <div className="card-body">
                        <h5 className="card-title">{student.firstname}{" "}{student.lastname}</h5>
                        <p className="card-text">{student.email}</p>
                    </div>
                    <ul className="list-group list-group-flush">
                        {/* <li class="list-group-item">{student.email}</li> */}
                        <li className="list-group-item">{student.rollnumber}</li>
                    </ul>
                    <div className="card-body">
                        <a href="#" className="card-link">View</a>

                        <button onClick={handleEditButtonClick}>
                          Edit
                        </button>
                        

                        <button className="btn btn-link card-link"  onClick={(e) => handleDelete(student.student_id,e)}>
                          Delete
                        </button>
                    </div>
                    {isComponentVisible && <UpdateCgpa value={student} />}
                    </div>
            </div>
          );
        })}
      </>
    );
  };


  return (
    <>
      <div className="container my-3 py-3">
        <div className="row">
          <div className="col-12">
            <h2 className="display-7 text-center">Students</h2>
            <hr />
          </div>
        </div>

        <div className="row justify-content-center">
          {<ShowStudents />}
        </div>


        <div>
          <h2>Add new Student</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-3">
              <label htmlFor="s_batch_code" className="form-label">Batch Code</label>
              <input type="text" className="form-control" id="s_batch_code" name="s_batch_code" value={formData.s_batch_code} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="rollnumber" className="form-label">Roll Number</label>
              <input type="text" className="form-control" id="rollnumber" name="rollnumber" value={formData.rollnumber} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="firstname" className="form-label">First Name</label>
              <input type="text" className="form-control" id="firstname" name="firstname" value={formData.firstname} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="lastname" className="form-label">Last Name</label>
              <input type="text" className="form-control" id="lastname" name="lastname" value={formData.lastname} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="email" className="form-label">Email</label>
              <input type="text" className="form-control" id="email" name="email" value={formData.email} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="photourl" className="form-label">Photo URL</label>
              <input type="text" className="form-control" id="photourl" name="photourl" value={formData.photourl} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="totalcredits" className="form-label">Total Credits</label>
              <input type="number" className="form-control" id="totalcredits" name="totalcredits" value={formData.totalcredits} onChange={handleInputChange} />
            </div>
            <div className="mb-3">
              <label htmlFor="graduationYear" className="form-label">Graduation Year</label>
              <input type="number" className="form-control" id="graduationYear" name="graduationYear" value={formData.graduationYear} onChange={handleInputChange} />
            </div>
            <button type="submit" className="btn btn-primary">Submit</button>
          </form>


        </div>
      </div>
    </>
  );
};

export default Students;

