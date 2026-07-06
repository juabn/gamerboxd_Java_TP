import { Link } from 'react-router-dom';
import './Navbar.css';



export default function Navbar() {
	


  return (
		<nav>
		<div className="nav-group">
			<div className="logo">Gamerboxd</div>
			<ul className="nav-links">
				<li>
				<Link to="/">Inicio</Link>
				</li>
				<li>
					<Link to="/">Juegos</Link>
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
			<Link to="/login">
				<button className="btnlogin">Login</button>
			</Link>
			</div>
		</nav>
  );
}