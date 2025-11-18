import React from 'react';

/**
 * Tableau réutilisable avec headers et données
 * @param {Object} props
 * @param {Array<Object>} props.columns - Définition des colonnes [{key: string, label: string, render?: Function}]
 * @param {Array<Object>} props.data - Données du tableau
 * @param {Function} props.onRowClick - Fonction appelée lors du clic sur une ligne
 * @param {string} props.emptyMessage - Message si tableau vide (défaut: 'Aucune donnée disponible')
 */
function Table({ 
  columns = [], 
  data = [], 
  onRowClick,
  emptyMessage = 'Aucune donnée disponible' 
}) {
  return (
    <div style={{ 
      backgroundColor: 'white', 
      borderRadius: '1rem', 
      boxShadow: '0 10px 25px rgba(0,0,0,0.15)', 
      border: '2px solid #d1d5db',
      overflow: 'hidden'
    }}>
      <table style={{ width: '100%', borderCollapse: 'collapse' }}>
        {/* En-têtes */}
        <thead>
          <tr style={{ backgroundColor: '#f3f4f6', borderBottom: '2px solid #d1d5db' }}>
            {columns.map((col) => (
              <th 
                key={col.key}
                style={{ 
                  textAlign: 'left', 
                  padding: '1.5rem 2rem', 
                  fontSize: '1.5rem', 
                  fontWeight: '700', 
                  color: '#1f2937' 
                }}
              >
                {col.label}
              </th>
            ))}
          </tr>
        </thead>
        
        {/* Corps */}
        <tbody>
          {data.length === 0 ? (
            <tr>
              <td 
                colSpan={columns.length} 
                style={{ 
                  padding: '3rem 2rem', 
                  textAlign: 'center', 
                  color: '#9ca3af', 
                  fontSize: '1.25rem' 
                }}
              >
                {emptyMessage}
              </td>
            </tr>
          ) : (
            data.map((row, index) => (
              <tr 
                key={row.id || index}
                style={{ 
                  borderBottom: '1px solid #e5e7eb',
                  transition: 'background-color 0.2s'
                }}
                onMouseEnter={(e) => e.currentTarget.style.backgroundColor = '#f9fafb'}
                onMouseLeave={(e) => e.currentTarget.style.backgroundColor = 'transparent'}
              >
                {columns.map((col) => (
                  <td 
                    key={col.key}
                    onClick={() => !col.render && onRowClick && onRowClick(row)}
                    style={{ 
                      padding: '1.5rem 2rem', 
                      fontSize: '1.125rem', 
                      color: '#4b5563',
                      cursor: !col.render && onRowClick ? 'pointer' : 'default'
                    }}
                  >
                    {/* Si la colonne a une fonction render custom, l'utiliser */}
                    {col.render ? col.render(row) : row[col.key]}
                  </td>
                ))}
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}

export default Table;
