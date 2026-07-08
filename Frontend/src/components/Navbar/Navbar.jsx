import { Link } from 'react-router-dom';
import './Navbar.css';
import avatar from '../../assets/avatar.svg';



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
					<Link to="/CrearGrupo">Comunidad</Link>
				</li>
			</ul>
			</div> 
			<div className="divlogin">
			{autenticado ? (
				<div className='usuariologeado'>
				  <button className="btnlogin" onClick={handleLogout}>
				    Cerrar Sesion
				  </button>
				  
				  
				  <div className="avatar-perfil" onClick={() => console.log("Ir al perfil")}>
				    <img src={avatar} alt="Perfil" className="avatar-img" />
				  </div>
				</div>
			        ) : (
			          <Link to="/login">
			            <button className="btnlogin">Login</button>
			          </Link>
			        )}
			</div>
		</nav>
  );
}