import './ingresoToken.css'
import { useNavigate } from "react-router";
import { useState } from 'react';

function IngresarToken(){
	
	const navigate = useNavigate();
	
	const [token, setToken] = useState("")
	

	const volver = () => {
		
		navigate('/')
	}
	
	
	const enviar = async(e) => {
		e.preventDefault()
		var mail = localStorage.getItem("mail");
		console.log(mail)
		
		
		try {
			
			const res = await fetch ("http://localhost:8081/verificartoken", {
				
				
				method: "POST",
				body: JSON.stringify({
					
					token: token,
					mail : localStorage.getItem("mail")
					
					
				}),
				headers:{
					"Content-type": "application/json",
				},

				
			})
			
			if(res.ok){
				
				console.log("adad")
			}
			if(res.status === 401){
				alert("Token incorrecto o expirado")
					}
			
		}
		catch{alert("asdad")}
		
		
		
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