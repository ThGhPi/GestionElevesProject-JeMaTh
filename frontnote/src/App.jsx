import { useState } from 'react'
import { BrowserRouter, Routes, Route } from 'react-router';
import LoginView from './view/LoginView';
import './App.css'

import Accueil from "./pages/Accueil.jsx";
import ListeProfesseurs from "./pages/ListeProfesseurs.jsx";
import ListeEnseignements from "./pages/ListeEnseignements.jsx";
import ListeEleves from "./pages/ListeEleves.jsx";
import ListeUtilisateurs from "./pages/ListeUtilisateurs.jsx";
import AjoutUtilisateur from "./pages/AjoutUtilisateur.jsx";

export default function App() {
    return (
      <BrowserRouter>

        <Routes>
            <Route path='/connexion' element={<LoginView />} />
            <Route path="/" element={<Accueil />} />
            <Route path="/professeurs" element={<ListeProfesseurs />} />
            <Route path="/enseignements" element={<ListeEnseignements />} />
            <Route path="/eleves" element={<ListeEleves />} />
            <Route path="/utilisateurs" element={<ListeUtilisateurs />} />
            <Route path="/utilisateurs/ajouter" element={<AjoutUtilisateur />} />
        </Routes>
      </BrowserRouter>
    );
  }
