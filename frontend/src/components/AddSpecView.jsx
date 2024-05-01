import React, { useState, useEffect } from "react";
import { useDispatch } from "react-redux";
import { addCart } from "../redux/action";

import Skeleton from "react-loading-skeleton";
import "react-loading-skeleton/dist/skeleton.css";

import { Link } from "react-router-dom";

const AddSpec = () => {
    const formSubmitHandler = () => {

        const url = "http://localhost:8070/utils/addSpec";

        console.log("Form Submission");
        const data = {};

        fetch(url, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
          })
            .then(response => {
              if (!response.ok) {
                throw new Error('Network response was not ok');
              }
              console.log("fetched response");
              console.log(response.body);
              return response.json();
            })
            .then(data => {
              // Handle the response data
              console.log('Response:', data);
            })
            .catch(error => {
              // Handle errors
              console.error('There was an error with the request:', error);
            });

    }
    const AddSpecForm = () => {
        return(
            <>
                <form>
                    <div className="form-group row">
                        <label htmlFor="code" className="col-sm-2 col-form-label">Code</label>
                        <div className="col-sm-10">
                        <input type="text" className="form-control" id="code" placeholder="eg. AB001" />
                        </div>
                    </div>
                    <div className="form-group row">
                        <label htmlFor="name" className="col-sm-2 col-form-label">Name</label>
                        <div className="col-sm-10">
                        <input type="text" className="form-control" id="name" placeholder="eg. Data Science" />
                        </div>
                    </div>
                    <div className="form-group row">
                        <label htmlFor="credits_req" className="col-sm-2 col-form-label">Credits</label>
                        <div className="col-sm-10">
                        <input type="password" className="form-control" id="credits_req" placeholder="eg. 12" />
                        </div>
                    </div>
                    <div className="form-group row">
                        <button onClick={formSubmitHandler()} className="btn btn-dark offset-2 col-10">Submit</button>
                    </div>
                </form>
            </>
        )
    }
  return (
    <>
      <div className="container my-3 py-3">
        <div className="row">
          <div className="col-12">
            <h2 className="display-7 text-center">Add Specialization</h2>
            <hr />
          </div>
        </div>
        <div className="row justify-content-center">
          {<AddSpecForm />}
        </div>
      </div>
    </>
  );
};

export default AddSpec;
