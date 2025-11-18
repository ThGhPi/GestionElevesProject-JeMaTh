// src/pages/ListeEnseignements.jsx
import { useState } from "react";
import Navbar from "../components/Navbar";

export default function ListeEnseignements() {

    // ⚠️ Données TEMPORAIRES (remplacées par l’API plus tard)
    const [enseignements, setEnseignements] = useState([
        {
            id: 1,
            matiere: "Mathématiques",
            professeurs: ["Jean Dupont", "Claire Martin"],
            classe: "5B"
        },
        {
            id: 2,
            matiere: "Histoire",
            professeurs: ["Laurent Bernard"],
            classe: "3A"
        }
    ]);

    // Liste de classes — temporaire (API plus tard)
    const classes = [
        "Tout",
        "6A", "6B", "6C",
        "5A", "5B", "5C",
        "4A", "4B", "4C",
        "3A", "3B", "3C"
    ];

    // Classe filtrée
    const [classeFiltre, setClasseFiltre] = useState("");

    // Filtrage des enseignements
    const enseignementsFiltres = classeFiltre
        ? enseignements.filter((e) => e.classe === classeFiltre)
        : enseignements;

    return (
        <>
            <Navbar />
            <div className="max-w-6xl mx-auto mt-10 flex gap-10 pb-20">

                {/* Colonne de gauche : Search + Buttons + tableau */}
                <div className="flex-1">

                    {/* 🔍 Barre de recherche + Boutons */}
                    <div className="flex items-center mb-6">

                        {/* Barre de recherche */}
                        <input
                            type="text"
                            placeholder="🔍 Search"
                            className="border-2 border-black rounded-md px-4 py-2 w-72 focus:outline-none shadow"
                        />

                        <div className="ml-8 flex gap-6">
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


                    {/* TABLEAU ENSEIGNEMENTS */}
                    <div className="border-4 border-black rounded-md w-full overflow-x-auto mt-6">
                        <table className="w-full text-left border-collapse">
                            <thead className="bg-gray-200 border-b-4 border-black">
                            <tr>
                                <th className="text-black py-3 px-4 border-r-4 border-black">Matière</th>
                                <th className="text-black py-3 px-4">Professeur(s)</th>
                            </tr>
                            </thead>

                            <tbody>
                            {enseignementsFiltres.length === 0 ? (
                                <tr>
                                    <td
                                        colSpan="2"
                                        className="text-center py-6 text-gray-500 border-t-4 border-black"
                                    >
                                        Aucun enseignement trouvé.
                                    </td>
                                </tr>
                            ) : (
                                enseignementsFiltres.map((ens) => (
                                    <tr
                                        key={ens.id}
                                        className="border-t-4 border-black h-16 hover:bg-gray-100 transition"
                                    >
                                        <td className="px-4 border-r-4 border-black text-black">
                                            {ens.matiere}
                                        </td>

                                        <td className="px-4 text-black">
                                            {ens.professeurs.join(", ")}
                                        </td>
                                    </tr>
                                ))
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>

                {/* Colonne de droite : Liste des classes */}
                <div className="w-32 border-2 border-black rounded-md">
                    <select
                        size={classes.length}
                        className="w-full h-full outline-none"
                        onChange={(e) =>
                            setClasseFiltre(e.target.value === "Tout" ? "" : e.target.value)
                        }
                    >
                        {classes.map((cl) => (
                            <option key={cl} value={cl}>
                                {cl}
                            </option>
                        ))}
                    </select>
                </div>

            </div>

        </>
    );
}
