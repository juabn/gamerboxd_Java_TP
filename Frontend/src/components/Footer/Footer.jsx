import './Footer.css';

import { Link } from 'react-router-dom';


export default function Navbar() {
	


  return (
		<nav>
		<div className="foot-group">
			<div className="foot-logo">Gamerboxd</div>
			<div className="foot-"
			<ul className="foot-links">
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