// src/pages/AjoutUtilisateur.jsx
import Navbar from "../components/Navbar";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function AjoutUtilisateur() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        nom: "",
        prenom: "",
        email: "",
        pseudo: "",
        telephone: "",
        adresse: "",
        role: ""
    });

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleReset = () => {
        setFormData({
            nom: "",
            prenom: "",
            email: "",
            pseudo: "",
            telephone: "",
            adresse: "",
            role: ""
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Nouvel utilisateur :", formData);
        // API plus tard
    };

    return (
        <>
            <Navbar />
            <div className="max-w-3xl mx-auto mt-10 pb-20 border-4 border-black rounded-md p-10">

                <h1 className="text-3xl font-bold text-center mb-10 text-black">
                    Ajouter un utilisateur
                </h1>

                <form onSubmit={handleSubmit} className="space-y-6">

                    {/* INPUT GROUP */}
                    {[
                        ["nom", "Nom"],
                        ["prenom", "Prénom"],
                        ["email", "Email"],
                        ["pseudo", "Pseudo"],
                        ["telephone", "Téléphone"],
                        ["adresse", "Adresse"],
                        ["role", "Role"]
                    ].map(([name, label]) => (
                        <div key={name} className="flex items-center">
                            <label className="w-40 text-black">{label}</label>
                            <input
                                type="text"
                                name={name}
                                value={formData[name]}
                                onChange={handleChange}
                                className="flex-1 border-2 border-gray-500 rounded-md px-3 py-2 focus:outline-none"
                            />
                        </div>
                    ))}

                    {/* BUTTONS */}
                    <div className="flex justify-center gap-10 mt-10">

                        <button
                            type="submit"
                            className="bg-green-600 hover:bg-green-700 text-white font-bold px-8 py-3 rounded-md border-2 border-black shadow"
                        >
                            Ajouter
                        </button>

                        <button
                            type="button"
                            onClick={handleReset}
                            className="bg-blue-600 hover:bg-blue-700 text-white font-bold px-8 py-3 rounded-md border-2 border-black shadow"
                        >
                            Réinitialiser
                        </button>

                        <button
                            type="button"
                            onClick={() => navigate("/utilisateurs")}
                            className="bg-red-600 hover:bg-red-700 text-white font-bold px-8 py-3 rounded-md border-2 border-black shadow"
                        >
                            Annuler
                        </button>
                    </div>
                </form>
            </div>
        </>
    );
}
