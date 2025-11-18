// src/pages/ListeUtilisateurs.jsx
import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import UserService from '../service/UserService.js';
import { useNavigate } from "react-router-dom";

export default function ListeUtilisateurs() {

    const [searchText, setSearchText] = useState("");
    const navigate = useNavigate();

    const [utilisateurs, setUtilisateurs] = useState([]);
    const [chargement, setChargement] = useState(false);
    const [error, setError] = useState(null);
    const [deleted, setDeleted] = useState([]);

    const getAll = () => {
        setChargement(true);
        setError(null);
        UserService.getAll()
            .then(response => {
                console.log(response);
                setUtilisateurs([...response.data])
            })
            .catch(erreur => {
                console.log(erreur);
                setError("Erreur lors du chargement de la liste des utilisateurs !")
            })
            .finally(() => { setChargement(false); });
    }

    useEffect(() => getAll(), []);

    

    return (

<>
        <Navbar />
            <div className="max-w-6xl mx-auto mt-10 pb-20">

                {/* 🔍 Barre de recherche + Boutons */}
                <div className="flex items-center justify-between mb-6">

                    {/* Barre de recherche */}
                    <input
                        type="text"
                        placeholder="🔍 Search"
                        className="
                            border-2 border-black rounded-md px-4 py-2
                            w-72 focus:outline-none shadow
                        "
                    />

                    {/* Boutons */}
                    <div className="flex gap-6">

                        {/* Ajouter → navigue vers /utilisateurs/ajouter */}
                        <button
                            className="bg-green-600 hover:bg-green-700 text-white font-bold px-6 py-2 rounded-md border-2 border-black shadow"
                            onClick={() => navigate("/utilisateurs/ajouter")}
                        >
                            Ajouter
                        </button>

                        <button className="bg-blue-600 hover:bg-blue-700 text-white font-bold px-6 py-2 rounded-md border-2 border-black shadow">
                            Modifier
                        </button>

                        <button className="bg-red-600 hover:bg-red-700 text-white font-bold px-6 py-2 rounded-md border-2 border-black shadow">
                            Supprimer
                        </button>
                    </div>
                </div>

                {/* TABLEAU UTILISATEURS */}
                <div className="border-4 border-black rounded-md w-full overflow-x-auto mt-6">
                    <table className="w-full text-left border-collapse">
                        <thead className="bg-gray-200 border-b-4 border-black">
                        <tr>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Nom</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Prénom</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Email</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Pseudo</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Téléphone</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Adresse</th>
                            <th className="text-black py-3 px-4">Role</th>
                        </tr>
                        </thead>

                        <tbody>
                        {utilisateurs.length === 0 ? (
                            <tr>
                                <td
                                    colSpan="7"
                                    className="text-center py-6 text-gray-500 border-t-4 border-black"
                                >
                                    Aucun utilisateur pour le moment.
                                </td>
                            </tr>
                        ) : (
                            utilisateurs.map((user) => (
                                <tr
                                    key={user.id}
                                    className="border-t-4 border-black h-16 hover:bg-gray-100 transition"
                                >
                                    <td className="px-4 border-r-4 border-black text-black">{user.nom}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{user.prenom}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{user.email}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{user.pseudo}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{user.telephone}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{user.adresse}</td>
                                    <td className="px-4 text-black">{user.role}</td>
                                </tr>
                            ))
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
</>
        
    );
}
