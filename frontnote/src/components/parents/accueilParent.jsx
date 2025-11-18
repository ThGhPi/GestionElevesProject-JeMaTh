import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { parentAPI } from "../../services/api"; 
import authService from "../../services/auth";

function AcceuilParent() {
  const navigate = useNavigate();
  const [children, setChildren] = useState([]);

  const parentId = authService.getUser()?.id;

useEffect(() => {
  async function load() {
    try {
      if (!parentId) return;
      
      const data = await parentAPI.getChildren(parentId);
      setChildren(data);
    } catch (err) {
      console.error("Erreur fetch enfants:", err);
    }
  }

  load();
}, [parentId]);


  return (
    <div className="flex flex-wrap gap-6 p-8">
      {children.map(student => (
        <div 
          key={student.id} 
          className="p-6 rounded-xl shadow bg-white w-64 border border-gray-200"
        >
          <h3 className="text-xl font-bold text-gray-800">
            {student.firstname} {student.lastname}
          </h3>

          <button
            className="mt-4 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
            onClick={() => navigate(`/classes/${student.id}`)}
          >
            Voir le détail
          </button>
        </div>
      ))}
    </div>
  );
}

export default AcceuilParent;
