import { useLocation } from 'react-router-dom';
import { useNavigate } from "react-router";
import './modificarcompanias.css'
import { useState, useEffect } from 'react';


function Modificarcompanias(){
	
	const navigate = useNavigate();
	
	const volver = () => {
		
		navigate("/AdministrarCompanias")
	}
	
	
	const cambiarnombre = (e) => {
		
		setnuevonombre(e.target.value)
		console.log(e.target.value)
			
	}
	
	
	const guardarcambios = (e) => {
		
		e.preventDefault();
		
		const nombreoriginalnormalizado = nombreEmpresaOriginal.toLowerCase().trim().normalize("NFD").replace(/[\u0300-\u0306]/g, "");
		const nuevonombre = nuevonombreempresa.toLowerCase().trim().normalize("NFD").replace(/[\u0300-\u0306]/g, "");
		
		
	
				
		
		if(nombreoriginalnormalizado == nuevonombre){
			
			alert("El nombre elegido es el mismo que ya posee la empresa")
			
		}
		
		
		
		else{
			
			fetch('http://localhost:8081/actualizardatosdeempresa', {
				
			method: "POST",
			body: JSON.stringify(
				{
					name:nuevonombreempresa,
					estado:estado,
					id:id
				}),
				headers: {
			"Content-type": "application/json",	
			'Authorization': 'Bearer ' + token },	
				
				
			})
			
			.then(response => {

				if(response.status === 200){
					alert("Actualizacion realizada con exito")
				}
				
				else if (response.status == 409){
					
					alert("Ya existe empresa con ese nombre")
					
				}
				
				else{
					
					alert("Error en la conexion, pruebe mas tarde")
				}
				
			}).catch(error => {
			    console.error("El error real es:", error); 
			    alert('Error en la conexion con la base de datos, pruebe mas tarde');
					});
	
	}
		
		
		
	}
	
	
	
	
	const location = useLocation();
	
	const nombreEmpresaOriginal = location.state?.nombre || "";
	
	
	
	//nombreempresa y estado hay que enviarlo de vuelta en el fetch para guardar los cambios
	
	
	const [nuevonombreempresa,setnuevonombre] = useState("");
		
	
	const [estado, setestado] = useState("");
	
	const [id, setid] = useState("");
	
	
	const [nombreempresa, setnombreempresa] = useState("");
	
	
	const dardebaja = () => {
			
			if(estado == "Activo"){
				
				setestado("inactivo")
			
			}
			if(estado == "inactivo"){
				
				setestado("Activo")
	
			}
		}

	
	
	
	
		
		let token = localStorage.getItem('token');
				
		useEffect(() => {		
			
		fetch('http://localhost:8081/devolverempresa',{
				
				
			method: 'POST', 
			headers: {
			'Content-Type': 'application/json',
			'Authorization': 'Bearer ' + token },
			body: JSON.stringify({name: nombreEmpresaOriginal})
			

				
			})
			.then(response => {
	        
		if (response.status === 200) {
	
		        return response.json().then(data => {
		           
					console.log(data);
					setestado(data.estado);
					setnombreempresa(data.name)
					setid(data.id)
					
		        });
		    } 
		    else if (response.status === 402) {
		        alert("Error en el token");
		        return { status: response.status, body: null }; 
		    }
		    else {
		        alert("Error en la bd intente mas tarde");
		        return { status: response.status, body: null }; 
		    }
		})
		.catch(error => {
		    console.error("El error real es:", error); 
		    alert('Error en la conexion con la base de datos, pruebe mas tarde');
				});
	
	}, []);
	return(
		
		<form onSubmit={guardarcambios}>
		<div className='divprincipalmodificarcompanias'>
		
		<p className='textoempresa'> {nombreempresa} </p>
		<p className='textoempresa'>{estado} </p>
		<p className='textoempresa'> ingrese nuevo nombre </p>
		<input onChange={cambiarnombre}/>
		
		
		
		
		<button   type = "button"onClick={dardebaja}> Cambiar estado </button>
		
		<button onClick={guardarcambios}> Guardar cambios </button>
		
		<button  type = "button" onClick={volver}> Volver </button>
		
		
		</div>
		</form>
		
	)
	
	
}


export default Modificarcompanias;