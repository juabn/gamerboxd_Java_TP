import './PaginaGrupo.css'
import { useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { useNavigate } from "react-router";


function PaginaGrupo(){
	
	const location = useLocation();
	const navigate = useNavigate();
	
	const rol = location.state?.rol;
	
	const [imagen, setimagen] = useState("")
	const [nombre, setnombre] = useState("")
	const [descripcion, setdescripcion] = useState("")
	const [miembros, setMiembros] = useState([]);
	const [idgrupo, setidgrupo] = useState("")
	
	
	
	const modificarGrupo = () => {
		
		navigate("/ModificarGrupo", {
			
			state: {
				img: imagen,
				nombre: nombre,
				id:idgrupo
			
			}
		})
		
	}
	
	const salirDelGrupo = async () => {
	    const tokenActual = localStorage.getItem('token');

	    try {
	        const response = await fetch('http://localhost:8081/salirDeGrupo', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json',
	                'Authorization': 'Bearer ' + tokenActual
	            },
	            body: JSON.stringify({})
	        });

	        if (response.ok) { 
	            alert("Usted salió del grupo");
	            navigate('/');
	        } else {
	            alert("Error al intentar salir del grupo");
	        }
	    } catch (error) {
	        console.error('Error en salirDeGrupo:', error);
	        alert("Error de conexión, intente nuevamente más tarde");
	    }
	};
	
	useEffect(() => {
		let tokenActual = localStorage.getItem('token');
		

	fetch('http://localhost:8081/recuperarGrupoPorMiembro', {
		
		method: 'POST', 
		  headers: {
		    'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + tokenActual
		  },
		  body: JSON.stringify({}) 
		})
		
		.then(response => response.json())
		.then(data => {
			
			console.log(data)
			
			
			
			setimagen(data.foto_perfil)
			setnombre(data.nombre)
			setdescripcion(data.descripcion)
			setMiembros(data.integrantes)
			setidgrupo(data.id)
		    
		   
		})
		.catch(error => console.error('Error:', error));
		

		
		}, []);
	
	
	
	
	return(
		
		<div className='divprincipalgrupo'>
		
		<p className='textojuego'> Imagen del grupo: </p>
		<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagen} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
								/>
		<p className='textojuego'> Nombre del grupo: </p>
		<p className='textojuego'> {nombre} </p>
		<p className='textojuego'> Descripcion del grupo: </p>
		<p className='textojuego'> {descripcion} </p>
		<p className='textojuego'> Miembros del grupo: </p>
		<div className='seccion-miembros'>
		                {miembros.map((miembro, index) => (
		                    <p key={index} className='textojuego'>
		                        {miembro.nombre_usuario}
		                    </p>
		                ))}
		            </div>
		
		{rol === 'miembro' && (
        <div className='seccion-miembro'>
            <button onClick={salirDelGrupo} type="button">Salir</button>
 
        </div>
		            )}
		
		
		{rol === 'admin' && (
        <div className='seccion-admin'>
            <button onClick={modificarGrupo} type="button">Editar información del grupo</button>
 
        </div>
		            )}
		
		
		</div>
	)
}



export default PaginaGrupo