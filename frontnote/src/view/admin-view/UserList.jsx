import React from 'react';
import Input from '../../component/Input';
import Button from '../../component/Button';
import UserLine from '../../component/UserLine';
import UserService from '../../service/UserService';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router';

const UserList = () => {
    const [searchText, setSearchText] = useState("");
    const navigate = useNavigate();

    const [userList, setUserList] = useState(null);
    const [chargement, setChargement] = useState(false);
    const [error, setError] = useState(null);
    const [deleted, setDeleted] = useState([]);

    const getAll = () => {
        setChargement(true);
        setError(null);
        UserService.getAll()
            .then(response => {
                console.log(response);
                setUserList([...response.data])
            })
            .catch(erreur => {
                console.log(erreur);
                setError("Erreur lors du chargement de la liste des utilisateurs !")
            })
            .finally(() => { setChargement(false); });
    }

    useEffect(() => getAll(), []);


    return (
        <>
            <div className='flex justify-around'>
                <Input type="text" placeholder="Rechercher" value={searchText} />
                <Button className="bg-green-600 text-white hover:bg-green-800">Ajouter</Button>
                <Button className="bg-indigo-600 text-white hover:bg-indigo-800">Modifier</Button>
                <Button className="bg-red-600 text-white hover:bg-red-800">Supprimer</Button>
            </div>
            <table>
                <thead>
                    <tr>
                        <th>Identifiant</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>Adresse</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    {chargement && (
                        <tr>
                            <td className='spinner-container'><div className='spinner'></div></td>
                            <td>Chargement ...</td>
                            <td className='spinner-container'><div className='spinner'></div></td>
                            <td>Chargement ...</td>
                            <td className='spinner-container'><div className='spinner'></div></td>
                            <td>Chargement ...</td>
                            <td className='spinner-container'><div className='spinner'></div></td>
                        </tr>
                    )}
                    {error && (
                        <tr>
                            <td className='font-bold' colSpan={7}>{error}</td>
                        </tr>
                    )}
                    {userList && !chargement && !error && userList.map(user => {
                        if (!deleted.includes(user.id)) {
                            return (
                                <tr key={user.id} className='' onClick={handleSelection}>
                                    <UserLine theuser={user} id={user.id} handleDeletion={setDeleted} />
                                </tr>
                            )
                        }
                    }
                    )}
                </tbody>
            </table>
        </>
    )
}

export default UserList