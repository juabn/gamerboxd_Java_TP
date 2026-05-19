import { Link } from 'react-router-dom';
import './Navbar.css';



export default function Navbar() {
	


  return (
		<nav>
		<div className="nav-group">
			<div className="logo">Gamerboxd</div>
			<ul class="nav-links">
				<li>
					<Link to="/">
						<a>Inicio</a>
					</Link>
				</li>
				<li>
					<Link to="/">
						<a>Juegos</a>
					</Link>
				</li>
				<li>
					<Link to="/">
						<a>Administracion</a>
					</Link>
				</li>
				<li>
					<Link to="/">
						<a>Sobre nosotros</a>
					</Link>
				</li>
			</ul>
			</div> 
			<div class="divlogin">
			<Link to="/login">
				<button class="btnlogin">Login</button>
			</Link>
			</div>
		</nav>
  );
}