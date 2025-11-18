// src/components/Header.jsx
import logo from "../assets/images/FrontNote.png";
import logoutIcon from "../assets/images/Déconnexion.png";
import { useNavigate } from "react-router";

export default function Header() {
    const navigate = useNavigate();
    const handleDisconnect = () => {
        navigate("/connexion")
    }

    return (
        <div className="relative w-full flex justify-center py-4 bg-white">

            {/* Logo centré */}
            <img src={logo} alt="FrontNote" className="h-44" />

            {/* Bouton Déconnexion en haut à droite */}
            <button onClick={handleDisconnect}
                className="absolute right-6 top-6 flex items-center gap-2 bg-black text-white rounded-xl shadow hover:bg-gray-800 transition"
            >
                <img src={logoutIcon} alt="logout" className="w-5" />
                Se déconnecter
            </button>
        </div>
    );
}
