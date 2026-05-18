import { useNavigate } from "react-router";
import { useState } from 'react';

import './RecuperarPassword.css'




function Recuperarpassword(){
	
	
	const [nombremail, setmail] = useState("");
	
	
	
	const enviado = async(e) => {
		e.preventDefault()
		
		try{
			
			const res = await fetch ("http://localhost:8081/recuperarpassword",{
				
				method: "POST",
				body: JSON.stringify({
					mail: nombremail
							}),
				headers:{
					
					"Content-type": "application/json",
				},
							
				
			})
			
			localStorage.setItem("mail", nombremail)
			
			
			if(res.ok){
				
				console.log(res.text)
				
				
			}
			
			if(res.status === 401){
				alert("Mail no encontrado")
					}
		}
		catch{alert("Error en la conexion con la base de datos")
			
			
		}
		
		
	}
	
	
	const navigate = useNavigate();
	
	
	const volver = () => {
		
		navigate('/')
		
	}
	
	const ingresotoken = () => {
		
		navigate('/ingresoToken');
	}
	
	
	const cargarmail = (e) => {
		
		setmail(e.target.value)
		
	}

	
	return(
		
		<div className="div_principal">
		<h1> Recuperar password </h1>
		<p> Ingrese mail</p>
		
		<form onSubmit={enviado}>
		<input 
		placeholder="Ingrese su mail"
		onChange={cargarmail}
		type="email"
		value={nombremail} />
		<button onClick={ingresotoken}> Enviar </button>
		</form>
		<button  onClick={volver}> Volver </button>
		</div>
		
	)
	
}

export default Recuperarpassword