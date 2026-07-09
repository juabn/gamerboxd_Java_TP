import { Link } from 'react-router-dom';
import './Navbar.css';

import { useNavigate } from "react-router";
import { useState} from 'react';



export default function Navbar({autenticado}) {
	
	const navigate = useNavigate();
	
	const handleLogout = () => {
	    localStorage.removeItem('token');
	    window.location.href = '/login'; // Recarga y limpia la sesión
	  };
	  
	  
	  
	  
	  
	  
	  const [imagen, setimagen] = useState("")
	  	  
	  	  let mail = localStorage.getItem('usuario');
	  	  
	  	  fetch('http://localhost:8081/fotousuario', {
	  	  		
	  	  		method: 'POST', 
	  	  		  headers: {
	  	  		    'Content-Type': 'application/json' 
	  	  		  },
	  	  		  body: JSON.stringify({mail: mail}) 
	  	  		})
	  	  		
	  	  		.then(response => response.json())
	  	  		.then(data => {
	  	  		    setimagen(data.foto_perfil)
	  	  		   
	  	  		})
	  	  		.catch(error => console.error('Error:', error));
	


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
				    <img src={imagen} onClick={() => navigate('/Modificarperfil')} alt="Perfil" className="avatar-img" />
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