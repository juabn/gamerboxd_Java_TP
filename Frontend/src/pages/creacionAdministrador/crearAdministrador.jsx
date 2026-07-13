import './crearAdministrador.css'
import { useState } from 'react';

function CreacionAdmin(){
	
	
	
	const [mail, setmail] = useState("")
	
	
	const handlemail = (e) => {
		
		setmail(e.target.value)
		
	}
	
	const enviar = (e) => {
		
		let token = localStorage.getItem('token');
		console.log(mail)
				
		e.preventDefault();
				
		fetch('http://localhost:8081/convertirenadmin',{
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({mail: mail}) 
				
		})
		
		.then(response => {
			
			if(response.status=== 200){
				
				alert("Usuario creado como administrador correctamente");
			}
			
			else if (response.status === 405){
				alert("No existe usuario con ese mail");
			}
			
			else if (response.status === 401){
				alert("Este usuario se encuentra inactivo, cambia su estado para convertirlo en administrador");
			}
			
			else if (response.status === 402){
				
				alert("Este usuario ya es administrador")
			}
			
			else{
				
				alert("Error en la bd, intente nuevamente mas tarde");
			}
					
					
					
			}).catch(() => {alert("Error inesperado, intente nuevamente mas tarde")})
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	return(
		
	<form onSubmit={enviar}>
	
	<div className="div_principal_creardmin">
	<p className='textocrearadmin'> Creacion de administrador</p>
	<p className='textocrearadmin'>Ingrese nombre del usuario </p>
	<input
	
	type = "email"
	value={mail}
	onChange={handlemail}
	placeholder='Ingrese mail'
	
	/>
	 
	 <button> Convertir en administrador </button>
	

	</div>
	
	</form>
	)
}

export default CreacionAdmin;