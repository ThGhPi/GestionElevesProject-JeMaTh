// src/pages/ListeProfesseurs.jsx
import { useState } from "react";
import Navbar from "../components/Navbar";

export default function ListeProfesseurs() {

    // ⚠️ Données TEMPORAIRES (remplacées plus tard par l’API)
    const [professeurs, setProfesseurs] = useState([
        {
            id: 1,
            nom: "Martin",
            prenom: "Sophie",
            email: "sophie.martin@ecole.com",
            matieres: ["Maths", "Physique"],
            classePrincipale: "3A"
        }
    ]);

    return (
        <>
            <Navbar />
            {/* Contenu principal */}
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
                        <button className="bg-green-600 hover:bg-green-700 text-white font-bold px-6 py-2 rounded-md border-2 border-black shadow">
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

                {/* TABLEAU PROFESSEURS */}
                <div className="border-4 border-black rounded-md w-full overflow-x-auto mt-6">
                    <table className="w-full text-left border-collapse">
                        <thead className="bg-gray-200 border-b-4 border-black">
                        <tr>
                            <th className="text-black py-3 px-4 border-r-4 border-black">id</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Nom</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Prénom</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Email</th>
                            <th className="text-black py-3 px-4 border-r-4 border-black">Matières</th>
                            <th className="text-black py-3 px-4">Classe principale</th>
                        </tr>
                        </thead>

                        <tbody>
                        {professeurs.length === 0 ? (
                            <tr>
                                <td
                                    colSpan="6"
                                    className="text-center py-6 text-gray-500 border-t-4 border-black"
                                >
                                    Aucun professeur pour le moment.
                                </td>
                            </tr>
                        ) : (
                            professeurs.map((prof) => (
                                <tr
                                    key={prof.id}
                                    className="border-t-4 border-black h-16 hover:bg-gray-100 transition"
                                >
                                    <td className="px-4 border-r-4 border-black text-black">{prof.id}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{prof.nom}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{prof.prenom}</td>
                                    <td className="px-4 border-r-4 border-black text-black">{prof.email}</td>
                                    <td className="px-4 border-r-4 border-black text-black">
                                        {prof.matieres.join(", ")}
                                    </td>
                                    <td className="px-4 text-black">{prof.classePrincipale}</td>
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
