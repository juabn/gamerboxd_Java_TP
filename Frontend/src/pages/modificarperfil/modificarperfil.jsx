import { useState, useEffect } from 'react';
import { useNavigate } from "react-router";
import './modificarperfil.css'




function Modificarperfil(){		
	
	
	
	const [estadofoto, setestadofoto] = useState(false)
	
	const [nuevonombre, setnuevonombre] = useState("")
	
	const insertarnombre = (e) => {
		
		setnuevonombre(e.target.value)
		console.log(e.target.value)
		
		
	}
	
	const enviar = (e) => {
		
		e.preventDefault();
		
		console.log(mail)	
		console.log(imagen)
		console.log(nuevonombre)	
		
		
		fetch('http://localhost:8081/actualizardatosperfil', {
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json' },
			body: JSON.stringify({mail: mail, foto_perfil: imagen, nombre_usuario: nuevonombre}) 
			
			
		})
		
		.then(response => response.json())
		.then(data => {
			
			console.log('Respuesta de exito:', data);
		
			if (data == "ok") {
			        alert("Actualizacion de perfil correcta");
			    } else {
			        alert("Hubo un problema al actualizar: " + data.status);
			    }
		})
		.catch(error => {
			
			console.error('Error:', error);
			alert("Error en la base de datos, intenta mas tarde");
		
	});
	}
	
	const [imagen, setimagen] = useState("")
	const [nombreoriginal, setnombreoriginal] = useState("")
	
	let mail = localStorage.getItem('usuario');
	
	
	
	useEffect(() => {
	
	fetch('http://localhost:8081/fotousuario', {
		
		method: 'POST', 
		  headers: {
		    'Content-Type': 'application/json' 
		  },
		  body: JSON.stringify({mail: mail}) 
		})
		
		.then(response => response.json())
		.then(data => {
			
			setimagen(data.foto_perfil)
			
			setnombreoriginal(data.nombre_usuario)
		    
		   
		})
		.catch(error => console.error('Error:', error));
		

		
		}, []);
		
	

		
		const navigate = useNavigate();
		
		
		const volver = () => {
			
			navigate("/login")
		}
		

		

				

			
			
			
		const insertarimagen = (e) => {
			
			let reader = new FileReader()
			reader.readAsDataURL(e.target.files[0])
			reader.onload = () => {
			setimagen(reader.result )
			setestadofoto(true);
			
			
			}
		}

			

		
		return(	
			
			<div className="contendorprincipal">
			
			<form onSubmit={enviar} className='contendorprincipal'>
			<p className='text'>Actualizar imagen</p>
			
			<input type = "file" 
						accept="image/*"
						onChange={insertarimagen}	
						/>
			<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagen} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
						/>
			<p className='text'>{nombreoriginal}</p>	
			<p className='text'>Cambiar nombre</p>			
			<input 
			placeholder="Ingrese nuevo nombre"
			value={nuevonombre}
			onChange={insertarnombre}
			
				/>			
			
			<button type="submit" disabled={!estadofoto && nuevonombre == ""} >Confirmar cambios  </button>
			<button type="button" onClick={volver}> Volver </button>
			
			
			
			
			
			
			
			</form>
		</div>
		
		)
}



export default Modificarperfil;