import Navbar from "../components/Navbar";
import IconButton from "../components/IconButton";
import { useNavigate } from "react-router-dom";

// Import images
import elevesIcon from "../assets/images/Liste d'élèves.png";
import profsIcon from "../assets/images/Liste des professeurs.png";
import usersIcon from "../assets/images/Liste d'utilisateurs.png";
import classesIcon from "../assets/images/Liste des classes.png";
import enseignIcon from "../assets/images/Liste des enseignements.png";
import mesEnfantsIcon from "../assets/images/Mes enfants.png";
import mesClassesIcon from "../assets/images/Mes classes.png";

export default function Accueil() {
    const navigate = useNavigate();

    return (
        <>
            <Navbar />
            <div className="max-w-6xl mx-auto mt-20 space-y-20">

                <div className="flex justify-center gap-28">
                    <IconButton
                        icon={elevesIcon}
                        label="Liste d'élèves"
                        onClick={() => navigate("/eleves")}
                    />
                    <IconButton
                        icon={profsIcon}
                        label="Liste des professeurs"
                        onClick={() => navigate("/professeurs")}
                    />

                    <IconButton icon={mesEnfantsIcon} label="Mes enfants" />
                    <IconButton icon={mesClassesIcon} label="Mes classes" />
                </div>

                <div className="flex justify-center gap-28 pb-20">
                    <IconButton
                        icon={enseignIcon}
                        label="Liste des enseignements"
                        onClick={() => navigate("/enseignements")}
                    />
                    <IconButton
                        icon={usersIcon}
                        label="Liste d'utilisateurs"
                        onClick={() => navigate("/utilisateurs")}
                    />
                    <IconButton icon={classesIcon} label="Liste des classes" />
                </div>

            </div>
        </>
    );
}
