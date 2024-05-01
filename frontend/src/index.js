import React from 'react';
import ReactDOM from 'react-dom/client';
import '../node_modules/font-awesome/css/font-awesome.min.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './redux/store';

import { Login, Register, PageNotFound, Profile } from "./pages"
import StudentsPage from './pages/StudentsPage';
import FacultyPage from './pages/FacultyPage';
import CoursePage from './pages/CoursesPage';
import AddSpecPage from './pages/AddSpecPage';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Provider store={store}>
      <Routes>
        <Route path="/" element={<FacultyPage />} />
        <Route path="/students" element={<StudentsPage />} />
        <Route path="/courses" element={<CoursePage />} />
        <Route path="/faculty" element={<FacultyPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/addSpecialization" element={<AddSpecPage />} />
        <Route path="*" element={<PageNotFound />} />
        
      </Routes>
    </Provider>
  </BrowserRouter>
);