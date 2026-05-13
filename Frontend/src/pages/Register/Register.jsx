import { useState } from 'react';
import './Register.css'

function Registro(){
	
	
	const [nombre, setnombre] = useState("");
	const [mail, setmail] = useState("");
	const [contrasenia, setcontrasenia] = useState("");
	
	const enviar = async(e) => {
		e.preventDefault();
		
		
		try{
			
			const res = await fetch('http://localhost:8081/registro',{
				
				method: "POST", 
				body: JSON.stringify({
								
				username: nombre,
				mail: mail,
				password: contrasenia
							}),
				headers: {
				"Content-type": "application/json",
								
				},

			})
			
			if(res.ok){
			const data = await res.text()	
			console.log(data)
			alert("Usuario registrado")
					}
					
			if(res.status === 409){
								
				alert("Usuario duplicado")}
			
		}catch{alert("Error en la conexion con la base de datos, intente mas tarde")}
									
	
				
			}
			
	const insertarnombre = (e) => {
							
		setnombre(e.target.value);
		
		
		}
		
	const insertarcontrasenia = (e) => {
		
		setcontrasenia(e.target.value);
	}
	
	const insertarmail = (e) => {
			
		setmail(e.target.value);
		}
			

	
	
	
	return(	
		
		<div className="contendorprincipal">
		<p>registro</p>
		<form onSubmit= {enviar} className='contendorprincipal'>
		<input 
		onChange={insertarmail} 
		placeholder="Ingrese mail"
		value={mail}/>
		
		<input 
		placeholder="Ingrese nombre"
		onChange={insertarnombre} 
		value={nombre}
		
		/>
		<input 
		placeholder="Ingrese contrasenia"
		onChange={insertarcontrasenia} 
		value={contrasenia}/>
		
		<p>aca iria para que ingrese la foto de perfil</p>
		<button>Enviar  </button>
		</form>
	</div>
	
	)
	
	
}



export default Registro;