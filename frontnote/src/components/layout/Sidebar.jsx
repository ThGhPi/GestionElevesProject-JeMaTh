import React from 'react';

/**
 * Sidebar avec liste d'items sélectionnables
 * @param {Object} props
 * @param {Array<string>} props.items - Liste des items à afficher
 * @param {string} props.selectedItem - Item actuellement sélectionné
 * @param {Function} props.onSelect - Fonction appelée lors de la sélection d'un item
 * @param {string} props.width - Largeur de la sidebar (défaut: '280px')
 */
function Sidebar({ items = [], selectedItem, onSelect, width = '280px' }) {
  return (
    <aside style={{ 
      width: width, 
      backgroundColor: 'white', 
      borderRight: '4px solid #d1d5db', 
      boxShadow: '2px 0 8px rgba(0,0,0,0.1)',
      overflowY: 'auto',
      padding: '1.5rem'
    }}>
      {items.map((item) => {
        const isSelected = selectedItem === item;
        
        return (
          <button
            key={item}
            onClick={() => onSelect && onSelect(item)}
            style={{
              width: '100%',
              textAlign: 'left',
              padding: '1rem 1.5rem',
              marginBottom: '0.75rem',
              fontSize: '1.5rem',
              fontWeight: '700',
              borderRadius: '0.75rem',
              border: isSelected ? 'none' : '2px solid #d1d5db',
              backgroundColor: isSelected ? '#000' : '#fff',
              color: isSelected ? '#fff' : '#1f2937',
              cursor: 'pointer',
              transition: 'all 0.2s',
              boxShadow: isSelected ? '0 4px 6px rgba(0,0,0,0.2)' : 'none'
            }}
            onMouseEnter={(e) => {
              if (!isSelected) {
                e.currentTarget.style.backgroundColor = '#f3f4f6';
              }
            }}
            onMouseLeave={(e) => {
              if (!isSelected) {
                e.currentTarget.style.backgroundColor = '#fff';
              }
            }}
          >
            {item}
          </button>
        );
      })}
    </aside>
  );
}

export default Sidebar;
