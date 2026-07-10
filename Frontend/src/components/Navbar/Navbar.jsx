import { Link } from 'react-router-dom';
import './Navbar.css';

import { useNavigate } from "react-router";
import { useState} from 'react';



export default function Navbar({autenticado}) {
	
	const [imagen, setimagen] = useState("")
	
	
	
	const renderUsuarioLogueado = () => {
	    return (
	        <div className='usuariologeado'>
	            <button className="btnlogin" onClick={handleLogout}>
	                Cerrar Sesión
	            </button>
	            <div className="avatar-perfil" onClick={() => console.log("Ir al perfil")}>
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
				
				rol == "usuario"? (
					renderUsuarioLogueado()
				):
				(rol == "administrador"?(
					renderUsuarioLogueado()):
				(console.log("asd")))
				
				
		        ) : (
		          <Link to="/login">
		            <button className="btnlogin">Login</button>
		          </Link>
		        )}
			</div>
		</nav>
  );
}