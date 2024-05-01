import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";

import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

import { Link } from "react-router-dom";

const CourseObs = () => {
  const [data, setData] = useState([]);
  const [course, setCourse] = useState([]);
  const [specs, setSpecs] = useState([]);
  const [filter, setFilter] = useState(course);
  let componentMounted = true;

  useEffect(() => {
    const getCourseObs = async () => {
      const response = await fetch("http://localhost:8070/course/all");
      console.log(response);
      if (componentMounted) {
        setCourse(await response.clone().json());
        setFilter(await response.json());
        
      }
      const response2 = await fetch("http://localhost:8070/utils/getAllSpec");
      console.log(response2);
      if (componentMounted) {
        setSpecs(await response2.json());        
      }
      console.log(specs);
      
      return () => {
        componentMounted = false;
      };
    };

    getCourseObs();
  }, []);

  const filterCourseOb = (specId) => {
    console.log(course);
    console.log(specId);
    const updatedList = course.filter((item) => item.specId!=undefined && item.specId.spec_id===specId );
    console.log("filter:");
    console.log(updatedList);
    setFilter(updatedList);
  }

  const ShowSpecObs = () => {
    return (
      <>{specs.map((spec) => {
          return (
            <div id={spec.spec_id} key={spec.spec_id} className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">                
                <div className="card">
                    <div className="card-body">
                        <h6 className="card-title">{spec.name}</h6>
                        <hr/>
                        <h6 className="card-subtitle mb-2 text-muted">{"Code: "}{spec.code}</h6>
                        <h6 className="card-subtitle mb-2 text-muted">{"Credits: "}{spec.credits_req}</h6>
                        <p className="card-text">{spec.description}</p>
                        <a href="#" className="card-link">Edit</a>
                        <a href="#" className="card-link">Delete</a>
                    </div>
                </div>
            </div>
          );
        })}
      </>
    );
  };

  const ShowSpecFilterMenu = () => {

    return (
        <>
        <div className="buttons text-center py-5">
            <button className="btn btn-outline-dark btn-sm m-2" onClick={() => setFilter(course)}>All</button>
            {
                specs.map((spec) => {
                    return (<button key={"filter"+spec.spec_id} className="btn btn-outline-dark btn-sm m-2" onClick={() => filterCourseOb(spec.spec_id)}>{spec.code}</button>);
                })
            }
        </div>
        
        </>
      );

  }




  const ShowCourseObs = () => {
    return (
      <>
        <ShowSpecFilterMenu />
        {filter.map((crs) => {
          return (

            <div id={crs.course_id} key={crs.course_id} className="col-md-4 col-sm-6 col-xs-8 col-12 mb-4">
                <div className="card">
                    <div className="card-body">
                        <h2 className="card-title">{crs.coursecode}</h2>                        
                        <h5 className="card-title">{crs.coursename}</h5>
                        <hr/>
                        <p>{"Credits"}</p>
                        <h5 className="card-title">{crs.credits}</h5>
                        <p>{"Capacity"}</p>
                        <h5 className="card-title">{crs.capacity}</h5>
                        <p>{"Faculty"}</p>
                        <h5 className="card-title">{crs.facultyId.title}{" "}{crs.facultyId.firstname}{" "}{crs.facultyId.lastname}
                        </h5>
                    </div>
                    <div className="card-body">
                        <a href="#" className="card-link">View</a>
                        <a href="#" className="card-link">Edit</a>
                        <a href="#" className="card-link">Delete</a>
                    </div>
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
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active" href="/addSpecialization">Add Specialization</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Add Course</a>
            </li>
        </ul>
        </div>
      <div className="row">
          <div className="col-12">
            <h2 className="display-7 text-center">Specializations</h2>
            <hr />
          </div>
        </div>
        <div className="row justify-content-center">
          {<ShowSpecObs />}
        </div>
        <div className="row">
          <div className="col-12">
            <h2 className="display-7 text-center">Courses</h2>
            <hr />
          </div>
        </div>
        <div className="row justify-content-center">
          {<ShowCourseObs />}
        </div>
      </div>
    </>
  );
};

export default CourseObs;
