import React from 'react';
import { Home, User, Mail } from 'lucide-react';

/**
 * Barre de navigation avec onglets
 * @param {Object} props
 * @param {string} props.activeTab - Onglet actif ('accueil' | 'compte' | 'contact')
 * @param {Function} props.onTabChange - Fonction appelée lors du changement d'onglet
 */
function Navigation({ activeTab = 'accueil', onTabChange }) {
  const tabs = [
    { id: 'accueil', label: 'Accueil', icon: Home },
    { id: 'compte', label: 'Mon Compte', icon: User },
    { id: 'contact', label: 'Contact', icon: Mail }
  ];

  const handleTabClick = (tabId) => {
    if (onTabChange) {
      onTabChange(tabId);
    }
  };

  return (
    <nav style={{ 
      backgroundColor: '#1d4ed8', 
      boxShadow: '0 4px 6px rgba(0,0,0,0.1)' 
    }}>
      <div style={{ display: 'flex' }}>
        {tabs.map((tab, index) => {
          const Icon = tab.icon;
          const isActive = activeTab === tab.id;
          
          return (
            <button
              key={tab.id}
              onClick={() => handleTabClick(tab.id)}
              style={{ 
                flex: 1, 
                display: 'flex', 
                alignItems: 'center', 
                justifyContent: 'center', 
                gap: '0.75rem', 
                padding: '1rem', 
                color: 'white', 
                fontSize: '1.25rem', 
                fontWeight: '600',
                backgroundColor: isActive ? '#1e40af' : 'transparent',
                border: 'none',
                borderRight: index < tabs.length - 1 ? '1px solid #2563eb' : 'none',
                cursor: 'pointer',
                transition: 'background-color 0.2s'
              }}
              onMouseEnter={(e) => !isActive && (e.currentTarget.style.backgroundColor = '#1e40af')}
              onMouseLeave={(e) => !isActive && (e.currentTarget.style.backgroundColor = 'transparent')}
            >
              <Icon size={24} />
              {tab.label}
            </button>
          );
        })}
      </div>
    </nav>
  );
}

export default Navigation;