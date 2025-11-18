import React from 'react'

const UserLine = (props) => {
  const [chargement, setChargement] = useState(false);
    const [error, setError] = useState(null);

    const loadEdit = () => {

    }

    const handleDelete = () => {
        setChargement(true);
        setError(null);
        let deleteValidation = confirm("Êtes-vous sûr de vouloir supprimer cette personne de la liste ?")
        if (confirm("Êtes-vous sûr de vouloir supprimer cette personne de la liste ?")) {
            personService.delete(props.id)
                .then(() => {
                    props.handleDeletion((deleted) => [...deleted, props.id])
                })
                .catch(erreur => {
                    console.log(erreur);
                    setError("Erreur lors du chargement de la suppression de la personne !")
                })
                .finally(() => { setChargement(false); });
        }
    }

  return (
    <>
        <td>{props.theuser.username}</td>
        <td>{props.theuser.firstname}</td>
        <td>{props.theuser.lastname}</td>
        <td>{props.theuser.email}</td>
        <td>{props.theuser.phoneNumber}</td>
        <td>{props.theuser.postalAdress}</td>
        <td>
            {chargement && (
                <div className='spinner-container'>
                    <div className='spinner'></div>
                </div>)}
            {!chargement && (
                props.theuser.role
            )}
            {error && (<p className='my-2'>{error}</p>)}
        </td>
    </>
  )
}

export default UserLine