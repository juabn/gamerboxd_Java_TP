import { useState } from 'react';
import './Register.css'

function Registro(){
	
	
	const [nombre, setnombre] = useState("");
	const [mail, setmail] = useState("");
	const [contrasenia, setcontrasenia] = useState("");
	const [imagen, setimagen] = useState("")
	
	
	
	
	const enviar = async(e) => {
		e.preventDefault();
		console.log(JSON.stringify({
		    username: nombre,
		    mail: mail,
		    password: contrasenia,
		    image: imagen
		}))
		
		try{
			
			const res = await fetch('http://localhost:8081/registro',{
				
				
				method: "POST", 
				body: JSON.stringify({
								
				username: nombre,
				mail: mail,
				password: contrasenia,
				image : imagen
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
		
		
	const insertarimagen = (e) => {
		
		let reader = new FileReader()
		reader.readAsDataURL(e.target.files[0])
		reader.onload = () => {
		setimagen(reader.result )
		
		}
	}

		

	
	return(	
		
		<div className="contendorprincipal">
		<p>registro</p>
		<form onSubmit= {enviar} className='contendorprincipal'>
		<input 
		type = "email"
		onChange={insertarmail} 
		placeholder="Ingrese mail"
		value={mail}
		required/>
		
		<input 
		placeholder="Ingrese nombre"
		onChange={insertarnombre} 
		value={nombre}
		required
		/>
		<input 
		type = "password"
		placeholder="Ingrese contrasenia"
		onChange={insertarcontrasenia} 
		value={contrasenia}
		required/>
		
		
		<input type = "file" 
		accept="image/*"
		onChange={insertarimagen}	
		/>
		<img className='imagen' src = {imagen}
		/>
		<button type="submit">Enviar  </button>
		</form>
	</div>
	
	)
	
	
}



export default Registro;