
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Accueil from "./pages/Accueil.jsx";
import ListeProfesseurs from "./pages/ListeProfesseurs.jsx";
import ListeEnseignements from "./pages/ListeEnseignements.jsx";
import ListeEleves from "./pages/ListeEleves.jsx";
import ListeUtilisateurs from "./pages/ListeUtilisateurs.jsx";
import AjoutUtilisateur from "./pages/AjoutUtilisateur.jsx";
import Header from "./components/Header.jsx";

export default function App() {
    return (

        <div className="min-h-screen bg-white flex flex-col">

            <Header />
            <BrowserRouter>

                <Routes>
                    <Route
                        path="/parent"
                        element={
                            <ProtectedRoute>
                                <AccueilParent />
                            </ProtectedRoute>
                        }
                    />

                    <Route
                        path="/classes/:studentId"
                        element={
                            <ProtectedRoute>
                                <ClassGroupPage />
                            </ProtectedRoute>
                        }
                    />

                    <Route path="*" element={<Navigate to="/connexion" replace />} />
                    <Route path="/" element={<Accueil />} />
                    <Route path="/connexion" element={<LoginPage />} />
                    <Route path="/professeurs" element={<ListeProfesseurs />} />
                    <Route path="/enseignements" element={<ListeEnseignements />} />
                    <Route path="/eleves" element={<ListeEleves />} />
                    <Route path="/utilisateurs" element={<ListeUtilisateurs />} />
                    <Route path="/utilisateurs/ajouter" element={<AjoutUtilisateur />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}


