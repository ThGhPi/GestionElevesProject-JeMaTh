import React from "react";
import { Navigate } from "react-router-dom";
import authService from "../../services/auth.js";

/**
 * Route protégée :
 * - vérifie l'authentification
 * - vérifie si le token est expiré
 * - redirige vers /login si nécessaire
 */
export default function ProtectedRoute({ children }) {
  const isAuthenticated = authService.isAuthenticated();
  const isTokenExpired = authService.isTokenExpired();

  // Conditions de refus
  if (!isAuthenticated || isTokenExpired) {

    if (isTokenExpired) {
      authService.logout(); // Nettoyer le token expiré
    }

    return <Navigate to="/login" replace />;
  }

  return <>{children}</>;
}
