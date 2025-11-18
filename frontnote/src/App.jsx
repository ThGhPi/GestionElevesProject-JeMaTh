// src/App.jsx
import { Routes, Route } from "react-router-dom";
import Accueil from "./pages/Accueil.jsx";
import ListeProfesseurs from "./pages/ListeProfesseurs.jsx";
import ListeEnseignements from "./pages/ListeEnseignements.jsx";
import ListeEleves from "./pages/ListeEleves.jsx";
import ListeUtilisateurs from "./pages/ListeUtilisateurs.jsx";
import AjoutUtilisateur from "./pages/AjoutUtilisateur.jsx";

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Accueil />} />
            <Route path="/professeurs" element={<ListeProfesseurs />} />
            <Route path="/enseignements" element={<ListeEnseignements />} />
            <Route path="/eleves" element={<ListeEleves />} />
            <Route path="/utilisateurs" element={<ListeUtilisateurs />} />
            <Route path="/utilisateurs/ajouter" element={<AjoutUtilisateur />} />
        </Routes>
    );
}