import React from 'react';
import { Search } from 'lucide-react';

/**
 * Barre de recherche avec icône
 * @param {Object} props
 * @param {string} props.value - Valeur actuelle du champ
 * @param {Function} props.onChange - Fonction appelée lors du changement de valeur
 * @param {string} props.placeholder - Texte placeholder (défaut: 'Search')
 * @param {string} props.maxWidth - Largeur maximale (défaut: '800px')
 */
function SearchBar({ 
  value, 
  onChange, 
  placeholder = 'Search', 
  maxWidth = '800px' 
}) {
  return (
    <div style={{ maxWidth: maxWidth, marginBottom: '2rem' }}>
      <div style={{ position: 'relative' }}>
        <Search 
          style={{ 
            position: 'absolute', 
            left: '1rem', 
            top: '50%', 
            transform: 'translateY(-50%)', 
            color: '#9ca3af' 
          }} 
          size={28} 
        />
        <input
          type="text"
          placeholder={placeholder}
          value={value}
          onChange={(e) => onChange && onChange(e.target.value)}
          style={{
            width: '100%',
            paddingLeft: '3.5rem',
            paddingRight: '1.5rem',
            paddingTop: '1.25rem',
            paddingBottom: '1.25rem',
            fontSize: '1.25rem',
            border: '2px solid #d1d5db',
            borderRadius: '0.75rem',
            outline: 'none',
            backgroundColor: 'white',
            transition: 'border-color 0.2s, box-shadow 0.2s'
          }}
          onFocus={(e) => {
            e.currentTarget.style.borderColor = '#3b82f6';
            e.currentTarget.style.boxShadow = '0 0 0 3px rgba(59, 130, 246, 0.1)';
          }}
          onBlur={(e) => {
            e.currentTarget.style.borderColor = '#d1d5db';
            e.currentTarget.style.boxShadow = 'none';
          }}
        />
      </div>
    </div>
  );
}

export default SearchBar;