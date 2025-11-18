import React from 'react';

/**
 * Logo FRONTNOTE avec icône livre et texte multicolore
 * @param {Object} props
 * @param {number} props.size - Taille du logo (défaut: 64)
 * @param {number} props.fontSize - Taille du texte en rem (défaut: 4)
 */
function Logo({ size = 64, fontSize = 4 }) {
  return (
    <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
      {/* Icône livre avec plume */}
      <div style={{ width: `${size}px`, height: `${size}px` }}>
        <svg viewBox="0 0 100 100" style={{ width: '100%', height: '100%' }}>
          <path d="M15 25 L15 80 L45 70 L45 15 Z" fill="#fbbf24" stroke="#92400e" strokeWidth="2"/>
          <path d="M45 15 L45 70 L75 80 L75 25 Z" fill="#3b82f6" stroke="#1e3a8a" strokeWidth="2"/>
          <path d="M10 25 L15 25 L15 80 L10 82 Z" fill="#dc2626"/>
          <path d="M75 25 L80 25 L80 82 L75 80 Z" fill="#dc2626"/>
          <path d="M60 10 Q65 5 70 15 L60 40 Z" fill="#dc2626" stroke="#7f1d1d" strokeWidth="1"/>
        </svg>
      </div>
      
      {/* Texte FRONTNOTE multicolore */}
      <h1 style={{ fontSize: `${fontSize}rem`, fontWeight: '900', display: 'flex', letterSpacing: '-0.025em' }}>
        <span style={{ color: '#b91c1c' }}>F</span>
        <span style={{ color: '#dc2626' }}>R</span>
        <span style={{ color: '#eab308' }}>O</span>
        <span style={{ color: '#60a5fa' }}>N</span>
        <span style={{ color: '#b91c1c' }}>T</span>
        <span style={{ color: '#eab308' }}>N</span>
        <span style={{ color: '#2563eb' }}>O</span>
        <span style={{ color: '#b91c1c' }}>T</span>
        <span style={{ color: '#dc2626' }}>E</span>
      </h1>
    </div>
  );
}

export default Logo;