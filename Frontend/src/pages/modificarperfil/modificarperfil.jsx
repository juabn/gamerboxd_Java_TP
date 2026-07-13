import { useState, useEffect } from 'react';
import { useNavigate } from "react-router";
import './modificarperfil.css '




function Modificarperfil(){		
	
	
	
	
	const [estadofoto, setestadofoto] = useState(false)
	
	const [nuevonombre, setnuevonombre] = useState("")
	
	const insertarnombre = (e) => {
		
		setnuevonombre(e.target.value)
		console.log(e.target.value)
		
		
	}
	
	
	const handleLogout = () => {
	    localStorage.removeItem('token');
	    window.location.href = '/login'; 
	  };
	  
	
	
	const dardebaja = (e) => {
		
		let token = localStorage.getItem('token');
		
		e.preventDefault();
		
		fetch('http://localhost:8081/dardebaja',{
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({}) 
				
		})
		
		.then(response => {
			
			if(response.status=== 200){
				
				alert("Usuario dado de baja correctamente");
				
				
			}
			
			else if (response.status === 401){
				
				alert("Error en la bd, intente nuevamente mas tarde");
			}
			
			
			
		}).catch(() => {alert("Error inesperado, intente nuevamente mas tarde")})
		
	}
	
	const enviar = (e) => {
		let token = localStorage.getItem('token');
		
		e.preventDefault();
		
		console.log(imagen)
		console.log(nuevonombre)	
		
		console.log("Token a enviar:", token);
		
		fetch('http://localhost:8081/actualizardatosperfil', {
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({foto_perfil: imagen, nombre_usuario: nuevonombre}) 
			
			
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
	
	
	
	
	
	useEffect(() => {
		let tokenActual = localStorage.getItem('token');
		
	
	fetch('http://localhost:8081/fotousuario', {
		
		method: 'POST', 
		  headers: {
		    'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + tokenActual
		  },
		  body: JSON.stringify({}) 
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
			
			<form onSubmit={enviar} className='form'>
			<p className='text'>Actualizar imagen</p>
			
			<input className='file-input' type = "file" 
						accept="image/*"
						onChange={insertarimagen}	
						/>
			<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagen} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
						/>
			<p className='text'>{nombreoriginal}</p>	
			<p className='text'>Cambiar nombre</p>			
			<input  className='input'
			placeholder="Ingrese nuevo nombre"
			value={nuevonombre}
			onChange={insertarnombre}
			
				/>			
			
			<button className='submit-btn' type="submit" disabled={!estadofoto && nuevonombre == ""} >Confirmar cambios  </button>
			<button type = "button" onClick={dardebaja}> Dar de baja </button>
			<button type="button" onClick={volver}> Volver </button>
			
			
			
			
			
			
			
			</form>
		</div>
		
		)
}



export default Modificarperfil;