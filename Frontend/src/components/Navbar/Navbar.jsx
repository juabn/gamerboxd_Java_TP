import { Link } from 'react-router-dom';
import './Navbar.css';

import { useNavigate } from "react-router";
import { useState} from 'react';



export default function Navbar({autenticado}) {
	
	const [imagen, setimagen] = useState("")
	
	
	const renderPanelDerecho = () => {
			if (!autenticado) {
				return (
					<Link to="/login">
						<button className="btnlogin">Login</button>
					</Link>
				);
			}

			return (
				<div className='usuariologeado'>
					<button className="btnlogin" onClick={handleLogout}>
						Cerrar Sesión
					</button>
					<div className="avatar-perfil">
						<img 
							src={imagen || null} 
							onClick={() => navigate('/Modificarperfil')} 
							alt="Perfil" 
							className="avatar-img" 
						/>
					</div>
				</div>
			);
		};
		
		
	
	
	
	const [rol, setrol] = useState("");
	
	
	const navigate = useNavigate();
	
	const handleLogout = () => {
	    localStorage.removeItem('token');
	    window.location.href = '/login'; // Recarga y limpia la sesión
	  };
	  
	  
	  
	  let tokenActual = localStorage.getItem('token');
	 
	  
	  //trae rol de persona(administrador o usuario)
	  if(tokenActual !== null){
	  fetch('http://localhost:8081/verificarjwt',{
		method: 'POST', 
			  	  		  headers: {
			  	  		    'Content-Type': 'application/json' ,
							'Authorization': 'Bearer ' + tokenActual
			  	  		  },
			  	  		  body: JSON.stringify({}) 
	  })
	  .then(response => response.json())
	  .then(data => {
	    console.log(data)
		setrol(data)
	  	  	  		   
  	  		})
	.catch(error => console.error('Error:', error));
	  
	}else{
		console.log("no estas logeado")
	}
	  
	  	  
	if(tokenActual !== null){  	
	  	  
	  	  fetch('http://localhost:8081/fotousuario', {
			
	  	  		
	  	  		method: 'POST', 
	  	  		  headers: {
	  	  		    'Content-Type': 'application/json' ,
					'Authorization': 'Bearer ' + tokenActual
	  	  		  },
	  	  		  body: JSON.stringify({}) 
	  	  		})
	  	  		
	  	  		.then(response => response.json())
	  	  		.then(data => {
	  	  		    setimagen(data.foto_perfil)
	  	  		   
	  	  		})
	  	  		.catch(error => console.error('Error:', error));
	
	}

  return (

	<nav>
				<div className="nav-group">
					<div className="logo">Gamerboxd</div>
					<ul className="nav-links">
						{/*opciones publicas*/}
						<li><Link to="/">Inicio</Link></li>
						<li><Link to="Juegos">Juegos</Link></li>
						
						{/*opciones para todos los logeados*/}
						{autenticado && (
							<li><Link to="/CrearGrupo">Comunidad</Link></li>
						)}

						{/*opciones exclusivas para admins logeados*/}
						{autenticado && rol === "administrador" && (
							<>
								<li><Link to="/AdministrarCompanias">Cargar compania</Link></li>
								<li><Link to="/cargarjuegos">Cargar juegos</Link></li>
								<li><Link to="/CreacionAdmin">Crear administrador</Link></li>
							</>
						)}
					</ul>
				</div> 

				<div className="divloginnavbar">
					{renderPanelDerecho()}
				</div>
			</nav>

  );
}