import { useState} from 'react';
import { useNavigate } from "react-router";
import { useLocation } from 'react-router-dom';



function ModificarGrupo(){		
	
	
	const location = useLocation();
	
	const imagenRecibida = location.state?.img || '';
	const nombreRecibido = location.state?.nombre || '';
	const idgrupo = location.state?.id || '';
	
	const [estadofoto, setestadofoto] = useState(false)
	
	const [nuevonombre, setnuevonombre] = useState("")
	
	const [descripcion, setdescripcion] = useState("")
	
	const insertarnombre = (e) => {
		
		setnuevonombre(e.target.value)
		
		
	}
	
	const insertardescripcion = (e) => {
		
		setdescripcion(e.target.value)
		
		
		
	}
	
	
	const handleLogout = () => {
	   
	    navigate("/")
	  };
	  
	
	
	const dardebaja = (e) => {
		
		let token = localStorage.getItem('token');
		
		e.preventDefault();
		
		fetch('http://localhost:8081/dardebajagrupo',{
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({id: idgrupo}) 
				
		})
		
		.then(response => {
			
			if(response.status=== 200){
				
				alert("Grupo dado de baja correctamente");
				handleLogout();
				
				
			}
			
			else if (response.status === 401){
				
				alert("Error en la bd, intente nuevamente mas tarde");
			}
			
			
			
		}).catch(() => {alert("Error inesperado, intente nuevamente mas tarde")})
		
	}
	
	const enviar = (e) => {
		let token = localStorage.getItem('token');
		
		e.preventDefault();
		

		console.log(nuevonombre)
		console.log(descripcion)
		
		fetch('http://localhost:8081/actualizardatosgrupo', {
			
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({id:idgrupo,foto_perfil: imagen, nombre: nuevonombre, descripcion: descripcion}) 		
			
		})
		
		.then(response => {
			if(response.status === 200){
				alert("Actualización realizada con éxito");
						
			}
			else if (response.status == 409){
				alert("Ya existe grupo con ese nombre");
			}
			else{
				alert("Error en la conexión, pruebe más tarde");
			}
		})
		.catch(error => {
			console.error("El error real es:", error); 
			alert('Error en la conexión con la base de datos, pruebe más tarde');
		});
	}
	
	const [imagen, setimagen] = useState("")
	


		
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
			<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagenRecibida} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
						/>
			<p className='text'>{nombreRecibido}</p>	
			<p className='text'>Cambiar nombre del grupo</p>			
			<input  className='input'
			placeholder="Ingrese nuevo nombre"
			value={nuevonombre}
			onChange={insertarnombre}
			
				/>			
				
			<p className='text'>Cambiar descripcion</p>	
			<input  className='input'
			placeholder="Ingrese nueva descripcion"
			value={descripcion}
			onChange={insertardescripcion}

				/>		
			
			<button className='submit-btn' type="submit" disabled={!estadofoto && nuevonombre == ""} >Confirmar cambios  </button>
			<button type = "button" onClick={dardebaja}> Eliminar grupo </button>
			<button type="button" onClick={volver}> Volver </button>
			

			
			
			</form>
		</div>
		
		)
}



export default ModificarGrupo;