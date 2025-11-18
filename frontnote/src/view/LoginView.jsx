import React from 'react'
import loginService from '../service/LoginService';
import Button from '../component/Button';
import Label from '../component/Label';
import Input from '../component/Input';
import { useNavigate } from 'react-router';
import { useState } from 'react';


const LoginView = () => {

    const navigate = useNavigate();
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
            .then((data) =>{
                console.log(data)
                navigate("/");
            })
            .catch(erreur => {
                console.log(erreur);
                setError("Identifiant ou mot de passe erroné !")
            })
            .finally(() => {
            });
    }

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
            <form onSubmit={validateSubmit} noValidate validated={validated.toString()}
                className="bg-white p-6 rounded-2xl shadow-md w-full max-w-sm space-y-4">
                <h2 className="text-xl font-semibold text-gray-700">Connexion à <span className='italic'>frontnote</span></h2>
                {error && (<p className='text-red-800'>{error}</p>)}
                <div>
                    <Label>Identifiant *</Label>
                    <Input type="text" name="username"
                        placeholder="identifiant" required />
                </div>

                <div>
                    <Label>Mot de passe *</Label>
                    <Input type="password" name="password"
                        placeholder="mot de passe" required />
                </div>

                <Button type="submit" className="bg-blue-500 text-white hover:bg-blue-600">
                    Connexion
                </Button>
                <Button type="reset" className="bg-gray-200 text-black hover:bg-gray-300">
                    Effacer
                </Button>
            </form>
        </div>
    )
}

export default LoginView