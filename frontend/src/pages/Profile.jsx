import React, { useEffect, useState } from "react";
import { Footer, Navbar } from "../components";

const Profile = () => {
    const [profile, setProfile] = useState([]);
    useEffect(() => {
        const getProfile = async () => {
          const response = await fetch(`http://localhost:5000/api/v1/profileData`);
          const data = await response.json();
          setProfile(data);
        };
        getProfile();
    }, [profile]
);
  return (
    <>
      <Navbar />
        <div className="container my-3 py-3">
            
            <div className='row'>
                <h1 className="py-4">Profile</h1>
            </div>
            <div className='row mx-auto'>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <td colSpan={2}>
                                <img src={profile.image} height={100} width={100} alt="Profile"></img>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">Name</th>
                            <td>{profile.name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Email</th>
                            <td>{profile.email}</td>
                        </tr>
                        <tr>
                            <th scope="row">Phone</th>
                            <td>{profile.phone}</td>
                        </tr>
                        <tr>
                            <th scope="row">Past Orders</th>
                            <td><p class="mb-1">See more</p></td>
                        </tr>
                    </tbody>
                    </table>
            </div>

        </div>
      <Footer />
    </>
  )
}

export default Profile