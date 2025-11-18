// src/pages/ListeEleves.jsx
import { useState } from "react";
import Header from "../components/Header";
import Navbar from "../components/Navbar";

export default function ListeEleves() {

    // ⚠️ Données TEMPORAIRES (API plus tard)
    const [eleves, setEleves] = useState([
        {
            id: 1,
            nom: "Durand",
            prenom: "Lucas",
            dateNaissance: "2010-05-12",
            classe: "5B"
        },
        {
            id: 2,
            nom: "Petit",
            prenom: "Emma",
            dateNaissance: "2011-09-03",
            classe: "3A"
        }
    ]);

    // Liste de classes (temporaire)
    const classes = [
        "Tout",
        "6A", "6B", "6C",
        "5A", "5B", "5C",
        "4A", "4B", "4C",
        "3A", "3B", "3C"
    ];

    // Classe filtrée
    const [classeFiltre, setClasseFiltre] = useState("");

    // Filtrage
    const elevesFiltres = classeFiltre
        ? eleves.filter((e) => e.classe === classeFiltre)
        : eleves;

    return (
        <div className="min-h-screen bg-white flex flex-col">

            <Header />
            <Navbar />

            <div className="max-w-6xl mx-auto mt-10 flex gap-10 pb-20">

                {/* Colonne gauche */}
                <div className="flex-1">

                    {/* Barre de recherche + boutons */}
                    <div className="flex items-center mb-6">

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

                    {/* TABLEAU ÉLÈVES */}
                    <div className="border-4 border-black rounded-md w-full overflow-x-auto mt-6">
                        <table className="w-full text-left border-collapse">
                            <thead className="bg-gray-200 border-b-4 border-black">
                            <tr>
                                <th className="text-black py-3 px-4 border-r-4 border-black">id</th>
                                <th className="text-black py-3 px-4 border-r-4 border-black">Nom</th>
                                <th className="text-black py-3 px-4 border-r-4 border-black">Prénom</th>
                                <th className="text-black py-3 px-4 border-r-4 border-black">Date de naissance</th>
                                <th className="text-black py-3 px-4">Classe</th>
                            </tr>
                            </thead>

                            <tbody>
                            {elevesFiltres.length === 0 ? (
                                <tr>
                                    <td
                                        colSpan="5"
                                        className="text-center py-6 text-gray-500 border-t-4 border-black"
                                    >
                                        Aucun élève trouvé.
                                    </td>
                                </tr>
                            ) : (
                                elevesFiltres.map((el) => (
                                    <tr
                                        key={el.id}
                                        className="border-t-4 border-black h-16 hover:bg-gray-100 transition"
                                    >
                                        <td className="px-4 border-r-4 border-black text-black">{el.id}</td>
                                        <td className="px-4 border-r-4 border-black text-black">{el.nom}</td>
                                        <td className="px-4 border-r-4 border-black text-black">{el.prenom}</td>
                                        <td className="px-4 border-r-4 border-black text-black">{el.dateNaissance}</td>
                                        <td className="px-4 text-black">{el.classe}</td>
                                    </tr>
                                ))
                            )}
                            </tbody>
                        </table>
                    </div>
                </div>

                {/* Colonne droite : liste des classes */}
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
        </div>
    );
}
