import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";

import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

import { Link } from "react-router-dom";

const Facultys = () => {
  const [data, setData] = useState([]);
  let componentMounted = true;

//   const dispatch = useDispatch();

//   const addFaculty = (faculty) => {
//     dispatch(addCart(faculty))
//   }

  useEffect(() => {
    const getFacultys = async () => {
      const response = await fetch("http://localhost:8070/faculty/all");
      console.log(response);
      if (componentMounted) {
        setData(await response.clone().json());
      }

      return () => {
        componentMounted = false;
      };
    };

    getFacultys();
  }, []);

  const ShowFacultys = () => {
    return (
      <>
      {
        data.map((faculty)=>{
            return(
                <div id={faculty.faculty_id} key={faculty.faculty_id} className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
                <div className="card">
                    <img className="card-img-top" src={faculty.photourl} alt="" />
                    <div className="card-body">
                        <h5 className="card-title">{faculty.title}{" "}{faculty.firstname}{" "}{faculty.lastname}</h5>
                        <p className="card-text">{faculty.email}</p>
                    </div>
                    <div className="card-body">
                        <a href="#" className="card-link">View</a>
                        <a href="#" className="card-link">Edit</a>
                        <a href="#" className="card-link">Delete</a>
                    </div>
                </div>
            </div>                
            )
        })

      }
            
      </>
    );
  };
  return (
    <>
      <div className="container my-3 py-3">
        <div className="row">
          <div className="col-12">
            <h2 className="display-7 text-center">Faculty</h2>
            <hr />
          </div>
        </div>
        <div className="row justify-content-center">
          {<ShowFacultys />}
        </div>
      </div>
    </>
  );
};

export default Facultys;
