// src/App.jsx
import { Routes, Route } from "react-router-dom";
import Accueil from "./pages/Accueil.jsx";
import ListeProfesseurs from "./pages/ListeProfesseurs.jsx";
import ListeEnseignements from "./pages/ListeEnseignements.jsx";
import ListeEleves from "./pages/ListeEleves.jsx";
import ListeUtilisateurs from "./pages/ListeUtilisateurs.jsx";
import AjoutUtilisateur from "./pages/AjoutUtilisateur.jsx";
import LoginView from "./view/LoginView.jsx"
import Header from "./components/Header.jsx";

export default function App() {
    return (
        <div className="min-h-screen bg-white flex flex-col">

            <Header />
            <Routes>
                <Route path="/" element={<Accueil />} />
                <Route path="/connexion" element={<LoginView />} />
                <Route path="/professeurs" element={<ListeProfesseurs />} />
                <Route path="/enseignements" element={<ListeEnseignements />} />
                <Route path="/eleves" element={<ListeEleves />} />
                <Route path="/utilisateurs" element={<ListeUtilisateurs />} />
                <Route path="/utilisateurs/ajouter" element={<AjoutUtilisateur />} />
            </Routes>
        </div>
    );
}