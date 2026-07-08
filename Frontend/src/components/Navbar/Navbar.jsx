import { Link } from 'react-router-dom';
import './Navbar.css';




export default function Navbar({autenticado}) {
	
	const handleLogout = () => {
	    localStorage.removeItem('token');
	    window.location.href = '/login'; // Recarga y limpia la sesión
	  };
	


  return (
		<nav>
		<div className="nav-group">
			<div className="logo">Gamerboxd</div>
			<ul className="nav-links">
				<li>
				<Link to="/">Inicio</Link>
				</li>
				<li>
					<Link to="Juegos">Juegos</Link>
				</li>
				<li>
					<Link to="/">Administracion</Link>
				</li>
				<li>
					<Link to="/">Sobre nosotros</Link>
				</li>
			</ul>
			</div> 
			<div className="divlogin">
			{autenticado ? (
			          <button className="btnlogin" onClick={handleLogout} >
			            Cerrar Sesion
			          </button>
			        ) : (
			          <Link to="/login">
			            <button className="btnlogin">Login</button>
			          </Link>
			        )}
			</div>
		</nav>
  );
}