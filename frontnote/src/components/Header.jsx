// src/components/Header.jsx
import logo from "../assets/images/FrontNote.png";
import { useNavigate } from "react-router-dom";
import { Power } from 'lucide-react';

/**
 * Header de l'application avec logo et bouton déconnexion
 * @param {Object} props
 * @param {Function} props.onLogout - Fonction appelée lors du clic sur déconnexion
 */
export default function Header({onLogout}) {
    const navigate = useNavigate();
    const handleDisconnect = () => {
        navigate("/connexion")
    }

    return (
        <div className="relative w-full flex justify-center py-4 bg-white">

            {/* Logo centré */}
            <img src={logo} alt="FrontNote" className="h-44" />

            {/* Bouton Déconnexion en haut à droite */}
        <button 
          onClick={onLogout}
          style={{ 
            display: 'flex', 
            alignItems: 'center', 
            gap: '0.5rem', 
            color: '#6b7280', 
            fontSize: '1.125rem', 
            background: 'none', 
            border: 'none', 
            cursor: 'pointer',
            transition: 'color 0.2s'
          }}
          onMouseEnter={(e) => e.currentTarget.style.color = '#374151'}
          onMouseLeave={(e) => e.currentTarget.style.color = '#6b7280'}
        >
          <Power size={24} />
          <span>Se déconnecter</span>
        </button>
        </div>
    );
}
