import { useState, useEffect } from 'react';
import { useNavigate } from "react-router";
import './modificarperfil.css'




function Modificarperfil(){		
	
	
	const [imagen, setimagen] = useState("")
	const [nombreoriginal, setnombreoriginal] = useState("")
	
	let mail = localStorage.getItem('usuario');
	
	console.log(localStorage.getItem('usuario'))
	
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
			console.log(data.nombre_usuario)
			setnombreoriginal(data.nombre_usuario)
		    
		   
		})
		.catch(error => console.error('Error:', error));
		

		
		}, []);
		
	
	
	const [nombre, setnombre] = useState("");
		
		const navigate = useNavigate();
		
		
		const volver = () => {
			
			navigate("/login")
		}
		

		

				
		const insertarnombre = (e) => {
								
			setnombre(e.target.value);
		
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
			
			<form className='contendorprincipal'>
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
			onChange={insertarnombre} 
			value={nombre}
			required
				/>			
			
			<button type="submit">Confirmar cambios  </button>
			<button onClick={volver}> Volver </button>
			
			
			
			
			
			
			
			</form>
		</div>
		
		)
}



export default Modificarperfil;