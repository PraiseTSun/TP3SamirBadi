import React from "react";
import {Route, Routes} from "react-router-dom";
import HomePage from "./components/HomePage";
import './App.css';
import Header from "./components/Header";
import Client from "./components/Client";
import Employe from "./components/Employe";
import Administrator from "./components/Administrator";
import PageNotFound from "./components/PageNotFound";

function App() {
  return (
      <div >
        <header >
          <h1>Javatown</h1>
          <Header/>
        </header>

        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/client" element={<Client/>} />
          <Route path="/employe" element={<Employe/>} />
          <Route path="/administrator" element={<Administrator/>} />
          <Route path="*" element={<PageNotFound/>} />
        </Routes>
      </div>
  );
}

export default App;
