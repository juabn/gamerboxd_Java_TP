import { useLocation } from 'react-router-dom';
import { useNavigate } from "react-router";
import './modificarcompanias.css'
import { useState, useEffect } from 'react';

function Modificarcompanias(){
	
	const navigate = useNavigate();
	const location = useLocation();
	const nombreEmpresaOriginal = location.state?.nombre || "";
	
	
	const [nuevonombreempresa, setnuevonombre] = useState("");
	const [estado, setestado] = useState("");
	const [id, setid] = useState("");
	
	
	const [empresaconfirmada, setempresaconfirmada] = useState(nombreEmpresaOriginal);
	const [estadoconfirmado, setestadoconfirmado] = useState(""); 
	
	let token = localStorage.getItem('token');
	
	const volver = () => {
		navigate("/AdministrarCompanias");
	}
	
	const cambiarnombre = (e) => {
		setnuevonombre(e.target.value);
	}
	
	const dardebaja = () => {
		if(estado === "Activo"){
			setestado("inactivo");
		} else if(estado === "inactivo"){
			setestado("Activo");
		}
	}
	
	
	const huboCambios = 
		(nuevonombreempresa.trim() !== "" && nuevonombreempresa.trim() !== empresaconfirmada) || 
		(estado !== estadoconfirmado);

	const guardarcambios = (e) => {
		e.preventDefault();
		
		
		let nombreFinalParaEnviar = nuevonombreempresa.trim() === "" ? empresaconfirmada : nuevonombreempresa;
		
		
		if (nuevonombreempresa.trim() !== "") {
			const nombreoriginalnormalizado = empresaconfirmada.toLowerCase().trim().normalize("NFD").replace(/[\u0300-\u0306]/g, "");
			const nuevonombrenormalizado = nuevonombreempresa.toLowerCase().trim().normalize("NFD").replace(/[\u0300-\u0306]/g, "");
			
			if(nombreoriginalnormalizado === nuevonombrenormalizado){
				alert("El nombre elegido es el mismo que ya posee la empresa");
				return;
			}
		}
		
		fetch('http://localhost:8081/actualizardatosdeempresa', {
			method: "POST",
			body: JSON.stringify({
				name: nombreFinalParaEnviar,
				estado: estado,
				id: id
			}),
			headers: {
				"Content-type": "application/json",	
				'Authorization': 'Bearer ' + token 
			}
		})
		.then(response => {
			if(response.status === 200){
				alert("Actualización realizada con éxito");
				
				
				setempresaconfirmada(nombreFinalParaEnviar);
				setestadoconfirmado(estado);
				
				
				setnuevonombre(""); 
			}
			else if (response.status == 409){
				alert("Ya existe empresa con ese nombre");
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
		fetch('http://localhost:8081/devolverempresa',{
			method: 'POST', 
			headers: {
				'Content-Type': 'application/json',
				'Authorization': 'Bearer ' + token 
			},
			body: JSON.stringify({name: nombreEmpresaOriginal})
		})
		.then(response => {
			if (response.status === 200) {
				return response.json().then(data => {
					console.log(data);
					setid(data.id);
					
					setestado(data.estado);
					
					setestadoconfirmado(data.estado); 
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
			<div className='divprincipalmodificarcompanias'>
			
				
				<p className='textoempresa'> Nombre: {empresaconfirmada} </p>
				<p className='textoempresa'> Estado: {estado} </p>
				
				<p className='textoempresa'> Ingrese nuevo nombre </p>
				
				<input 
					type="text"
					value={nuevonombreempresa} 
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