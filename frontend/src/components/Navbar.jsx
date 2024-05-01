import React from 'react'
import { NavLink } from 'react-router-dom'
import { useSelector } from 'react-redux'

const Navbar = () => {
    const state = useSelector(state => state.handleCart)
    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark py-3 sticky-top">
            <div className="container">
                <NavLink className="navbar-brand fw-bold fs-4 px-2" to="/">ACADEMIA</NavLink>
                <button className="navbar-toggler mx-2" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <div className="buttons text-center">
                        <NavLink to="/students" className="btn btn-dark m-2">Manage Students</NavLink>
                        <NavLink to="/faculty" className="btn btn-dark m-2">Manage Faculty</NavLink>
                        <NavLink to="/courses" className="btn btn-dark m-2">Manage Courses</NavLink>
                        <NavLink to="/login" className="btn btn-dark m-2">Profile</NavLink>
                    </div>
                </div>


            </div>
        </nav>
    )
}

export default Navbar