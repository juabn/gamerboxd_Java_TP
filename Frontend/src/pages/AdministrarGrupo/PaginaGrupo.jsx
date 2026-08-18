import './PaginaGrupo.css'
import { useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';


function PaginaGrupo(){
	
	const location = useLocation();
	
	const rol = location.state?.rol;
	
	const [imagen, setimagen] = useState("")
	const [nombre, setnombre] = useState("")
	const [descripcion, setdescripcion] = useState("")
	const [miembros, setMiembros] = useState([]);
	
	useEffect(() => {
		let tokenActual = localStorage.getItem('token');
		

	fetch('http://localhost:8081/obtenergrupo', {
		
		method: 'POST', 
		  headers: {
		    'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + tokenActual
		  },
		  body: JSON.stringify({}) 
		})
		
		.then(response => response.json())
		.then(data => {
			
			setimagen(data.foto_perfil)
			setnombre(data.nombre)
			setdescripcion(data.descripcion)
			setMiembros(data.integrantes)
		    
		   
		})
		.catch(error => console.error('Error:', error));
		

		
		}, []);
	
	
	
	
	return(
		
		<div className='divprincipalgrupo'>
		<p className='textojuego'> {imagen} </p>
		<p className='textojuego'> {nombre} </p>
		<p className='textojuego'> {descripcion} </p>
		<p className='textojuego'> Miembro 1 </p>
		<p className='textojuego'> Miembro 2 </p>
		<p className='textojuego'> Miembro 3 </p>
		
		
		{rol === 'admin' && (
        <div className='seccion-admin'>
            <button type="button">Editar información del grupo</button>
 
        </div>
		            )}
		
		
		</div>
	)
}



export default PaginaGrupo