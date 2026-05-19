import { useState } from 'react';
import { useNavigate } from "react-router";


import './Login.css'

function Login({ setAuth }){
	
	
	
	
	
	
	const [nombre, setnombre] = useState("");
	const [contrasenia, setcontrasenia] = useState("");
	const navigate = useNavigate();
	
	
	const registro_navigate = () => {
		
		
		navigate('/registro');
		
	}
	
	const recuperarpassword_navigate = () => {
		
		navigate('/recuperarpassword')
	}

	
	
	const enviado = async(e) => { 
		e.preventDefault();
		
		
		
		try{
		const res = await fetch ('http://localhost:8081/login', {
			
			method: "POST", 
			body: JSON.stringify({
				
				mail: nombre,
				contrasenia: contrasenia
			}),
			headers: {
				"Content-type": "application/json",
				
			},

		})
		
		if(res.ok){
		const data = await res.json()	
		console.log(data.token)
		localStorage.setItem('token', data.token);
		alert("Usuario correcto")
		setAuth(true);
		navigate('/');
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
						type="email" 
						placeholder='Ingrese su mail'
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
						<button className='ingresar'> Ingresar </button>
					</div>
					
					</form>
					<p onClick={registro_navigate} className='aviso'> ¿No tienes cuenta? </p>
					<p onClick={recuperarpassword_navigate} className='aviso'> ¿Olvidaste tu contraseña? </p>		
				
				 </div>		
			
			</div>
		

		
		</div>
	);
}

export default Login;