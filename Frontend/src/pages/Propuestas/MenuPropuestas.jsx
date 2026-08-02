import './menuPropuestas.css'
import { useNavigate } from "react-router";
import { useState } from 'react';

function MenuPropuestas(){
	
	const navigate = useNavigate();
	
	
	const [nombrejuego, setnombrejuego] = useState("");
		
		const manejarnombrejuego = (e) => {
			
			setnombrejuego(e.target.value)
			
		};
	
	const modificarjuego = (e) => {
		
		let token = localStorage.getItem('token');
				
		e.preventDefault();
				
		fetch('http://localhost:8081/existejuego',{
				
				
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({name: nombrejuego})
			

				
			})
			.then(response => {
	        
	        if (response.status === 200) {
	            alert("Bien");
	            setnombrejuego(""); 
				navigate("/ModificarJuego", {state:{nombre: nombrejuego}});
	        } 
			else if (response.status === 404) {
	            alert("No existe juego");
	            setnombrejuego(""); 
	        }
			else if (response.status === 401) {
	            alert("Error en la bd");
	            setnombrejuego(""); 
	        }
			else if (response.status === 402) {
	            alert("Error en el token");
	            setnombrejuego(""); 
	        }
			else {
	            alert("Error en la bd intente mas tarde" );
	        }
	    })
	    .catch(error => alert('Error en la conexion con la base de datos, pruebe mas tarde', error));
	};
	

	
		return(
			<div className='divprincipalmenupropuesta'>
		
			<p className= 'textomenupropuestas'> Ingrese juego </p>
			<input
			type = "text"
			value = {nombrejuego}
			onChange = {manejarnombrejuego}
			required /> 
			<button onClick={modificarjuego}>Modificar juego </button>
			<button >Ver propuestas recibidas </button>
			
		</div>
		);
}


export default MenuPropuestas;