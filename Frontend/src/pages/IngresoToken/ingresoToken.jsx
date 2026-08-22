import './ingresoToken.css'
import { useNavigate } from "react-router";
import { useState } from 'react';
import { API_URL } from '../../config';

function IngresarToken(){
	
	const navigate = useNavigate();
	
	const [token, setToken] = useState("")
	
	var mail1 = localStorage.getItem("mail");
	

	const volver = () => {
		
		navigate('/login')
	}
	
	
	const enviar = async(e) => {
		e.preventDefault()
		
		
		try {
			
			const res = await fetch (`${API_URL}/verificartoken`, {
				
				
				method: "POST",
				body: JSON.stringify({
					
					token: token,
					mail : mail1
					
					
				}),
				headers:{
					"Content-type": "application/json",
				},

				
			})
			
			if(res.ok){
				
				console.log("ok")
				avanzar()
				
			}
			if(res.status === 401){
				alert("Token incorrecto o expirado")
					}
			
		}
		catch{alert("Error en la conexion con la base de datos, intente nuevamente")
			navigate("/login")
		}
		
		
		
	}
	
	
	const actualizarvalor = (e) => {
		
		setToken(e.target.value)
		
	}
	
	const avanzar = () => {
		
		navigate('/ingresarnuevapassword')
	}
	

	
	return(
		
		
		<div className="contendorprincipal">
		<h1> Ingresar Token </h1>
		
		<form onSubmit={enviar}>
		<input placeholder="Ingrese el token"
		onChange={actualizarvalor}
		value={token} />
		<button> Enviar </button>
		</form>
		<button onClick={volver}> Volver </button> 
		</div>
	)
	
	
		

		
	}
	
	

	



export default IngresarToken