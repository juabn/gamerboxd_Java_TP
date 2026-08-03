import './ModificarJuego.css'
import { useLocation } from 'react-router-dom';
import { useNavigate } from "react-router";

import { useState, useEffect } from 'react';

function Modificarcompanias(){
	
	const navigate = useNavigate();
	const location = useLocation();
	const nombreJuegoOriginal = location.state?.nombre || "";
	
	
	
	
	const [nuevonombrejuego, setnuevonombre] = useState("");
	const [estado, setestado] = useState("");
	const [id, setid] = useState("");
	const [imagen, setimagen] = useState("")
	
	const [estadofoto, setestadofoto] = useState(false)
	
	
	const insertarimagen = (e) => {
				
				let reader = new FileReader()
				reader.readAsDataURL(e.target.files[0])
				reader.onload = () => {
				setimagen(reader.result )
				setestadofoto(true);
				
				
				}
			}
	
	const [juegoconfirmado, setjuegoconfirmado] = useState(nombreJuegoOriginal);
	const [estadoconfirmado, setestadoconfirmado] = useState(""); 
	
	let token = localStorage.getItem('token');
	
	const volver = () => {
		navigate("/MenuPropuestas");
	}
	
	const cambiarnombre = (e) => {
		setnuevonombre(e.target.value);
	}
	
	const dardebaja = () => {
		if(estado === "activo"){
			setestado("inactivo");
		} else if(estado === "inactivo"){
			setestado("activo");
		}
	}
	
	
	const huboCambios = 
		(nuevonombrejuego.trim() !== "" && nuevonombrejuego.trim() !== juegoconfirmado) || 
		(estado !== estadoconfirmado)  || (estadofoto !== false);

	const guardarcambios = (e) => {
		e.preventDefault();
		
		
		let nombreFinalParaEnviar = nuevonombrejuego.trim();
		    
		    
		    if (nombreFinalParaEnviar !== "") {
		        const nombreoriginalnormalizado = juegoconfirmado.toLowerCase().trim().normalize("NFD").replace(/[\u0300-\u0306]/g, "");
		        const nuevonombrenormalizado = nombreFinalParaEnviar.toLowerCase().trim().normalize("NFD").replace(/[\u0300-\u0306]/g, "");
		        
		        if (nombreoriginalnormalizado === nuevonombrenormalizado) {
		            alert("El nombre elegido es el mismo que ya posee el juego");
		            return;
		        }
		    }
		
		fetch('http://localhost:8081/actualizardatosjuego', {
			method: "POST",
			body: JSON.stringify({
				name: nombreFinalParaEnviar,
				estado: estado,
				id: id,
				background_image: imagen
			}),
			headers: {
				"Content-type": "application/json",	
				'Authorization': 'Bearer ' + token 
			}
		})
		.then(response => {
			if(response.status === 200){
				alert("Actualización realizada con éxito");
				
				if (nombreFinalParaEnviar !== "") {
				            setjuegoconfirmado(nombreFinalParaEnviar);
				        }
				
				
				setnuevonombre("");
				setestadoconfirmado(estado);
				setestadofoto(false);
				
				
				
			}
			else if (response.status == 409){
				alert("Ya existe juego con ese nombre");
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
	
	
	useEffect(() => {		
		
		fetch('http://localhost:8081/devolverjuego',{
			method: 'POST', 
			headers: {
				'Content-Type': 'application/json',
				'Authorization': 'Bearer ' + token 
			},
			body: JSON.stringify({name: nombreJuegoOriginal})
		})
		.then(response => {
			if (response.status === 200) {
				return response.json().then(data => {
					console.log(data);
					setid(data.id);
					
					setestado(data.estado);
					
					setestadoconfirmado(data.estado); 
					
					setimagen(data.background_image)
				});
			} 
			else if (response.status === 402) {
				alert("Error en el token");
			}
			else {
				alert("Error en la bd intente más tarde");
			}
		})
		.catch(error => {
			console.error("El error real es:", error); 
			
			alert('Error en la conexión con la base de datos, pruebe más tarde');
		});
	}, []);

	return(
		<form onSubmit={guardarcambios}>
			<div className='divprincipalmodificarjuegos'>
			
				<img className='imagen' style={{ width: '20vh', height: '20vh', objectFit: 'cover', borderRadius: '50%' }} src = {imagen} //lo pongo asi porque en el css no aplica los cambios no se qeu onda
									/>
									
				<input className='file-input' type = "file" 
										accept="image/*"
										onChange={insertarimagen}	
										/>
									
				<p className='textojuego'> Nombre: {juegoconfirmado} </p>
				<p className='textojuego'> Estado: {estado} </p>
				
				<p className='textojuego'> Ingrese nuevo nombre </p>
				
				<input 
					type="text"
					value={nuevonombrejuego} 
					onChange={cambiarnombre}
				/>
				
				<button type="button" onClick={dardebaja}> Cambiar estado </button>
				
				
				<button type="submit" disabled={!huboCambios}> Guardar cambios </button>
				
				<button type="button" onClick={volver}> Volver </button>
			
			</div>
		</form>
	)
}

export default Modificarcompanias;