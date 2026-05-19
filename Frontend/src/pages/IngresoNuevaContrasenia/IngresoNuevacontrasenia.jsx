import { useNavigate } from "react-router";
import { useState } from 'react';

import './IngresoNuevaContrasenia.css'


function IngresoNuevacontrasenia(){
	
	
	const [password1, setpassword1] = useState("");
	const [password2, setpassword2] = useState("");
	const navigate = useNavigate();
	
	
	const enviar = async(e) => {
		e.preventDefault()
		
		
		if(password1 == password2){
			
			console.log(localStorage.getItem('mail'))
			
			
			try{
				const res = await fetch("http://localhost:8081/cambiarpassword",{
					

					method: "POST",
					body: JSON.stringify({
						contrasenia: password1,
						mail: localStorage.getItem('mail')
								}),	
					headers:{
						
						"Content-type": "application/json",
					},

				})
				
				if(res.ok){
					
					alert("Contrasenia cambiada")
					navigate("/login")
				}

			}catch{
					
				alert("Error en la conexion con la base de datos, intente nuevamente")
				
					
				}
			
		}else{
			
			alert("Las contrasenias no coinciden")
		}
		
		

		
	}
	
	
	const password1cargar = (e) =>(
		
		setpassword1(e.target.value)
	)
	
	const password2cargar = (e) =>(
		
		setpassword2(e.target.value)
	)
	
	const volver = () => {
		
		navigate('/')
	}
		

	return(
		
		<div className='contendorprincipal'>
		
		<h1> Ingrese nueva contrasenia </h1>
		
		<form onSubmit={enviar} className='contendorprincipal'>
		
		<input placeholder='Ingrese nueva contrasenia'
		onChange={password1cargar}
		value = {password1}/>
		<input placeholder='Ingrese la contrasenia nuevamente'
		onChange={password2cargar}
		value = {password2}/>
		<button> Cambiar contrasenia</button>
		
		</form>
		
		<button onClick={volver} > volver </button>
	
	</div>

)
		
	
}


export default IngresoNuevacontrasenia