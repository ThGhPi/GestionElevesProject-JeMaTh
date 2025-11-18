// src/components/Navbar.jsx
import { useNavigate } from "react-router-dom";

import accueilIcon from "../assets/images/Accueil.png";
import contactIcon from "../assets/images/Contact.png";

export default function Navbar() {
    const navigate = useNavigate();




    return (
        <div className="w-full bg-[#3C4EC5] border-y-4 border-black">
            <div className="flex justify-evenly">

                {/* Accueil */}
                <div
                    className="flex justify-center items-center border-r-4 border-black cursor-pointer hover:bg-[#5A6DE0] transition w-1/3"
                    onClick={() => navigate("/")}
                >
                    <NavItem icon={accueilIcon} label="Accueil" />
                </div>

                {/* Mon Compte */}
                <div className="flex justify-center items-center cursor-pointer hover:bg-[#5A6DE0] transition w-1/3">
                    <NavItem icon={compteIcon} label="Mon Compte" />
                </div>

                {/* Contact */}
                <div className="flex justify-center items-center border-l-4 border-black cursor-pointer hover:bg-[#5A6DE0] transition w-1/3">
                    <NavItem icon={contactIcon} label="Contact" />
                </div>

            </div>
        </div>
    );
}

function NavItem({ icon, label }) {
    return (
        <div className="flex items-center gap-2 py-3 font-bold text-white text-lg">
            <img src={icon} alt={label} className="w-8 h-8" />
            {label}
        </div>
    );
}
