import React from 'react'
import loginService from '../service/LoginService';


const LoginView = () => {

    const [error, setError] = useState(null);
    const [validated, setValidated] = useState(false);

    const validateSubmit = (event) => {
        event.preventDefault();
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.stopPropagation();
        } else { handleSubmit(event) }
        setValidated(true);
    }

    const handleSubmit = (e) => {
        setError(null);
        const form = e.currentTarget;
        const loginData = {};
        loginData.username = form.username.value;
        loginData.password = form.password.value;
        loginService.logAppUser(loginData)
            .then(response => {
                useNavigate;
            })
            .catch(erreur => {
                console.log(erreur);
                setError("Identifiant ou mot de passe erroné !")
            })
            .finally(() => {
                if (updatedPerson === null) {
                    setError("Erreur du chargement de la mis à jour !")
                } else { location.replace("/persons") }
            });
    }

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
            <form onSubmit={handleSubmit}
                className="bg-white p-6 rounded-2xl shadow-md w-full max-w-sm space-y-4">
                <h2 className="text-xl font-semibold text-gray-700">Connexion à <span className='italic'>frontnote</span></h2>
                <div>
                    <label className="block text-sm font-medium text-gray-600 mb-1">Identifiant *</label>
                    <input type="text" name="username" value={formData.username} onChange={handleChange}
                        className="w-full p-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-200"
                        placeholder="identifiant" required />
                </div>

                <div>
                    <label className="block text-sm font-medium text-gray-600 mb-1">Mot de passe *</label>
                    <input type="password" name="password" value={formData.password} onChange={handleChange}
                        className="w-full p-2 border rounded-lg focus:outline-none focus:ring focus:ring-blue-200"
                        placeholder="mot de passe" required />
                </div>

                <button type="submit" className="w-full py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition">
                    Connexion
                </button>
                <button type="reset" className="w-full py-2 bg-gray-200 text-black rounded-lg hover:bg-gray-300 transition">
                    Effacer
                </button>
            </form>
        </div>
    )
}

export default LoginView