export default function IconButton({ icon, label, onClick }) {
    return (
        <div
            className="flex flex-col items-center cursor-pointer group"
            onClick={onClick}   // <-- OBLIGATOIRE
        >
            <img
                src={icon}
                alt={label}
                className="w-24 h-24 object-contain transition-transform duration-200 group-hover:scale-105"
            />
            <span className="mt-3 bg-black text-white px-4 py-1 rounded-md text-sm shadow">
                {label}
            </span>
        </div>
    );
}