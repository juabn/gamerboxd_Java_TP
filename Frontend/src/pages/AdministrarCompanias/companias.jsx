import { useState } from 'react';

import './companias.css'

function AdministrarCompanias(){
	
	
	const [nombrecompania, setnombrecompania] = useState("");
	
	const manejarnombrecompania = (e) => {
		
		setnombrecompania(e.target.value)
	};
	
	
	const enviarcompania = (e) => {
		
		
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
		<form onSubmit={enviarcompania}  className='contenedorsecundariocompania'>
		<input 
		type = "text"
		value={nombrecompania}
		onChange={manejarnombrecompania}
		required
		/>
		<p className='empresa'> {nombrecompania} </p>
		<button type="submit"
		> Agregar</button>
		<p className='empresa'> Aca iria un select</p>
		<button type="button"> Borrar </button>
		<button type="button"> Modificar </button>
		</form>
		</div>
	)
	
	
}



export default AdministrarCompanias