import { useState } from 'react';


import './Login.css'

function Login(){
	
	
	
	
	const [nombre, setnombre] = useState("");
	const [contrasenia, setcontrasenia] = useState("");
	

	
	
	const enviado = async(e) => { 
		e.preventDefault();
		
		
		try{
		const res = await fetch ('http://localhost:8081/login', {
			
			method: "POST", 
			body: JSON.stringify({
				
				username: nombre,
				password: contrasenia
			}),
			headers: {
				"Content-type": "application/json",
				
			},

		})
		
		if(res.ok){
		const data = await res.text()	
		console.log(data)
		alert("Usuario correcto")
		}
		
		if(res.status === 401){
			
			alert("Mail no encontrado o credenciales incorrectas")
		}
		}catch{alert("Error en la conexion con la base de datos")}
		
			
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

export default Login;