import { useState } from 'react';
import { useNavigate } from "react-router";

import './companias.css'

function AdministrarCompanias(){
	
	const navigate = useNavigate();
	
	
	const [nombrecompania, setnombrecompania] = useState("");
	
	const manejarnombrecompania = (e) => {
		
		setnombrecompania(e.target.value)
	};
	
	
	const modificarcompania = (e) => {
		
		let token = localStorage.getItem('token');
				
		e.preventDefault();
				
		fetch('http://localhost:8081/existeempresa',{
				
				
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({name: nombrecompania})
			

				
			})
			.then(response => {
	        
	        if (response.status === 200) {
	            alert("Bien");
	            setnombrecompania(""); 
				navigate("/Modificarcompanias", {state:{nombre: nombrecompania}});
	        } 
			else if (response.status === 404) {
	            alert("No existe empresa");
	            setnombrecompania(""); 
	        }
			else if (response.status === 401) {
	            alert("Error en la bd");
	            setnombrecompania(""); 
	        }
			else if (response.status === 402) {
	            alert("Error en el token");
	            setnombrecompania(""); 
	        }
			else {
	            alert("Error en la bd intente mas tarde" );
	        }
	    })
	    .catch(error => alert('Error en la conexion con la base de datos, pruebe mas tarde', error));
	};
		
		
	
	
	
	
	const crearcompania = (e) => {
		
		
		let token = localStorage.getItem('token');
		
		e.preventDefault();
		
	fetch('http://localhost:8081/crearcompania',{
		
		
		method: 'POST', 
		headers: {
		'Content-Type': 'application/json',
		'Authorization': 'Bearer ' + token },
		body: JSON.stringify({name: nombrecompania})
		
		
		
	})
	.then(response => {
	        
	        if (response.status === 200) {
	            alert("Creación de empresa correcta");
	            setnombrecompania(""); 
	        } 
			else if (response.status === 409) {
				            alert("Empresa repetida");
				            setnombrecompania(""); 
	        }
			else {
	            alert("Hubo un problema al crear la empresa. Código HTTP: " + response.status);
	        }
	    })
	    .catch(error => console.error('Error en el fetch:', error));
	};
	  

	
	
	
	
	return(
		<div className='ContenedorPrincipalCompanias'>
		
		
		
		<p className='textop'> Agregar compania</p>
		<form onSubmit={crearcompania}  className='contenedorsecundariocompania'>
		<input 
		type = "text"
		value={nombrecompania}
		onChange={manejarnombrecompania}
		required
		/>
		<p className='empresa'> {nombrecompania} </p>
		<button type="submit"
		> Agregar</button>
		<button type="button" onClick={modificarcompania}> Modificar </button>
		<button> Volver</button>
		</form>
		</div>
	)
	
	
}



export default AdministrarCompanias