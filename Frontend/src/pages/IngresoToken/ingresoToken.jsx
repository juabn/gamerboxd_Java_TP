import './ingresoToken.css'
import { useNavigate } from "react-router";
import { useState } from 'react';

function IngresarToken(){
	
	const navigate = useNavigate();
	
	const [token, setToken] = useState("")
	
	var mail1 = localStorage.getItem("mail");
	

	const volver = () => {
		
		navigate('/')
	}
	
	
	const enviar = async(e) => {
		e.preventDefault()
		
		console.log(mail1)
		console.log(token)
		
		try {
			
			const res = await fetch ("http://localhost:8081/verificartoken", {
				
				
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
				
				alert("ok")
			}
			if(res.status === 401){
				alert("Token incorrecto o expirado")
					}
			
		}
		catch{alert("Error")}
		
		
		
	}
	
	
	const actualizarvalor = (e) => {
		
		setToken(e.target.value)
		
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