import { useState } from 'react';


import './Register.css'

function Hola(){
	
	
	
	
	const [nombre, setnombre] = useState("");
	const [contrasenia, setcontrasenia] = useState("");
	
	var datix = {
		username: nombre,
		password: contrasenia
		
	}
	
	
	
	const enviado = (e) => { 
		e.preventDefault();
		console.log(datix)
		
		fetch ('http://localhost:8081/', {
			
			method: "POST", 
			body: JSON.stringify({
				
				username: nombre,
				password: contrasenia
			}),
			headers: {
				"Content-type": "application/json",
				
			},
			
			
		}).then( (res) => res.text() ) 
		.catch((error) => console.error("Error:", error))
		
		
		
			
	}
	
	
	const manejarusuario = (e) => {
		
		setnombre(e.target.value);
		
		
	}
	
	const manejarpassword = (e) => {
		
		
		setcontrasenia(e.target.value);
		
		
	}
	
	
	
		
	return(
		
		<div className="contenedorPrincipal">
			
			<div className="primerDiv">
			
				<div className="divlogin">
				
					<div className='bordecolor'> </div>
					
					<p className='bienvenido'> Bienvenido </p>
					
					<form className='formulario' onSubmit={enviado}>
					<div className='inputs'>
						<input  
						className='input' 
						type="text" 
						placeholder='Ingrese su usuario'
						onChange={manejarusuario}
						value = {nombre}
						 	
						/>
						<input  
						className='input' 
						type="password" 
						placeholder='Ingrese su contraseña' 
						onChange={manejarpassword}
						value={contrasenia} />
					
					</div>
					
					
					<div className='divboton'>
						<button  onClick={enviado}  className='ingresar'> Ingresar </button>
					</div>
					
					</form>
					<p  className='aviso'> ¿No tienes cuenta? </p>
					<p className='aviso'> ¿Olvidaste tu contraseña? </p>
				
				
				 </div>
				
			
			
			</div>
		
		
				
		
		
		
		</div>
	);
}

export default Hola;